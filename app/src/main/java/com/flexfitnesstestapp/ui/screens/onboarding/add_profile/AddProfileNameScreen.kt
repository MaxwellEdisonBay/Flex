package com.flexfitnesstestapp.ui.screens.onboarding.add_profile

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.data.datasource.remote.Resource
import com.flexfitnesstestapp.navigation.Screen
import com.flexfitnesstestapp.ui.component.base.BaseActionButton
import com.flexfitnesstestapp.ui.component.base.BaseGreyIconButton
import com.flexfitnesstestapp.ui.component.base.BaseTextField
import com.flexfitnesstestapp.ui.component.getBalloonBuilder
import com.flexfitnesstestapp.ui.component.getTooltipText
import com.flexfitnesstestapp.ui.component.layout.BaseScreenLayout
import com.flexfitnesstestapp.ui.component.text.BaseBodyGreyText
import com.flexfitnesstestapp.ui.component.text.BaseTitleHeader
import com.flexfitnesstestapp.ui.component.topbar.AppBarState
import com.flexfitnesstestapp.utils.validateNameField
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.BalloonWindow

@Composable
internal fun AddProfileNameScreen(
    navController: NavController,
    onAppBarState: (AppBarState) -> Unit
) {
    val viewModel = hiltViewModel<AddProfileNameViewModel>()
    val addProfileFlow = viewModel.addProfileFlow.collectAsState()
    var nameText by remember { mutableStateOf("") }
    var nameError by remember {
        mutableStateOf(false)
    }
    var tooltip by remember {
        mutableStateOf<BalloonWindow?>(null)
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> uri?.let { selectedImageUri = it } }
    )

    val onImagePickerClick = {
        singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    val onNameFieldChange: (String) -> Unit = {
        nameError = it.validateNameField(allowBlank = true)
        nameText = it
    }

    val onContinue = {
        nameText = nameText.trim()
        nameError = nameText.validateNameField()
        if (!nameError) {
            viewModel.addProfileInfo(
                name = nameText,
                imageUri = selectedImageUri
            )

        } else {
            tooltip?.showAlignTop(yOff = 45.dp.value.toInt())
        }
    }

    val onAddSuccess = {
        navController.navigate(Screen.Onboarding.route) {
            popUpTo(0)
        }
    }

    BaseScreenLayout(onAppBarState) {
        Column(
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth()
        ) {
            BaseTitleHeader(textRes = R.string.add_profile_name_title)
            Spacer(modifier = Modifier.height(16.dp))
            BaseBodyGreyText(textRes = R.string.add_profile_name_subtitle)
            Spacer(modifier = Modifier.height(30.dp))
            Card(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(80.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    colorResource(id = R.color.blue_gradient_start),
                                    colorResource(id = R.color.blue_gradient_end),
                                )
                            )
                        )
                ) {
                    if (selectedImageUri == null) {
                        Image(
                            painter = painterResource(id = R.drawable.slack_smile),
                            contentDescription = stringResource(
                                id = R.string.add_profile_name_add_image_alt_text
                            ),
                            modifier = Modifier
                                .fillMaxSize(0.65f)
                                .align(Alignment.Center)
                        )
                    } else {
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = stringResource(
                                id = R.string.add_profile_name_add_image_alt_text
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            BaseGreyIconButton(
                textRes = R.string.add_profile_name_edit_button_title,
                iconRes = R.drawable.ic_add_profile_photo,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                onImagePickerClick()
            }
            Spacer(modifier = Modifier.height(10.dp))
            Balloon(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                builder = getBalloonBuilder(),
                balloonContent = {
                    getTooltipText(textRes = R.string.add_profile_name__error_tooltip)
                }
            ) { balloonWindow ->
                tooltip = balloonWindow
                BaseTextField(
                    value = nameText,
                    onValueChange = onNameFieldChange,
                    placeholderRes = R.string.add_profile_name_name_field_placeholder,
                    isError = nameError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        autoCorrect = false
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onContinue()
                        }
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
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
                        onAddSuccess()
                    }
                }
            }
        }
        BaseActionButton(
            textRes = R.string.continue_text,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
                .padding(horizontal = 30.dp, vertical = 3.dp),

//                    .imePadding(),
        ) {
            onContinue()
        }
    }
}