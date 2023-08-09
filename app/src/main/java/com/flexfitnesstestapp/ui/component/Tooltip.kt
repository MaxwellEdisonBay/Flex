package com.flexfitnesstestapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.flexfitnesstestapp.R
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.overlay.BalloonOverlayRoundRect

@Composable
internal fun getBalloonBuilder() = rememberBalloonBuilder {
    setArrowSize(9)
    setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
    setArrowPosition(0.5f)
    setWidth(BalloonSizeSpec.WRAP)
    setHeight(BalloonSizeSpec.WRAP)
    setPadding(12)
    setCornerRadius(60f)
    setBackgroundColorResource(R.color.black)
    setAutoDismissDuration(2000)
    setFocusable(false)
    setDismissWhenShowAgain(true)
    setDismissWhenTouchOutside(true)
    setBalloonAnimation(BalloonAnimation.FADE)
//    setIsVisibleOverlay(false)
//        setBalloonHighlightAnimation(BalloonHighlightAnimation.SHAKE)
    setOverlayShape(
        BalloonOverlayRoundRect(
            R.dimen.editBalloonOverlayRadius,
            R.dimen.editBalloonOverlayRadius
        )
    )
    setDismissWhenClicked(true)
}

@Composable
internal fun getTooltipText(@StringRes textRes: Int) =
    Text(
        text = stringResource(id = textRes),
        color = Color.White,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Medium
    )