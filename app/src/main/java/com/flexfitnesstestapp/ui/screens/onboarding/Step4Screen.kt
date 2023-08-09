package com.flexfitnesstestapp.ui.screens.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.navigation.Screen
import com.flexfitnesstestapp.ui.component.base.BaseActionButton
import com.flexfitnesstestapp.ui.component.base.BaseElevatedIconButton
import com.flexfitnesstestapp.ui.component.layout.BaseScreenLayout
import com.flexfitnesstestapp.ui.component.text.BaseBodyGreyText
import com.flexfitnesstestapp.ui.component.text.BaseSubTitleHeader
import com.flexfitnesstestapp.ui.component.text.BaseTitleHeader
import com.flexfitnesstestapp.ui.component.topbar.AppBarState
import com.flexfitnesstestapp.ui.screens.main.SharedViewModel
import com.flexfitnesstestapp.ui.theme.nunitoFamily
import com.flexfitnesstestapp.utils.ONBOARDING_PROGRESS_STEP

@Composable
internal fun Step4Screen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    onAppBarState: (AppBarState) -> Unit
) {

    var days by remember {
        mutableStateOf(DAYS_MIN_VALUE)
    }

    val onContinue: () -> Unit = {
        sharedViewModel.onboardingState.apply {
            progressTest.value += ONBOARDING_PROGRESS_STEP
            selectedDays = days
        }
        navController.navigate(Screen.Onboarding.Step5.route)
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
            BaseTitleHeader(textRes = R.string.onboarding_step4_title)
            Spacer(modifier = Modifier.height(15.dp))
            BaseSubTitleHeader(textRes = R.string.onboarding_step4_subtitle)
            Spacer(modifier = Modifier.height(15.dp))
            FrequencySelector(
                days = days,
                plusEnabled = days != DAYS_MAX_VALUE,
                minusEnabled = days != DAYS_MIN_VALUE,
                onPlusClick = { days++ },
                onMinusClick = { days-- },
            )
        }
        BaseActionButton(
            textRes = R.string.continue_text,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
                .padding(horizontal = 30.dp, vertical = 3.dp),

            ) {
            onContinue()
        }
    }
}

const val DAYS_MIN_VALUE = 1
const val DAYS_MAX_VALUE = 7

@Composable
private fun FrequencySelector(
    days: Int,
    minusEnabled: Boolean,
    plusEnabled: Boolean,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,

    ) {
    val daysString =
        "$days " + stringResource(id = if (days == 1) R.string.common_day_quantified else R.string.common_days_quantified)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.common_button_blue_grey))
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {

            BaseElevatedIconButton(
                iconRes = R.drawable.ic_minus,
                contentDescRes = R.string.onboarding_step4_selector_remove_alt_text,
                onClick = onMinusClick,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterStart),
                enabled = minusEnabled,
                fractionWidth = .5f,
                fractionHeight = 1f
            )

            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = daysString,
                    fontFamily = nunitoFamily,
                    fontSize = 20.sp,
                    color = Color.Black,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                BaseBodyGreyText(
                    textRes = R.string.common_per_week,
                    textAlign = TextAlign.Center,
                    color = R.color.common_text_blue_grey
                )
            }
            BaseElevatedIconButton(
                iconRes = R.drawable.ic_add,
                contentDescRes = R.string.onboarding_step4_selector_add_alt_text,
                onClick = onPlusClick,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterEnd),
                enabled = plusEnabled
            )


        }
    }
}