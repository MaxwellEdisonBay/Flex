package com.flexfitnesstestapp.ui.component.base

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flexfitnesstestapp.R

@Composable
internal fun BaseElevatedIconButton(
    @DrawableRes iconRes: Int,
    @StringRes contentDescRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fractionWidth: Float = 0.6f,
    fractionHeight: Float = 0.6f,
    @ColorRes containerColor: Int = R.color.white,
    @ColorRes contentColor: Int = R.color.black,
    @ColorRes disabledContainerColor: Int = R.color.white,
    @ColorRes disabledContentColor: Int = R.color.common_light_grey,
    elevation: Dp = 2.dp
) {
    Card(
        shape = CircleShape, modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
    ) {
        IconButton(
            onClick = onClick,

            enabled = enabled,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = colorResource(id = containerColor),
                contentColor = colorResource(id = contentColor),
                disabledContainerColor = colorResource(id = disabledContainerColor),
                disabledContentColor = colorResource(id = disabledContentColor),
            ),
            modifier = Modifier

                .fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = stringResource(
                    id = contentDescRes
                ),
//                    tint = colorResource(id = R.color.black),
                modifier = Modifier
                    .fillMaxWidth(fractionWidth)
                    .fillMaxHeight(fractionHeight)

            )
        }
    }
}