package com.diegopizzo.design.components.card

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diegopizzo.design.components.surface.LFSurface
import com.diegopizzo.design.components.surface.LFSurfaceShape
import com.diegopizzo.design.components.text.LFBodyLarge
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.BorderStrokeTokens
import com.diegopizzo.design.tokens.SpaceTokens

@Composable
fun LFCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    LFSurface(
        modifier = modifier,
        onClick = onClick,
        shape = LFSurfaceShape.Big,
        border = BorderStrokeTokens.Medium(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        content()
    }
}

@Preview("Default", "LFCard", showBackground = true)
@Preview("Dark theme", "LFCard", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFCellIconPreview() {
    LFTheme {
        LFCard(
            modifier = Modifier.padding(SpaceTokens.Large),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                LFBodyLarge(text = "LFCard")
            }
        }
    }
}
