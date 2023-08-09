package com.flexfitnesstestapp.ui.screens.onboarding.add_profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.data.model.db.ProfileData
import com.flexfitnesstestapp.data.repository.auth.FirebaseAuthRepository
import com.flexfitnesstestapp.data.repository.firestore.FirebaseFirestoreRepository
import com.flexfitnesstestapp.data.repository.storage.FirebaseStorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AddProfileNameViewModel @Inject constructor(
    private val firestoreRepo: FirebaseFirestoreRepository,
    private val storageRepo: FirebaseStorageRepository,
    private val authRepo: FirebaseAuthRepository
) : ViewModel() {
    private val _addProfileFlow = MutableStateFlow<Resource<String>?>(null)
    internal val addProfileFlow = _addProfileFlow.asStateFlow()
    internal fun addProfileInfo(name: String, imageUri: Uri?) {
        viewModelScope.launch {
            _addProfileFlow.value = Resource.Loading
            var imageWebUrl: String? = null
            if (imageUri != null) {
                val imageResult = storageRepo.uploadImage(imageUri = imageUri)
                if (imageResult is Resource.Success) {
                    imageWebUrl = imageResult.result
                }
            }
            val result = firestoreRepo.addNewProfile(
                ProfileData(
                    userName = name,
                    photoUrl = imageWebUrl,
                ), authRepo.currentUser?.uid.orEmpty()
            )
            _addProfileFlow.value = result
        }
    }
}