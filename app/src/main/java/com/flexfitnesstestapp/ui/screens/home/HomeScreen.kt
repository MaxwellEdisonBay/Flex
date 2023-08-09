package com.flexfitnesstestapp.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.data.model.db.ProfileData
import com.flexfitnesstestapp.ui.component.base.BaseActionButton
import com.flexfitnesstestapp.ui.component.layout.BaseScreenLayout
import com.flexfitnesstestapp.ui.component.text.BaseTitleHeader
import com.flexfitnesstestapp.ui.component.topbar.AppBarState
import com.flexfitnesstestapp.ui.screens.auth.AuthViewModel
import com.flexfitnesstestapp.ui.screens.main.SharedViewModel

@Composable
internal fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    sharedViewModel: SharedViewModel,
    onAppBarState: (AppBarState) -> Unit
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val addProfileFlow = viewModel.getProfileFlow.collectAsState()
    val profileData by remember {
        sharedViewModel.currentProfile
    }
    val onGetProfileSuccess: (ProfileData?) -> Unit = {
        sharedViewModel.currentProfile.value = it
    }

    LaunchedEffect(Unit) {
        viewModel.getProfileData(authViewModel.currentUser?.uid)
    }

    BaseScreenLayout(
        onAppBarState = onAppBarState,
    ) {
        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            BaseTitleHeader(textRes = R.string.home_screen_title)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = profileData.toString())
            Spacer(modifier = Modifier.height(15.dp))
            BaseActionButton(textRes = R.string.home_log_out, onClick = { authViewModel.logout() })
        }

        addProfileFlow.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }

                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        onGetProfileSuccess(it.result)
                    }
                }
            }
        }
    }

}