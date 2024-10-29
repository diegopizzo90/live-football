package com.diegopizzo.design.components.divider

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.design.tokens.ThicknessTokens

@Composable
fun LFHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = ThicknessTokens.Thin,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    Canvas(
        modifier
            .fillMaxWidth()
            .padding(horizontal = SpaceTokens.MediumLarge)
            .height(thickness),
    ) {
        drawLine(
            color = color,
            strokeWidth = thickness.toPx(),
            start = Offset(0f, thickness.toPx() / 2),
            end = Offset(size.width, thickness.toPx() / 2),
        )
    }
}

@Preview("Default", "LFDivider", showBackground = true)
@Preview("Dark theme", "LFDivider", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LFDividerPreview() {
    LFTheme {
        Box(
            modifier = Modifier.size(400.dp),
            contentAlignment = Alignment.Center,
        ) {
            LFHorizontalDivider()
        }
    }
}
