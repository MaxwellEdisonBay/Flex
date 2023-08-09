package com.flexfitnesstestapp.ui.screens.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.data.model.api.PlaceEnum
import com.flexfitnesstestapp.navigation.Screen
import com.flexfitnesstestapp.ui.component.base.BaseSelectIconButtonData
import com.flexfitnesstestapp.ui.component.base.BaseSelectionIconButton
import com.flexfitnesstestapp.ui.component.layout.BaseScreenLayout
import com.flexfitnesstestapp.ui.component.text.BaseTitleHeader
import com.flexfitnesstestapp.ui.component.topbar.AppBarState
import com.flexfitnesstestapp.ui.screens.main.SharedViewModel
import com.flexfitnesstestapp.utils.ONBOARDING_PROGRESS_STEP

private const val EXPANSION_ANIMATION_TIME_DURATION = 600

private typealias PlaceButtonData = BaseSelectIconButtonData<PlaceEnum>

@Composable
internal fun Step3Screen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    onAppBarState: (AppBarState) -> Unit
) {
    var isButtonsVisible by remember {
        mutableStateOf(false)
    }

    val enterTransition = remember {
        slideInVertically(
            animationSpec = tween(
                EXPANSION_ANIMATION_TIME_DURATION,
                delayMillis = 200
            ), initialOffsetY = { it / 2 }) + fadeIn(
            initialAlpha = .1f,
            animationSpec = tween(EXPANSION_ANIMATION_TIME_DURATION, delayMillis = 200)
        )
    }

    val buttonData = remember {
        listOf(
            PlaceButtonData(
                textRes = R.string.onboarding_step3_button_gym,
                iconRes = R.drawable.ic_location,
                type = PlaceEnum.GYM
            ),
            PlaceButtonData(
                textRes = R.string.onboarding_step3_button_home,
                iconRes = R.drawable.ic_home,
                type = PlaceEnum.HOME
            ),
        )
    }

    val onButtonClick: (PlaceEnum) -> Unit = {
        sharedViewModel.onboardingState.apply {
            progressTest.value += ONBOARDING_PROGRESS_STEP
            place = it
        }
        navController.navigate(Screen.Onboarding.Step4.route)
    }

    LaunchedEffect(Unit) {
        isButtonsVisible = true
    }

    BackHandler(enabled = true) {
        sharedViewModel.onboardingState.progressTest.value -= ONBOARDING_PROGRESS_STEP
        navController.popBackStack()
    }

    BaseScreenLayout(
        onAppBarState = onAppBarState,
        appBarState = AppBarState(
            displayTopBar = true,
            displayBackButton = true,
            displayProgressBar = true
        )
    ) {
        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            BaseTitleHeader(textRes = R.string.onboarding_step3_title)
            Spacer(modifier = Modifier.height(15.dp))
            AnimatedVisibility(visible = isButtonsVisible, enter = enterTransition) {
                Column {

                    Spacer(modifier = Modifier.height(16.dp))

                    buttonData.forEach {
                        PlaceSelectButton(it, onButtonClick)
                    }
                }
            }
        }
    }
}

@Composable
private fun PlaceSelectButton(data: PlaceButtonData, onClick: (PlaceEnum) -> Unit) {
    BaseSelectionIconButton(
        textRes = data.textRes,
        iconRes = data.iconRes,
        iconColor = R.color.common_icon_bg_green,
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick(data.type) }
    )
    Spacer(modifier = Modifier.height(16.dp))
}