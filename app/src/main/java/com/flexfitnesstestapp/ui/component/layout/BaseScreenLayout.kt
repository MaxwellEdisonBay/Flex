package com.flexfitnesstestapp.ui.component.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.ui.component.topbar.AppBarState

@Composable
internal fun BaseScreenLayout(
    onAppBarState: (AppBarState) -> Unit,
    modifier: Modifier = Modifier,
    appBarState: AppBarState = AppBarState(),
    content: @Composable (BoxScope.() -> Unit),
) {
    LaunchedEffect(Unit) {
        onAppBarState(appBarState)
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.common_bg_grey))
            .padding(horizontal = dimensionResource(id = R.dimen.common_content_padding))
    ) {
        content()
    }
}