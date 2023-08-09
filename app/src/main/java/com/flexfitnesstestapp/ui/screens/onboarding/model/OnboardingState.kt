package com.flexfitnesstestapp.ui.screens.onboarding.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.flexfitnesstestapp.data.model.api.GenderEnum
import com.flexfitnesstestapp.data.model.api.PlaceEnum
import com.flexfitnesstestapp.data.model.api.ProgramTypesEnum
import com.flexfitnesstestapp.utils.ONBOARDING_PROGRESS_STEP

internal data class OnboardingState(
    var programType: ProgramTypesEnum? = null,
    var gender: GenderEnum? = null,
    var place: PlaceEnum? = null,
    var selectedDays: Int? = null,
    var referralCode: String? = null,
    val progressTest: MutableState<Float> = mutableStateOf(ONBOARDING_PROGRESS_STEP)
)
