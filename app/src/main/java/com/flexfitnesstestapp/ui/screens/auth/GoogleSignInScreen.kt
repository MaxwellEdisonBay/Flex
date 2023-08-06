package com.flexfitnesstestapp.ui.screens.auth

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.ui.component.GoogleSignInButton
import com.flexfitnesstestapp.ui.component.background.AnimatedBackground
import timber.log.Timber

@Composable
internal fun GoogleSignInScreen(
    authViewModel: AuthViewModel
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                authViewModel.getGoogleSignInResult(result)
            }
        }
    )

    val uriHandler = LocalUriHandler.current

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = colorResource(id = R.color.common_light_grey))) {
            append(stringResource(id = R.string.login_screen_sign_in_policy_start_text) + " ")
        }
        pushStringAnnotation(tag = "policy", annotation = "https://www.getflex.fitness/privacy-policy.html")
        withStyle(style = SpanStyle(color = Color.Black)) {
            append(stringResource(id = R.string.login_screen_sign_in_privacy_policy))
        }
        pop()

        withStyle(style = SpanStyle(color = colorResource(id = R.color.common_light_grey))) {
            append(" "  + stringResource(id = R.string.login_screen_sign_in_policy_middle_text) + " ")
        }

        pushStringAnnotation(tag = "terms", annotation = "https://www.getflex.fitness/terms-of-use.html")

        withStyle(style = SpanStyle(color = Color.Black)) {
            append(stringResource(id = R.string.login_screen_sign_in_terms_of_use))
        }
        pop()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 25.dp),
//                    .scale(1.1f),
                painter = painterResource(id = R.drawable.flex_text_logo),
                contentDescription = stringResource(
                    id = R.string.flex_logo_alt_text
                )
            )
            Spacer(modifier = Modifier.height(90.dp))
            Text(text= stringResource(id = R.string.login_screen_sign_in_motto),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                )
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            GoogleSignInButton {
                authViewModel.launchGoogleLogin(launcher)
            }
            Text(
                text = stringResource(id = R.string.login_screen_sign_in_other_options),
                color = colorResource(
                    id = R.color.common_light_pale_blue
                ),
                modifier = Modifier.padding(vertical = 12.dp),
                style = MaterialTheme.typography.titleMedium
            )
            ClickableText(text = annotatedString, style = MaterialTheme.typography.titleSmall, onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "policy", start = offset, end = offset).firstOrNull()?.let {
                    Timber.tag("policy URL").d(it.item)
                    uriHandler.openUri(it.item)

                }

                annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset).firstOrNull()?.let {
                    Timber.tag("terms URL").d(it.item)
                    uriHandler.openUri(it.item)
                }
            })
        }

    }
}