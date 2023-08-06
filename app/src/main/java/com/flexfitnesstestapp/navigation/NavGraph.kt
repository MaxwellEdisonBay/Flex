package com.flexfitnesstestapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.flexfitnesstestapp.ui.screens.auth.AuthViewModel
import com.flexfitnesstestapp.ui.screens.auth.GoogleSignInScreen
import com.flexfitnesstestapp.ui.screens.auth.SignUpScreen
import com.flexfitnesstestapp.ui.screens.main.SharedViewModel

@Composable
internal fun Navigation(
    authViewModel: AuthViewModel,
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(navController, startDestination = Screen.Login.route, modifier) {
        composable(Screen.Login.route) {
//            LoginScreen(
//                viewModel = authViewModel,
//                navController = navController,
//            )
            GoogleSignInScreen (
                authViewModel
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                viewModel = authViewModel,
                navController = navController,
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}