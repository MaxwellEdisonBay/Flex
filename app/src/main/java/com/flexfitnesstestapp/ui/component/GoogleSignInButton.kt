package com.flexfitnesstestapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flexfitnesstestapp.R
import sv.lib.squircleshape.SquircleShape

@Composable
internal fun GoogleSignInButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,

        modifier = Modifier
            .clip(shape = SquircleShape())
            .background(color = Color.White)
            .border(1.dp, colorResource(id = R.color.common_light_grey), SquircleShape())
            .fillMaxWidth(0.8f),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
        )


    ) {

        Image(painter = painterResource(id = R.drawable.google_logo), contentDescription = null)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = stringResource(id = R.string.login_screen_sign_in_google),
            color = colorResource(
                id = R.color.common_dark_grey
            )
        )

    }
}

@Preview
@Composable
internal fun GoogleSignInButtonPreview() {
}
