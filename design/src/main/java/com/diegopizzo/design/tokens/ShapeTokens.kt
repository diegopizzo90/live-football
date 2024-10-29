package com.diegopizzo.design.tokens

import androidx.compose.foundation.shape.RoundedCornerShape

/**
 * The border radius tokens are as follows:
 * - CornerMini: 4dp
 * - CornerSmall: 8dp
 * - CornerMedium: 12dp
 * - CornerLarge: 16dp
 * - CornerBig: 20dp
 */
object ShapeTokens {
    /**
     * CornerMini border radius (4dp)
     */
    val CornerMini = RoundedCornerShape(BorderRadiusTokens.Mini)

    /**
     * CornerSmall border radius (8dp)
     */
    val CornerSmall = RoundedCornerShape(BorderRadiusTokens.Small)

    /**
     * CornerMedium border radius (12dp)
     */
    val CornerMedium = RoundedCornerShape(BorderRadiusTokens.Medium)

    /**
     * CornerLarge border radius (16dp)
     */
    val CornerLarge = RoundedCornerShape(BorderRadiusTokens.Large)

    /**
     * CornerBig border radius (20dp)
     */
    val CornerBig = RoundedCornerShape(BorderRadiusTokens.Big)

    /**
     * CircleShape
     */
    val CircleShape = RoundedCornerShape(percent = 50)
}
