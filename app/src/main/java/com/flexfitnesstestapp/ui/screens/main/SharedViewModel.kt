package com.flexfitnesstestapp.ui.screens.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.flexfitnesstestapp.data.model.db.ProfileData
import com.flexfitnesstestapp.data.repository.api.ApiRepository
import com.flexfitnesstestapp.ui.screens.onboarding.model.OnboardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SharedViewModel @Inject constructor(private val apiRepository: ApiRepository) :
    ViewModel() {

    internal val onboardingState: OnboardingState = OnboardingState()
    internal val currentProfile = mutableStateOf<ProfileData?>(null)
}