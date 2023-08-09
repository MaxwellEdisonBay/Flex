package com.flexfitnesstestapp.ui.screens.onboarding.step1

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.data.model.api.ProgramTypesEnum
import com.flexfitnesstestapp.navigation.Screen
import com.flexfitnesstestapp.ui.component.base.BaseSelectIconButtonData
import com.flexfitnesstestapp.ui.component.layout.BaseScreenLayout
import com.flexfitnesstestapp.ui.component.text.BaseTitleHeader
import com.flexfitnesstestapp.ui.component.topbar.AppBarState
import com.flexfitnesstestapp.ui.screens.main.SharedViewModel
import com.flexfitnesstestapp.ui.theme.nunitoFamily
import com.flexfitnesstestapp.utils.ONBOARDING_PROGRESS_STEP

private const val EXPANSION_ANIMATION_TIME_DURATION = 300

internal typealias ProgramTypeData = BaseSelectIconButtonData<ProgramTypesEnum>

@Composable
internal fun Step1Screen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    onAppBarState: (AppBarState) -> Unit
) {
    val viewModel = hiltViewModel<Step1ViewModel>()
    val itemList = remember { viewModel.buttonList }
    val onSelectClick: (ProgramTypesEnum) -> Unit = {
        sharedViewModel.onboardingState.apply {
            programType = it
            progressTest.value += ONBOARDING_PROGRESS_STEP
        }
        navController.navigate(Screen.Onboarding.Step2.route)
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
            BaseTitleHeader(textRes = R.string.onboarding_step1_title)
            Spacer(modifier = Modifier.height(35.dp))
            itemList.forEach {
                ProgramTypeRow(data = it, onSelectClick)
            }
        }
    }
}

@Composable
private fun ColumnScope.ProgramTypeRow(data: ProgramTypeData, onCLick: (ProgramTypesEnum) -> Unit) {
    var isCollapsed by remember {
        mutableStateOf(false)
    }
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPANSION_ANIMATION_TIME_DURATION)
        ) + fadeIn(initialAlpha = .3f, animationSpec = tween(EXPANSION_ANIMATION_TIME_DURATION))
    }

    Button(
        onClick = {
            onCLick(data.type)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.5.dp),
        shape = RoundedCornerShape(25.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = if (isCollapsed) 10.dp else 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = data.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = stringResource(id = data.textRes), color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = nunitoFamily
                    )
                }
                AnimatedVisibility(visible = isCollapsed, enter = enterTransition) {
                    Text(
                        text = stringResource(id = data.textDesc ?: R.string.no_data),
                        modifier = Modifier.padding(start = 42.dp),
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.common_light_blue_grey),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        letterSpacing = 0.5.sp

                    )
                }
            }
            if (!isCollapsed) {
                IconButton(onClick = { isCollapsed = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = stringResource(
                            id = R.string.onboarding_step1_info_alt_text
                        ),
                        tint = colorResource(id = R.color.common_light_grey),
                        modifier = Modifier
                            .rotate(180f)
                            .size(30.dp)
                    )
                }
            }


        }

    }
}