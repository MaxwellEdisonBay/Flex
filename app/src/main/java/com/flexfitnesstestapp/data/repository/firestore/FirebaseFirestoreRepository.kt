package com.flexfitnesstestapp.data.repository.firestore

import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.data.model.db.ProfileData
import com.google.firebase.firestore.CollectionReference

internal interface FirebaseFirestoreRepository {
    val users: CollectionReference
    suspend fun addNewProfile(profileData: ProfileData, userId: String): Resource<String>
    suspend fun getProfileData(userId: String): Resource<ProfileData?>
    suspend fun updateProfileWithOnboardingData(
        profileData: ProfileData,
        userId: String
    ): Resource<String>

    suspend fun deleteProfile(id: String): Resource<String>
}