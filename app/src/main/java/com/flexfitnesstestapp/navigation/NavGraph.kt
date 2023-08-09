package com.flexfitnesstestapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.flexfitnesstestapp.ui.component.topbar.AppBarState
import com.flexfitnesstestapp.ui.screens.auth.AuthViewModel
import com.flexfitnesstestapp.ui.screens.auth.GoogleSignInScreen
import com.flexfitnesstestapp.ui.screens.home.HomeScreen
import com.flexfitnesstestapp.ui.screens.main.SharedViewModel
import com.flexfitnesstestapp.ui.screens.onboarding.Step2Screen
import com.flexfitnesstestapp.ui.screens.onboarding.Step3Screen
import com.flexfitnesstestapp.ui.screens.onboarding.Step4Screen
import com.flexfitnesstestapp.ui.screens.onboarding.add_profile.AddProfileNameScreen
import com.flexfitnesstestapp.ui.screens.onboarding.step1.Step1Screen
import com.flexfitnesstestapp.ui.screens.onboarding.step5.Step5Screen

@Composable
internal fun Navigation(
    authViewModel: AuthViewModel,
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    modifier: Modifier,
    onAppBarState: (AppBarState) -> Unit
) {
    val initialPage = if (authViewModel.isLoggedIn) {
        Screen.Home.route
    } else {
        Screen.Login.route
    }
    NavHost(navController, startDestination = initialPage, modifier) {
        composable(Screen.Login.route) {

            GoogleSignInScreen(
                authViewModel,
                navController,
                onAppBarState = onAppBarState
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                authViewModel = authViewModel,
                navController = navController,
                sharedViewModel = sharedViewModel,
                onAppBarState = onAppBarState
            )
        }
        composable(Screen.AddProfileName.route) {
            AddProfileNameScreen(
                navController = navController,
                onAppBarState = onAppBarState
            )
        }

        navigation(
            route = Screen.Onboarding.route,
            startDestination = Screen.Onboarding.Step1.route
        ) {
            composable(Screen.Onboarding.Step1.route) {
                Step1Screen(
                    sharedViewModel = sharedViewModel,
                    navController = navController,
                    onAppBarState = onAppBarState
                )
            }
            composable(Screen.Onboarding.Step2.route) {
                Step2Screen(
                    sharedViewModel = sharedViewModel,
                    navController = navController,
                    onAppBarState = onAppBarState
                )
            }
            composable(Screen.Onboarding.Step3.route) {
                Step3Screen(
                    sharedViewModel = sharedViewModel,
                    navController = navController,
                    onAppBarState = onAppBarState
                )
            }
            composable(Screen.Onboarding.Step4.route) {
                Step4Screen(
                    sharedViewModel = sharedViewModel,
                    navController = navController,
                    onAppBarState = onAppBarState
                )
            }

            composable(Screen.Onboarding.Step5.route) {
                Step5Screen(
                    sharedViewModel = sharedViewModel,
                    navController = navController,
                    onAppBarState = onAppBarState
                )
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}