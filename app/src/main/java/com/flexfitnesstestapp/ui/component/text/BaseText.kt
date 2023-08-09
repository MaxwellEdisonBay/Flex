package com.flexfitnesstestapp.ui.component.text

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.flexfitnesstestapp.R

@Composable
internal fun BaseTitleHeader(@StringRes textRes: Int, textAlign: TextAlign = TextAlign.Start) {
    Text(
        text = stringResource(id = textRes),
        style = MaterialTheme.typography.titleLarge,
        fontSize = 32.sp,
        textAlign = textAlign,
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = 0.sp,
        lineHeight = 34.sp
    )
}

@Composable
internal fun BaseSubTitleHeader(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    @ColorRes color: Int = R.color.common_light_grey
) {
    Text(
        text = stringResource(id = textRes),
        style = MaterialTheme.typography.titleMedium,
        color = colorResource(
            id = color
        ),
        textAlign = textAlign,
        modifier = modifier,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        letterSpacing = 1.sp
    )
}

@Composable
internal fun BaseBodyGreyText(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    @ColorRes color: Int = R.color.common_light_grey
) {
    Text(
        text = stringResource(id = textRes),
        style = MaterialTheme.typography.titleMedium,
        color = colorResource(
            id = color
        ),
        textAlign = textAlign,
        modifier = modifier
    )
}