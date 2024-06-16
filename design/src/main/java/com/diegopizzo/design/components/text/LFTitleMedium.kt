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
import java.util.Locale

@Composable
fun LFTitleMedium(
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
        text = text.uppercase(Locale.getDefault()),
        color = color?.takeOrElse { MaterialTheme.colorScheme.primary.disabled(enabled) },
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier,
        enabled = enabled,
    )
}

@Preview("Default", "LFTitleMedium", showBackground = true)
@Preview("Dark theme", "LFTitleMedium", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFTitleMediumPreview(
    @PreviewParameter(LFTitleMediumPreviewParameterProvider::class)
    viewData: LFTitleMediumViewData,
) {
    LFTheme {
        LFTitleMedium(
            text = viewData.text,
            enabled = viewData.enabled,
        )
    }
}

data class LFTitleMediumViewData(val text: String, val enabled: Boolean)

private class LFTitleMediumPreviewParameterProvider : PreviewParameterProvider<LFTitleMediumViewData> {
    override val values: Sequence<LFTitleMediumViewData>
        get() = listOf(
            LFTitleMediumViewData("TitleMedium", true),
            LFTitleMediumViewData("TitleMedium", false),
        ).asSequence()
}
