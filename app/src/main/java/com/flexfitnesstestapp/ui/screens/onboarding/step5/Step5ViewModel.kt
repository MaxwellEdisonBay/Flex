package com.flexfitnesstestapp.ui.screens.onboarding.step5

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.data.model.db.ProfileData
import com.flexfitnesstestapp.data.repository.auth.FirebaseAuthRepository
import com.flexfitnesstestapp.data.repository.firestore.FirebaseFirestoreRepository
import com.flexfitnesstestapp.ui.screens.onboarding.model.OnboardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class Step5ViewModel @Inject constructor(
    private val firestoreRepo: FirebaseFirestoreRepository,
    private val authRepo: FirebaseAuthRepository
) : ViewModel() {

    private val _updateProfileFlow = MutableStateFlow<Resource<String>?>(null)
    internal val updateProfileFlow = _updateProfileFlow.asStateFlow()
    internal fun doUpdateOnboardingInfoCall(state: OnboardingState) {
        viewModelScope.launch {
            _updateProfileFlow.value = Resource.Loading
            val result = firestoreRepo.updateProfileWithOnboardingData(
                ProfileData(
                    gender = state.gender?.apiName,
                    mainGoal = state.programType?.apiName,
                    workoutPlace = state.place?.apiName,
                    frequency = state.selectedDays,
                    referralCode = state.referralCode

                ), authRepo.currentUser?.uid.orEmpty()
            )
            _updateProfileFlow.value = result
        }
    }

    internal val buttonData = listOf(
        ReferralButtonData(
            textRes = R.string.onboarding_step5_button_yes,
            iconRes = R.drawable.ic_check_filled,
            type = true
        ),
        ReferralButtonData(
            textRes = R.string.onboarding_step5_button_no,
            iconRes = R.drawable.ic_close,
            type = false
        ),
    )

    internal val enterTransition = slideInVertically(
        animationSpec = tween(
            EXPANSION_ANIMATION_TIME_DURATION,
            delayMillis = 200
        ), initialOffsetY = { it / 2 }) + fadeIn(
        initialAlpha = .1f,
        animationSpec = tween(EXPANSION_ANIMATION_TIME_DURATION, delayMillis = 200)
    )

    companion object {
        private const val EXPANSION_ANIMATION_TIME_DURATION = 600

    }
}