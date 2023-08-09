package com.flexfitnesstestapp.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mybudget.utils.networkconnection.ConnectionState
import com.example.mybudget.utils.networkconnection.connectivityState
import com.flexfitnesstestapp.navigation.Navigation
import com.flexfitnesstestapp.navigation.Screen
import com.flexfitnesstestapp.navigation.currentRoute
import com.flexfitnesstestapp.ui.component.CircularIndeterminateProgressBar
import com.flexfitnesstestapp.ui.component.topbar.AppBarState
import com.flexfitnesstestapp.ui.component.topbar.ProgressTopBar
import com.flexfitnesstestapp.ui.screens.auth.AuthViewModel
import com.flexfitnesstestapp.utils.ONBOARDING_PROGRESS_STEP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(authViewModel: AuthViewModel) {
    val sharedViewModel = hiltViewModel<SharedViewModel>()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val searchProgressBar = remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val progress by remember {
        sharedViewModel.onboardingState.progressTest
    }
    var appBarState by remember {
        mutableStateOf(AppBarState())
    }

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    val currentRoute = currentRoute(navController)


    val loginFlow = authViewModel.loginFlow.collectAsState()
    if (currentRoute != null && currentRoute !== Screen.Login.route && loginFlow.value == null) {
        navController.navigate(Screen.Login.route) {
            popUpTo(0) // reset stack
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {

        }
    ) {
        Scaffold(
            topBar = {
                if (appBarState.displayProgressBar) {
                    ProgressTopBar(progress,
                        onBackNavigation = if (navController.previousBackStackEntry != null) {
                            {
                                sharedViewModel.onboardingState.progressTest.value -= ONBOARDING_PROGRESS_STEP
                                navController.popBackStack()
                            }
                        } else null)
                }
            },
            content = {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Navigation(
                        authViewModel,
                        sharedViewModel,
                        navController,
                        Modifier.padding(it),
                        onAppBarState = {
                            appBarState = it
                        })
                    Column {
                        CircularIndeterminateProgressBar(
                            isDisplayed = searchProgressBar.value,
                            0.1f
                        )
                    }
                }
            },
            bottomBar = {
            }
        )
    }

}
