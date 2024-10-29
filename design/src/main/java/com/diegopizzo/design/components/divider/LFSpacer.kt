package com.diegopizzo.design.components.divider

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp

@Composable
@NonRestartableComposable
fun LFVerticalSpacer(
    height: Dp,
) {
    Layout(
        modifier = Modifier.height(height),
        measurePolicy = SpacerMeasurePolicy,
    )
}

@Composable
@NonRestartableComposable
fun LFHorizontalSpacer(
    width: Dp,
) {
    Layout(
        modifier = Modifier.width(width),
        measurePolicy = SpacerMeasurePolicy,
    )
}

private object SpacerMeasurePolicy : MeasurePolicy {

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints,
    ): MeasureResult {
        return with(constraints) {
            val width = if (hasFixedWidth) maxWidth else 0
            val height = if (hasFixedHeight) maxHeight else 0
            layout(width, height) {}
        }
    }
}
