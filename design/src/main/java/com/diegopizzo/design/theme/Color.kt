package com.diegopizzo.design.theme

import androidx.compose.ui.graphics.Color

object ColorPalette {

    val Primary = Color(0xFFE91E76)
    val Secondary = Color(0xFF4C0333)

    // Alert & Status
    val Info = Color(0xFFE91E76)
    val Success = Color(0xFF42B529)
    val Warning = Color(0xFFFFC120)
    val Error = Color(0xFFF05D5D)
    val Disabled = Color(0xFFE1E1E1)
    val DisButton = Color(0xFF962364)

    // Greyscale
    val Grey900 = Color(0xFF212121)
    val Grey800 = Color(0xFF424242)
    val Grey700 = Color(0xFF616161)
    val Grey600 = Color(0xFF757575)
    val Grey500 = Color(0xFF9E9E9E)
    val Grey400 = Color(0xFFBDBDBD)
    val Grey300 = Color(0xFFE0E0E0)
    val Grey200 = Color(0xFFEEEEEE)
    val Grey100 = Color(0xFFF5F5F5)
    val Grey50 = Color(0xFFFAFAFA)

    // Gradients
    val GradientPink = Color(0xFFE91E76)
    val GradientPurple = Color(0xFF9C27B0)
    val GradientYellow = Color(0xFFFFC120)
    val GradientGreen = Color(0xFF4CAF50)
    val GradientBlue = Color(0xFF2196F3)
    val GradientRed = Color(0xFFF44336)
    val GradientOrange = Color(0xFFFF9800)

    // Dark Colors
    val Dark1 = Color(0xFF000000)
    val Dark2 = Color(0xFF212121)
    val Dark3 = Color(0xFF303030)
    val Dark4 = Color(0xFF424242)

    // Others
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val Red = Color(0xFFF44336)
    val Pink = Color(0xFFE91E76)
    val Purple = Color(0xFF9C27B0)
    val DeepPurple = Color(0xFF673AB7)
    val Indigo = Color(0xFF3F51B5)
    val Blue = Color(0xFF2196F3)
    val LightBlue = Color(0xFF03A9F4)
    val Cyan = Color(0xFF00BCD4)
    val Teal = Color(0xFF009688)
    val Green = Color(0xFF4CAF50)
    val LightGreen = Color(0xFF8BC34A)
    val Lime = Color(0xFFCDDC39)
    val Yellow = Color(0xFFFFEB3B)
    val Amber = Color(0xFFFFC107)
    val Orange = Color(0xFFFF9800)
    val DeepOrange = Color(0xFFFF5722)
    val Brown = Color(0xFF795548)
    val BlueGrey = Color(0xFF607D8B)

    // Background
    val BackgroundPink = Color(0xFFFFF0F6)
    val BackgroundPurple = Color(0xFFF3E5F5)
    val BackgroundYellow = Color(0xFFFFFDE7)
    val BackgroundGreen = Color(0xFFF1F8E9)
    val BackgroundBlue = Color(0xFFE3F2FD)
    val BackgroundRed = Color(0xFFFFEBEE)
    val BackgroundOrange = Color(0xFFFFF3E0)

    // Transparent
    val TransparentPink = Color(0x33E91E76)
    val TransparentPurple = Color(0x339C27B0)
    val TransparentYellow = Color(0x33FFC120)
    val TransparentGreen = Color(0x334CAF50)
    val TransparentBlue = Color(0x332196F3)
    val TransparentRed = Color(0x33F44336)
    val TransparentOrange = Color(0x33FF9800)
}

internal fun Color.toHex(): String {
    return String.format(
        "%02X%02X%02X%02X",
        (alpha * 255).toInt(),
        (red * 255).toInt(),
        (green * 255).toInt(),
        (blue * 255).toInt()
    )
}
