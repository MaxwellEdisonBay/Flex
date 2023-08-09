package com.flexfitnesstestapp.ui.screens.onboarding.step5

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.navigation.Screen
import com.flexfitnesstestapp.ui.component.base.BaseActionButton
import com.flexfitnesstestapp.ui.component.base.BaseElevatedIconButton
import com.flexfitnesstestapp.ui.component.base.BaseSelectIconButtonData
import com.flexfitnesstestapp.ui.component.base.BaseSelectionIconButton
import com.flexfitnesstestapp.ui.component.base.BaseTextField
import com.flexfitnesstestapp.ui.component.layout.BaseScreenLayout
import com.flexfitnesstestapp.ui.component.text.BaseTitleHeader
import com.flexfitnesstestapp.ui.component.topbar.AppBarState
import com.flexfitnesstestapp.ui.component.topbar.rememberImeState
import com.flexfitnesstestapp.ui.screens.main.SharedViewModel
import com.flexfitnesstestapp.ui.theme.nunitoFamily
import com.flexfitnesstestapp.utils.ONBOARDING_PROGRESS_STEP
import kotlinx.coroutines.launch


internal typealias ReferralButtonData = BaseSelectIconButtonData<Boolean>

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Step5Screen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    onAppBarState: (AppBarState) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val vm = hiltViewModel<Step5ViewModel>()
    val updateProfileFlow = vm.updateProfileFlow.collectAsState()


    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    var isButtonsVisible by remember {
        mutableStateOf(false)
    }

    var refText by remember {
        mutableStateOf("")
    }

    val basePadding = dimensionResource(id = R.dimen.common_content_padding)

    val enterTransition = remember { vm.enterTransition }

    val buttonData = remember { vm.buttonData }

    val onUpdate: (Boolean) -> Unit = {
        sharedViewModel.onboardingState.apply {
            progressTest.value += ONBOARDING_PROGRESS_STEP
            referralCode = if (it) refText else null
            vm.doUpdateOnboardingInfoCall(this)
        }
    }

    val onUpdateSuccess = {
        navController.navigate(Screen.Home.route) {
            popUpTo(0)
        }
    }

    val hideBottomSheet = {
        coroutineScope.launch { bottomSheetState.hide() }
            .invokeOnCompletion {
                if (!bottomSheetState.isVisible) {
                    openBottomSheet = false
                }
            }
    }

    val onRowButtonClick: (Boolean) -> Unit = {
        if (it) {
            openBottomSheet = true
        } else {
            onUpdate(false)
        }

    }

    LaunchedEffect(Unit) {
        isButtonsVisible = true
    }

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
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
            BaseTitleHeader(textRes = R.string.onboarding_step5_title)
            Spacer(modifier = Modifier.height(15.dp))
            AnimatedVisibility(visible = isButtonsVisible, enter = enterTransition) {
                Column {

                    Spacer(modifier = Modifier.height(16.dp))

                    buttonData.forEach {
                        ReferralSelectButton(it, onRowButtonClick)
                    }
                }
            }
        }
        // Sheet content
        if (openBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier


                    .padding(bottom = 300.dp),
                shape = RoundedCornerShape(30.dp),
                onDismissRequest = { openBottomSheet = false },
                sheetState = bottomSheetState,
                dragHandle = {
                    Divider(
                        modifier = Modifier
                            .width(50.dp)
                            .padding(vertical = 5.dp),
                        thickness = 2.dp,
                        color = colorResource(
                            id = R.color.common_light_grey
                        )
                    )
                }
            ) {

                Card(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxWidth(),

                    colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.common_bg_grey)),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
//                            .padding(top = 3.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
                            .padding(
                                top = 10.dp,
                                bottom = basePadding,
                                end = basePadding,
                                start = basePadding
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = stringResource(id = R.string.onboarding_step5_bottom_sheet_title),
                                color = colorResource(
                                    id = R.color.black
                                ),
                                fontFamily = nunitoFamily,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                letterSpacing = 0.sp
                            )
                            BaseElevatedIconButton(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .size(30.dp),
                                iconRes = R.drawable.ic_close,
                                contentDescRes = R.string.close,
                                onClick = {
                                    refText = ""
                                    hideBottomSheet()
                                },
                                contentColor = R.color.common_grey,
                                containerColor = R.color.transparent,
                                fractionHeight = 1f,
                                fractionWidth = 1f,
                                elevation = 0.5.dp
                            )

                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        BaseTextField(
                            value = refText,
                            onValueChange = { refText = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholderRes = R.string.onboarding_step5_bottom_sheet_placeholder
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        BaseActionButton(
                            textRes = R.string.continue_text,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(30),
                            contentPadding = PaddingValues(vertical = 15.dp),
                            onClick = { onUpdate(true) },
                            enabled = refText.isNotBlank()
                        )
                    }

                }
            }
        }
        updateProfileFlow.value?.let {
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
                        onUpdateSuccess()
                    }
                }
            }
        }
    }
}

@Composable
private fun ReferralSelectButton(data: ReferralButtonData, onClick: (Boolean) -> Unit) {
    BaseSelectionIconButton(
        textRes = data.textRes,
        iconRes = data.iconRes,
        iconColor = if (data.type) R.color.common_icon_bg_green else R.color.common_text_field_error,
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick(data.type) }
    )
    Spacer(modifier = Modifier.height(16.dp))
}