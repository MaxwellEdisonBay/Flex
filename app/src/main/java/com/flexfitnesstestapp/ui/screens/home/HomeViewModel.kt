package com.flexfitnesstestapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.data.model.db.ProfileData
import com.flexfitnesstestapp.data.repository.auth.FirebaseAuthRepository
import com.flexfitnesstestapp.data.repository.firestore.FirebaseFirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val firestoreRepo: FirebaseFirestoreRepository,
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {
    private val _getProfileFlow = MutableStateFlow<Resource<ProfileData?>?>(null)
    internal val getProfileFlow = _getProfileFlow.asStateFlow()
    internal fun getProfileData(userId: String?) {
        viewModelScope.launch {
            _getProfileFlow.value = Resource.Loading

            val result = firestoreRepo.getProfileData(
                userId.orEmpty()
            )
            _getProfileFlow.value = result
        }
    }
}