package com.diegopizzo.design.tokens

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color

sealed class BorderStrokeTokens(val borderStroke: BorderStroke) {
    data class Small(val color: Color) : BorderStrokeTokens(BorderStroke(BorderWidthTokens.Hairline, color))
    data class Medium(val color: Color) : BorderStrokeTokens(BorderStroke(BorderWidthTokens.Thin, color))
    data class Thick(val color: Color) : BorderStrokeTokens(BorderStroke(BorderWidthTokens.Thick, color))
}
