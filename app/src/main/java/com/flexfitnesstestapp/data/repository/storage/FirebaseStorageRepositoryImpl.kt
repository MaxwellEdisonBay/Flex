package com.flexfitnesstestapp.data.repository.storage

import android.net.Uri
import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.data.utils.Constants.STORAGE_IMAGE_REFERENCE
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FirebaseStorageRepositoryImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage
) : FirebaseStorageRepository {

    override val storageRef: StorageReference
        get() = firebaseStorage.reference

    override suspend fun uploadImage(imageUri: Uri): Resource<String> {
        return try {
            val fileRef = storageRef.child("$STORAGE_IMAGE_REFERENCE/${imageUri.lastPathSegment}")
            val uploadTask = fileRef.putFile(imageUri)
            val result = uploadTask.await().storage.downloadUrl.await().toString()
            Resource.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}
