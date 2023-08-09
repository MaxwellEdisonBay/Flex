package com.flexfitnesstestapp.ui.component.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.flexfitnesstestapp.R

@Composable
internal fun BaseActionButton(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(50),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick, shape = shape,
        modifier = modifier,
//            .padding(horizontal = 30.dp, vertical = 3.dp),
//            .background(
//                Color.Black
//            ),
        enabled = enabled,
        contentPadding = contentPadding,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
        )
    ) {
        Text(text = stringResource(id = textRes))
    }
}

@Composable
internal fun BaseGreyIconButton(
    @StringRes textRes: Int,
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick, shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(horizontal = 7.dp, vertical = 5.dp),
        modifier = modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
//            .padding()
//            .padding(horizontal = 20.dp, vertical = 3.dp)
//            .background(
//                Color.Black
//            )
        ,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.common_button_light_grey),
        )
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = textRes),
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Medium
        )
    }
}