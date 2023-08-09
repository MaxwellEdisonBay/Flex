package com.flexfitnesstestapp.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.flexfitnesstestapp.R
import com.flexfitnesstestapp.utils.ONBOARDING_PROGRESS_STEP

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean, verticalBias: Float) {
    if (isDisplayed) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()//.background(Color.Black.copy(alpha = 0.5f)),
        ) {
            val (progressBar) = createRefs()
            val topBias = createGuidelineFromTop(verticalBias)
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(progressBar)
                {
                    top.linkTo(topBias)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}

@Composable
internal fun GreyLinearProgressBar(progress: Float, modifier: Modifier = Modifier) {
    LinearProgressIndicator(
        progress = progress,
        modifier = modifier
            .clip(CircleShape)
            .height(2.dp),
        color = colorResource(id = R.color.common_light_grey),
        trackColor = colorResource(
            id = R.color.common_button_light_grey
        )
    )
}

@Composable
internal fun AnimatedProgressBar(progress: Float, modifier: Modifier = Modifier) {
    var localProgress by remember { mutableStateOf(progress - ONBOARDING_PROGRESS_STEP) }
    val progressAnimDuration = 600
    val progressAnimation by animateFloatAsState(
        targetValue = localProgress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
        label = ""
    )
    LinearProgressIndicator(
        progress = progressAnimation,
        modifier = modifier
            .clip(CircleShape)
            .height(2.dp),
        color = colorResource(id = R.color.common_light_grey),
        trackColor = colorResource(
            id = R.color.common_button_light_grey
        ),
    )
    LaunchedEffect(progress) {
        localProgress = progress
    }
}