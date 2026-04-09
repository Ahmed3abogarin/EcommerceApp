package com.vtol.ecommerceapp.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.vtol.ecommerceapp.R
import com.vtol.ecommerceapp.ui.theme.Green
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun BuyButton(modifier: Modifier = Modifier) {
    var paddleOffsetX by remember { mutableFloatStateOf(0f) }
    var isSuccess by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current


    // Reset the state after couple of seconds
    LaunchedEffect(isSuccess) {
        delay(2700)
        val start = paddleOffsetX
        animate(
            initialValue = start,
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500)
        ) { value, _ ->
            paddleOffsetX = value
        }
        isSuccess = false
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (isSuccess) Green else Color.Black,
        animationSpec = tween(500), label = "bgColor"
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (isSuccess) 0f else 100f,
        animationSpec = tween(200, easing = LinearEasing)
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.CenterStart
    ) {
        val parentWidthPx = with(density) { maxWidth.toPx() }
        val boxSizeDp = 65.dp
        val boxWidthPx = with(density) { boxSizeDp.toPx() }
        val maxOffset = parentWidthPx - boxWidthPx

        // Centered Text Label
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AnimatedVisibility(
                visible = !isSuccess,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = "Swipe to Purchase",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
            }
        }

        // The Draggable Handle
        Box(
            modifier = Modifier
                .offset { IntOffset(paddleOffsetX.roundToInt(), 0) }
                .width(boxSizeDp)
                .height(boxSizeDp)
                .padding(4.dp) // Padding to look like a button inside a track
                .clip(CircleShape)
                .background(Color.White)
                .draggable(
                    enabled = !isSuccess,
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        paddleOffsetX = (paddleOffsetX + delta).coerceIn(0f, maxOffset)
                    },
                    onDragStopped = {
                        val threshold = maxOffset * 0.9f
                        val target = if (paddleOffsetX >= threshold) {
                            isSuccess = true
                            maxOffset / 2
                        } else {
                            0f
                        }

                        coroutineScope.launch {
                            animate(
                                initialValue = paddleOffsetX,
                                targetValue = target,
                                animationSpec = tween(durationMillis = 400)
                            ) { value, _ ->
                                paddleOffsetX = value
                            }
                        }
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            //  switch between icons Smoothly
            AnimatedContent(
                targetState = isSuccess,
                transitionSpec = {
                    fadeIn(tween(300)) togetherWith fadeOut(tween(300))
                }, label = "iconTransition"
            ) { success ->
                val icon = painterResource(if (success) R.drawable.ic_check else R.drawable.ic_cart_v2)
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = if (success) Green else Color.Black,
                    modifier = Modifier
                        .height(28.dp)
                        .width(28.dp)
                )
            }
        }
    }
}