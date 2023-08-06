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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mybudget.utils.networkconnection.ConnectionState
import com.example.mybudget.utils.networkconnection.connectivityState
import com.flexfitnesstestapp.navigation.Navigation
import com.flexfitnesstestapp.navigation.currentRoute
import com.flexfitnesstestapp.ui.component.CircularIndeterminateProgressBar
import com.flexfitnesstestapp.ui.screens.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(authViewModel: AuthViewModel) {
    val sharedViewModel = hiltViewModel<SharedViewModel>()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val searchProgressBar = remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    val currentRoute = currentRoute(navController)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {

        }
    ) {
        Scaffold(
//            topBar = {
//                if (isNotLoginRoute) {
//                    when (currentRoute) {
//                        Screen.Home.route, Screen.History.route, Screen.AddNote.route -> {
//                            TopBar(
//                                title = navigationTitle(navController),
//                                openDrawer = {
//                                    scope.launch {
//                                        drawerState.apply {
//                                            if (isClosed) open() else close()
//                                        }
//                                    }
//                                },
//                            )
//                        }
//
//                        else -> {
//                            TopBarWithBackNav(
//                                title = navigationTitle(navController),
//                                openDrawer = {
//                                    scope.launch {
//                                        drawerState.apply {
//                                            if (isClosed) open() else close()
//                                        }
//                                    }
//                                },
//                                pressOnBack = {
//                                    navController.popBackStack()
//                                }
//                            )
//
//                        }
//                    }
//
//                }
//            },
            content = {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Navigation(authViewModel, sharedViewModel, navController, Modifier.padding(it))
                    Column {
                        CircularIndeterminateProgressBar(
                            isDisplayed = searchProgressBar.value,
                            0.1f
                        )

                    }
                }
            },
            bottomBar = {
//                when (currentRoute(navController)) {
//                    Screen.Home.route, Screen.AddNote.route, Screen.History.route -> {
//                        BottomNavigationUI(navController)
//                    }
//                }
            }
        )
    }

}

//@Composable
//internal fun BottomNavigationUI(navController: NavController) {
//    NavigationBar {
//        val items = listOf(
//            Screen.HomeNav,
//            Screen.AddNav,
//            Screen.HistoryNav,
//        )
//        items.forEach { item ->
//            NavigationBarItem(
//                label = { Text(text = stringResource(id = item.title)) },
//                selected = currentRoute(navController) == item.route,
//                icon = item.navIcon,
//                onClick = {
//                    navController.navigate(item.route) {
//                        // Pop up to the start destination of the graph to
//                        // avoid building up a large stack of destinations
//                        // on the back stack as users select items
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        // Avoid multiple copies of the same destination when
//                        // reselecting the same item
//                        launchSingleTop = true
//                        // Restore state when reselecting a previously selected item
//                        restoreState = true
//                    }
//                })
//        }
//    }
//}