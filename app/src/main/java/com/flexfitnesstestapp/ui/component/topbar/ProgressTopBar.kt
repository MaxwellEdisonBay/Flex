package com.flexfitnesstestapp.ui.component.topbar

import androidx.annotation.ColorRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.ui.component.AnimatedProgressBar
import com.flexfitnesstestapp.ui.component.text.BaseSubTitleHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProgressTopBar(
    progress: Float,
    @ColorRes color: Int = R.color.common_very_light_grey,
    onBackNavigation: (() -> Unit)?
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                AnimatedProgressBar(
                    progress = progress, modifier = Modifier
                        .width(200.dp)
                        .align(
                            Alignment.Center
                        )
                )
                if (onBackNavigation != null) {
                    Row(modifier = Modifier
                        .clickable { onBackNavigation() }
                        .align(Alignment.CenterStart)) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        BaseSubTitleHeader(textRes = R.string.back, color = R.color.black)
                        Spacer(modifier = Modifier.width(26.dp))
                    }
                }
            }
        },
        navigationIcon = {

        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = color)),
    )
}