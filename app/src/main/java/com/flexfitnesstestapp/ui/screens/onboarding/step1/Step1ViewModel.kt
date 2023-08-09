package com.flexfitnesstestapp.ui.screens.onboarding.step1

import androidx.lifecycle.ViewModel
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.data.model.api.ProgramTypesEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Step1ViewModel @Inject constructor() : ViewModel() {
    internal val buttonList = listOf(
        ProgramTypeData(
            R.string.onboarding_step1_lose_fat,
            R.drawable.ic_lose_fat,
            ProgramTypesEnum.LoseFat,
            R.string.onboarding_step1_lose_fat_desc,
        ),
        ProgramTypeData(
            R.string.onboarding_step1_build_muscles,
            R.drawable.ic_build_muscles,
            ProgramTypesEnum.BuildMuscles,
            R.string.onboarding_step1_build_muscles_desc,
        ),
        ProgramTypeData(
            R.string.onboarding_step1_get_stronger,
            R.drawable.ic_get_stronger,
            ProgramTypesEnum.GetStronger,
            R.string.onboarding_step1_get_stronger_desc,
        ),
        ProgramTypeData(
            R.string.onboarding_step1_improve_cardio,
            R.drawable.ic_improve_cardio,
            ProgramTypesEnum.ImproveCardio,
            R.string.onboarding_step1_improve_cardio_desc,

            ),
        ProgramTypeData(
            R.string.onboarding_step1_become_flexible,
            R.drawable.ic_become_flexible,
            ProgramTypesEnum.BecomeFlexible,
            R.string.onboarding_step1_become_flexible_desc,
        ),
        ProgramTypeData(
            R.string.onboarding_step1_general_fitness,
            R.drawable.ic_general_fitness,
            ProgramTypesEnum.GeneralFitness,
            R.string.onboarding_step1_general_fitness_desc,
        ),
    )
}