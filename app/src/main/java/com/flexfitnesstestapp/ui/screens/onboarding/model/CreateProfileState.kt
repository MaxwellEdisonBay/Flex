package com.flexfitnesstestapp.ui.screens.onboarding.model

import android.net.Uri

internal data class CreateProfileState(
    internal var name: String = "",
    internal var imageUri: Uri? = null,
)
