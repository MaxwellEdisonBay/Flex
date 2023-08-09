package com.flexfitnesstestapp.data.repository.storage

import android.net.Uri
import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.google.firebase.storage.StorageReference

internal interface FirebaseStorageRepository {
    val storageRef: StorageReference
    suspend fun uploadImage(imageUri: Uri): Resource<String>
}