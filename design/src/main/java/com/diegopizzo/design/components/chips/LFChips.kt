package com.diegopizzo.design.components.chips

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.diegopizzo.design.components.divider.LFHorizontalSpacer
import com.diegopizzo.design.components.image.LFIcon
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData.Companion.drawableResourcePainter
import com.diegopizzo.design.components.surface.LFSurface
import com.diegopizzo.design.components.surface.LFSurfaceShape
import com.diegopizzo.design.components.text.LFTitleMedium
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.BorderStrokeTokens
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.design.util.conditional

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LFChips(
    viewData: List<LFChipViewData>,
    modifier: Modifier = Modifier,
    onClick: (viewData: LFChipViewData) -> Unit = {},
) {
    FlowRow(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = SpaceTokens.ExtraLarge, vertical = SpaceTokens.MediumLarge),
    ) {
        viewData.forEachIndexed { index, chip ->
            LFChip(
                viewData = chip,
                modifier = Modifier
                    .conditional(index != viewData.lastIndex) {
                        padding(end = SpaceTokens.Medium)
                    },
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun LFChip(
    viewData: LFChipViewData,
    modifier: Modifier = Modifier,
    onClick: (viewData: LFChipViewData) -> Unit = {},
) {
    val chipColor = MaterialTheme.colorScheme.primary
    with(viewData) {
        LFSurface(
            modifier = modifier,
            onClick = { onClick(viewData) },
            color = Color.Unspecified,
            shape = LFSurfaceShape.Big,
            border = BorderStrokeTokens.Medium(chipColor),
        ) {
            Row(
                modifier = Modifier
                    .conditional(selected) {
                        background(chipColor)
                    }
                    .padding(SpaceTokens.Medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                icon?.let {
                    LFIcon(
                        viewData = it,
                        modifier = Modifier.size(16.dp),
                    )
                    LFHorizontalSpacer(width = SpaceTokens.Small)
                }
                LFTitleMedium(
                    text = text,
                    color = if (selected) MaterialTheme.colorScheme.background else Color.Unspecified,
                    maxLines = 1,
                    isUppercase = isTextUppercase,
                )
            }
        }
    }
}

@Immutable
data class LFChipViewData(
    val id: Long = 0,
    val icon: LFIconViewData? = null,
    val text: String,
    val isTextUppercase: Boolean = false,
    val selected: Boolean = false,
)

private class LFChipsPreviewParameterProvider : PreviewParameterProvider<List<LFChipViewData>> {
    private val default
        get() = LFChipViewData(
            text = "Chip 1",
            icon = LFIconViewData(drawableResourcePainter(Icons.ItalyFlag)),
            isTextUppercase = false,
            selected = false,
        )
    override val values: Sequence<List<LFChipViewData>>
        get() = listOf(
            listOf(
                default,
                default.copy(text = "Chip 2", selected = true, icon = null),
                default.copy(text = "Chip 3"),
                default.copy(text = "Chip 4", isTextUppercase = true),
                default.copy(text = "Chip 5"),
                default.copy(text = "Chip 6", selected = true, icon = null),
                default.copy(text = "Chip 7"),
                default.copy(text = "Chip 8", isTextUppercase = true),
                default.copy(text = "Chip 9"),
                default.copy(text = "Chip 10", selected = true, icon = null),
                default.copy(text = "Chip 11"),
                default.copy(text = "Chip 12", isTextUppercase = true),
            ),
        ).asSequence()
}

@Preview("Default", "LFChip")
@Preview("Dark theme", "LFChip", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFChipsPreview(
    @PreviewParameter(LFChipsPreviewParameterProvider::class)
    viewData: List<LFChipViewData>,
) {
    LFTheme {
        LFChips(
            viewData = viewData,
        )
    }
}
