@file:OptIn(ExperimentalLayoutApi::class)

package com.diegopizzo.match.presentation.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diegopizzo.design.components.divider.LFHorizontalSpacer
import com.diegopizzo.design.components.divider.LFVerticalSpacer
import com.diegopizzo.design.components.surface.LFSurface
import com.diegopizzo.design.components.surface.LFSurfaceShape
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.BorderStrokeTokens
import com.diegopizzo.design.tokens.ShapeTokens
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.design.util.conditional
import com.diegopizzo.design.util.shimmerLoadingAnimation

@Composable
internal fun LoadingMatchScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = true,
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
    ) {
        ChipsContent(isLoading)
        LFVerticalSpacer(height = SpaceTokens.MediumLarge)
        DatePickerContent(isLoading)
        LFVerticalSpacer(height = SpaceTokens.MediumLarge)
        MatchContent(isLoading)
    }
}

@Composable
private fun DatePickerContent(isLoading: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = SpaceTokens.ExtraLarge),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        1.rangeTo(7).forEach { index ->
            Column(
                modifier = Modifier
                    .width(40.dp)
                    .height(55.dp)
                    .weight(1f)
                    .conditional(index == 4) {
                        clip(ShapeTokens.CornerSmall)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .shimmerLoadingAnimation(isLoadingCompleted = !isLoading)
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .width(22.dp)
                        .height(12.dp)
                        .conditional(
                            condition = index == 4,
                            onTrue = { background(MaterialTheme.colorScheme.background) },
                            onFalse = { background(MaterialTheme.colorScheme.surfaceVariant) },
                        )
                        .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                )
                LFVerticalSpacer(height = SpaceTokens.Mini)
                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .height(12.dp)
                        .conditional(
                            condition = index == 4,
                            onTrue = { background(MaterialTheme.colorScheme.background) },
                            onFalse = { background(MaterialTheme.colorScheme.surfaceVariant) },
                        )
                        .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                )
            }
            LFHorizontalSpacer(width = SpaceTokens.Small)
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
        )
    }
}

@Composable
private fun ChipsContent(isLoading: Boolean) {
    FlowRow(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = SpaceTokens.ExtraLarge),
    ) {
        1.rangeTo(10).forEach { index ->
            LFSurface(
                modifier = Modifier
                    .height(50.dp)
                    .padding(end = SpaceTokens.Medium),
                color = Color.Unspecified,
                shape = LFSurfaceShape.Big,
                border = BorderStrokeTokens.Medium(MaterialTheme.colorScheme.surfaceVariant),
            ) {
                Row(
                    modifier = Modifier.padding(SpaceTokens.Medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    if (index != 1) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                        )
                        LFHorizontalSpacer(width = SpaceTokens.Small)
                    }
                    Box(
                        modifier = Modifier
                            .conditional(
                                condition = index % 2 == 0 || index == 1,
                                onTrue = { width(32.dp) },
                                onFalse = { width(64.dp) },
                            )
                            .height(16.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                    )
                }
            }
        }
    }
}

@Composable
private fun MatchContent(
    isLoading: Boolean,
) {
    FlowColumn(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(
                start = SpaceTokens.ExtraLarge,
                end = SpaceTokens.ExtraLarge,
                bottom = SpaceTokens.ExtraLarge,
            ),
        verticalArrangement = Arrangement.spacedBy(SpaceTokens.ExtraLarge),
    ) {
        val border = BorderStrokeTokens.Medium(MaterialTheme.colorScheme.surfaceVariant)
        1.rangeTo(10).forEach { _ ->
            LFSurface(
                shape = LFSurfaceShape.Big,
                border = border,
            ) {
                Row(
                    modifier = Modifier
                        .heightIn(116.dp)
                        .padding(SpaceTokens.MediumLarge),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(24.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                    )
                    LFHorizontalSpacer(width = SpaceTokens.Large)
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                            )
                            LFHorizontalSpacer(width = SpaceTokens.Large)
                            Box(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(32.dp)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                            )
                        }
                        LFVerticalSpacer(height = SpaceTokens.Large)
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                            )
                            LFHorizontalSpacer(width = SpaceTokens.Large)
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(32.dp)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                        )
                        LFVerticalSpacer(height = SpaceTokens.ExtraLarge)
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .shimmerLoadingAnimation(isLoadingCompleted = !isLoading),
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview("Default", "LoadingMatchScreen")
@Preview("Dark theme", "LoadingMatchScreen", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingMatchScreenPreview() {
    LFTheme {
        LoadingMatchScreen(
            isLoading = true,
        )
    }
}
