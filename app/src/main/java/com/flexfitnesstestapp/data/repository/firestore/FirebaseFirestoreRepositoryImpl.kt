package com.flexfitnesstestapp.data.repository.firestore

import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.data.model.db.ProfileData
import com.flexfitnesstestapp.data.utils.Constants.FIRESTORE_USERS_REFERENCE
import com.flexfitnesstestapp.data.utils.await
import com.flexfitnesstestapp.data.utils.serializeToMap
import com.flexfitnesstestapp.data.utils.toDataClass
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

internal class FirebaseFirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : FirebaseFirestoreRepository {
    override val users: CollectionReference
        get() = firebaseFirestore.collection(FIRESTORE_USERS_REFERENCE)

    override suspend fun addNewProfile(profileData: ProfileData, userId: String): Resource<String> {
        return try {
            val ref = users.document(userId)
            val profDate = profileData.serializeToMap().toMutableMap()
            profDate["createdAt"] = FieldValue.serverTimestamp()
            profDate["editedAt"] = FieldValue.serverTimestamp()
            ref.set(profDate).await()
            Resource.Success(ref.id)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun getProfileData(userId: String): Resource<ProfileData?> {
        return try {
            val result = users.document(userId).get().await()
//            val notes = result?.documents?.map { doc -> doc.data?.toDataClass() as ProfileData }.orEmpty()
            val test = result.data?.toDataClass<ProfileData>()
            Resource.Success(test)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun updateProfileWithOnboardingData(
        profileData: ProfileData,
        userId: String
    ): Resource<String> {
        return try {
            val ref = users.document(userId)
            ref.update(
                "editedAt", FieldValue.serverTimestamp(),
//                "userName", profileData.userName,
//                "photoUrl", profileData.photoUrl,
                "gender", profileData.gender,
                "mainGoal", profileData.mainGoal,
                "workoutPlace", profileData.workoutPlace,
                "frequency", profileData.frequency,
                "referralCode", profileData.referralCode,
            )
                .await()
            Resource.Success(ref.id)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }


    override suspend fun deleteProfile(id: String): Resource<String> {
        return try {
            users.document(id).delete().await()
            Resource.Success(id)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}