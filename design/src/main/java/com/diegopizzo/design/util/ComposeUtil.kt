package com.diegopizzo.design.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.conditional(
    condition: Boolean,
    onTrue: @Composable Modifier.() -> Modifier,
) = conditionalInternal(condition, onTrue = onTrue)

@Composable
fun Modifier.conditional(
    condition: Boolean,
    onTrue: @Composable Modifier.() -> Modifier,
    onFalse: @Composable Modifier.() -> Modifier,
) = conditionalInternal(condition, onTrue, onFalse)

@Composable
private fun Modifier.conditionalInternal(
    condition: Boolean,
    onTrue: @Composable Modifier.() -> Modifier,
    onFalse: (@Composable Modifier.() -> Modifier)? = null,
) = if (condition) {
    then(onTrue())
} else {
    onFalse?.let { then(Modifier.it()) }
} ?: this
