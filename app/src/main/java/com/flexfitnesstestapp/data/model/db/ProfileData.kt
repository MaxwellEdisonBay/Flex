package com.flexfitnesstestapp.data.model.db

internal data class ProfileData(
    val userName: String? = null,
    val photoUrl: String? = null,
    val gender: String? = null,
    val mainGoal: String? = null,
    val workoutPlace: String? = null,
    val frequency: Int? = null,
    val referralCode: String? = null,
)
