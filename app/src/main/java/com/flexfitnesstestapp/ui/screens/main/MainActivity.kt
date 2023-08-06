package com.flexfitnesstestapp.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.flexfitnesstestapp.ui.screens.auth.AuthViewModel
import com.flexfitnesstestapp.ui.theme.FlexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlexTheme {
                MainScreen(authViewModel)
            }
        }
    }
}