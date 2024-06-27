package com.diegopizzo.design.components.text

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.theme.disabled

@Composable
fun LFTitleSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = Color.Unspecified,
    textAlign: TextAlign? = null,
    overflow: TextOverflow? = null,
    softWrap: Boolean? = null,
    maxLines: Int? = null,
    minLines: Int? = null,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    enabled: Boolean = true,
) {
    LFBaseText(
        text = text,
        color = color?.takeOrElse { MaterialTheme.colorScheme.onSurface.disabled(enabled) },
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier,
        enabled = enabled,
    )
}

@Preview("Default", "LFTitleSmall", showBackground = true)
@Preview("Dark theme", "LFTitleSmall", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFTitleSmallPreview(
    @PreviewParameter(LFTitleSmallPreviewParameterProvider::class)
    viewData: LFTitleSmallViewData,
) {
    LFTheme {
        LFTitleSmall(
            text = viewData.text,
            enabled = viewData.enabled,
        )
    }
}

private data class LFTitleSmallViewData(val text: String, val enabled: Boolean)

private class LFTitleSmallPreviewParameterProvider : PreviewParameterProvider<LFTitleSmallViewData> {
    override val values: Sequence<LFTitleSmallViewData>
        get() = listOf(
            LFTitleSmallViewData("TitleSmall", true),
            LFTitleSmallViewData("TitleSmall", false),
        ).asSequence()
}
