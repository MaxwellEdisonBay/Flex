package com.flexfitnesstestapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

sealed class Screen(
    val route: String,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
) {
    object Login : Screen("login_screen")
    object Home : Screen("home_screen")
    object AddProfileName : Screen("add_profile_name_screen")

    object Onboarding : Screen("onboarding_screen") {
        internal object Step1 : Screen("onboarding_step1_screen")
        internal object Step2 : Screen("onboarding_step2_screen")
        internal object Step3 : Screen("onboarding_step3_screen")
        internal object Step4 : Screen("onboarding_step4_screen")
        internal object Step5 : Screen("onboarding_step5_screen")
    }

}