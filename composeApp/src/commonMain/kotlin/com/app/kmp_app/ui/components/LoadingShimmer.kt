package com.app.kmp_app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun LoadingShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Column(modifier = Modifier.padding(16.sdp)) {
        repeat(5) {
            ShimmerItem(brush = brush)
            Spacer(modifier = Modifier.height(16.sdp))
        }
    }
}

@Composable
fun ShimmerItem(brush: Brush) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.sdp)
                .background(brush, shape = RoundedCornerShape(16.sdp))
        )
        Spacer(modifier = Modifier.height(8.sdp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(20.sdp)
                .background(brush, shape = RoundedCornerShape(4.sdp))
        )
        Spacer(modifier = Modifier.height(4.sdp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(16.sdp)
                .background(brush, shape = RoundedCornerShape(4.sdp))
        )
    }
}
