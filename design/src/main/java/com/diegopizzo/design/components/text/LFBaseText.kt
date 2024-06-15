package com.diegopizzo.design.components.text

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.diegopizzo.design.theme.disabled

@Composable
internal fun LFBaseText(
    text: String,
    color: Color?,
    textAlign: TextAlign?,
    overflow: TextOverflow?,
    softWrap: Boolean?,
    maxLines: Int?,
    minLines: Int?,
    onTextLayout: (TextLayoutResult) -> Unit,
    style: TextStyle,
    modifier: Modifier = Modifier,
    enabled: Boolean,
) {
    LFBaseText(
        text = AnnotatedString(text),
        color = color,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style,
        modifier = modifier,
        enabled = enabled,
    )
}

@Composable
internal fun LFBaseText(
    text: AnnotatedString,
    color: Color?,
    textAlign: TextAlign?,
    overflow: TextOverflow?,
    softWrap: Boolean?,
    maxLines: Int?,
    minLines: Int?,
    onTextLayout: ((TextLayoutResult) -> Unit)?,
    style: TextStyle,
    modifier: Modifier = Modifier,
    enabled: Boolean,
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor(color, style, enabled),
        textAlign = textAlign,
        overflow = overflow ?: TextOverflow.Ellipsis,
        softWrap = softWrap ?: true,
        maxLines = maxLines ?: Int.MAX_VALUE,
        minLines = minLines ?: 1,
        onTextLayout = onTextLayout ?: {},
        style = style,
    )
}

@Composable
private fun textColor(
    color: Color?,
    style: TextStyle,
    enabled: Boolean,
): Color {
    color?.let { return it }
    return (style.color.takeOrElse { LocalContentColor.current }).disabled(enabled)
}
