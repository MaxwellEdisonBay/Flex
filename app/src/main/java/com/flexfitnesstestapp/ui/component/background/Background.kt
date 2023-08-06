package com.flexfitnesstestapp.ui.component.background

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flexfitnesstestapp.R
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Object(
    imageSize: Dp = 75.dp,
    rotation: Float = 0f,
    @DrawableRes imageRes: Int = R.drawable.landing_b_cardio
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .rotate(rotation)
            .size(imageSize)
    )
}

@Composable
fun MoveObject(
    modifier: Modifier,
    xOffset: Int,
    amplitude: Float,
    period: Float,
    getYOffset: (Int, Float, Float) -> Float,
    getSlope: (Int, Float, Float) -> Float,
    getRotation: (Float) -> Float,
    item: @Composable () -> Unit
) {


    Box(modifier) {
        Box(
            Modifier
                .offset(
                    x = xOffset.dp,
                    y = getYOffset(xOffset, amplitude, period).dp
                )
                .rotate(getRotation(getSlope(xOffset, amplitude, period)))
                .align(Alignment.CenterStart)
        ) {
            item()
        }
    }
}

@Composable
internal fun AnimatedIcon(
    amplitude: Float = 20f,
    period: Float = 200f,
    imageSize: Dp = 75.dp,
    @DrawableRes imageRes: Int = R.drawable.landing_b_cardio,
    rotation: Float = 0f,
    timeSpeed: Int = 7000,
    verticalOffset: Dp = 0.dp,
    horizontalOffset: Dp = 0.dp,
    reverse: Boolean = false,
    startTimeOffset: Int = 0

) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val endValue = screenWidth.value.toInt()
    val endAfterFlip = if (reverse) {
        imageSize.unaryMinus().value.toInt()
    } else {
        endValue
    }

    val startAfterFlip = if (reverse) {
        screenWidth.plus(horizontalOffset).value.toInt()
    } else {
        imageSize.unaryMinus().plus(horizontalOffset.unaryMinus()).value.toInt()
    }
    val scale = remember {
        Animatable(initialValue = startAfterFlip.toFloat())
    }

    val TWO_PI = 2 * PI

    val getY: (Int, Float, Float) -> Float = { x, amplitude, period ->
        (((sin(x * TWO_PI / period) * amplitude) + verticalOffset.value)).toFloat()
    }
    val RADIANS_TO_DEGREES_RATIO = 360f / TWO_PI

    val getSlope: (Int, Float, Float) -> Float = { x, amplitude, period ->
        (
            cos((TWO_PI * x / period)) * (amplitude * (TWO_PI) / period)
            ).toFloat()
    }

    val getRotation: (Float) -> Float = { slope ->
        (atan(slope) * RADIANS_TO_DEGREES_RATIO).toFloat()
    }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = endAfterFlip.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = timeSpeed, easing = LinearEasing),
                initialStartOffset = StartOffset(startTimeOffset)
            )
        )

    }
    MoveObject(
        modifier = Modifier.fillMaxSize(),
        xOffset = scale.value.toInt(),
        amplitude = amplitude,
        period = period,
        getYOffset = getY,
        getSlope = getSlope,
        getRotation = getRotation
    ) {
        Object(imageSize, rotation, imageRes)
    }
}

@Composable
internal fun AnimatedBackground() {
    AnimatedIcon(
        amplitude = 35f,
        imageSize = 270.dp,
        timeSpeed = 7000,
        period = 1000f,
        imageRes = R.drawable.landing_b_general,
        verticalOffset = (-200).dp,
        reverse = true,
        startTimeOffset = 1500,
    )
    AnimatedIcon(
        amplitude = 160f,
        imageSize = 250.dp,
        timeSpeed = 5500,
        period = 2000f,
        imageRes = R.drawable.landing_b_flexibility,
        verticalOffset = (0).dp,
        reverse = true,
        startTimeOffset = 1000,

        )
    AnimatedIcon(
        imageSize = 260.dp,
        timeSpeed = 6500,
        period = 2500f,
        amplitude = 60f,
        imageRes = R.drawable.landing_b_lose,
        verticalOffset = 20.dp,
        reverse = true,
        startTimeOffset = 3000,

        )
    AnimatedIcon(
        imageSize = 320.dp,
        timeSpeed = 8000,
        period = 600f,
        imageRes = R.drawable.landing_b_cardio,
        verticalOffset = (260).dp,
        reverse = true,
        startTimeOffset = 11000,
    )
    AnimatedIcon(
        imageSize = 300.dp,
        timeSpeed = 8000,
        period = 800f,
        amplitude = 150f,
        imageRes = R.drawable.landing_b_gain,
        verticalOffset = (280).dp,
        reverse = true,
        startTimeOffset = 1000,

        )
    AnimatedIcon(
        imageSize = 350.dp,
        timeSpeed = 8000,
        amplitude = 160f,
        period = 1000f,
        imageRes = R.drawable.landing_b_strength,
        verticalOffset = (300).dp,
        reverse = true,
        startTimeOffset = 6000,
    )
}
