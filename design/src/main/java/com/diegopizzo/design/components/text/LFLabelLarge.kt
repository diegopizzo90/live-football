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
fun LFLabelLarge(
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
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier,
        enabled = enabled,
    )
}

@Preview("Default", "LFLabelLarge", showBackground = true)
@Preview("Dark theme", "LFLabelLarge", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFLabelLargePreview(
    @PreviewParameter(LFLabelLargePreviewParameterProvider::class)
    viewData: LFLabelLargeViewData,
) {
    LFTheme {
        LFLabelLarge(
            text = viewData.text,
            enabled = viewData.enabled,
        )
    }
}

private data class LFLabelLargeViewData(val text: String, val enabled: Boolean)

private class LFLabelLargePreviewParameterProvider : PreviewParameterProvider<LFLabelLargeViewData> {
    override val values: Sequence<LFLabelLargeViewData>
        get() = listOf(
            LFLabelLargeViewData("LabelLarge", true),
            LFLabelLargeViewData("LabelLarge", false),
        ).asSequence()
}
