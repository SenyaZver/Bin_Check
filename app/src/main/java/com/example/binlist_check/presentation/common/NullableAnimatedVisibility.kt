package com.example.binlist_check.presentation.common

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.Ref


@Composable
inline fun <T> NullableAnimatedVisibility(
    modifier: Modifier = Modifier,
    value: T?,
    enter: EnterTransition = fadeIn() + slideInVertically(),
    exit: ExitTransition = fadeOut() + slideOutVertically(),
    crossinline content: @Composable (T) -> Unit
) {
    val ref = remember {
        Ref<T>()
    }

    ref.value = value ?: ref.value

    AnimatedVisibility(
        modifier = modifier,
        visible = value != null,
        enter = enter,
        exit = exit,
        content = {
            ref.value?.let { value ->
                content(value)
            }
        }
    )
}