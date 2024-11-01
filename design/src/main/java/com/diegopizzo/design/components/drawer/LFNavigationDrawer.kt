package com.diegopizzo.design.components.drawer

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.diegopizzo.design.components.divider.LFVerticalSpacer
import com.diegopizzo.design.components.image.LFIcon
import com.diegopizzo.design.components.image.LFIconViewData
import com.diegopizzo.design.components.image.PainterViewData.Companion.drawableResourcePainter
import com.diegopizzo.design.components.text.LFHeadingLarge
import com.diegopizzo.design.theme.Icons
import com.diegopizzo.design.theme.LFTheme
import com.diegopizzo.design.tokens.ShapeTokens
import com.diegopizzo.design.tokens.SpaceTokens
import com.diegopizzo.design.util.applyHazeEffect
import com.diegopizzo.design.util.conditional
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import kotlin.math.absoluteValue

@Composable
fun LFNavigationDrawer(
    navigationItems: List<LFNavigationDrawerItemViewData>,
    modifier: Modifier = Modifier,
    navigationDrawerTitle: String? = null,
    modalNavigationDrawerWidth: LFModalNavigationDrawerWidth = LFModalNavigationDrawerWidth.Medium,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    drawerShape: LFModalNavigationDrawerShape = LFModalNavigationDrawerShape.Zero,
    gesturesEnabled: Boolean = true,
    onNavigationItemClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var drawerWidthPx by remember { mutableFloatStateOf(1f) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        drawerContent = {
            LFModalNavigationDrawer(
                items = navigationItems,
                label = navigationDrawerTitle,
                drawerShape = drawerShape,
                width = modalNavigationDrawerWidth,
                onNavigationItemClick = onNavigationItemClick,
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    drawerWidthPx = coordinates.size.width.toFloat()
                },
            )
        },
        scrimColor = Color.Unspecified,
        modifier = modifier,
    ) {
        val hazeState = remember { HazeState() }

        val animateAlpha: Float by animateFloatAsState(
            targetValue = (1 - (drawerState.currentOffset / drawerWidthPx).absoluteValue).coerceIn(0f, 1f),
            label = "Haze Alpha",
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .haze(hazeState),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .applyHazeEffect(hazeState) {
                    alpha = animateAlpha
                },
        )
    }
}

@Composable
private fun LFModalNavigationDrawer(
    items: List<LFNavigationDrawerItemViewData>,
    modifier: Modifier = Modifier,
    label: String? = null,
    drawerShape: LFModalNavigationDrawerShape,
    width: LFModalNavigationDrawerWidth,
    drawerContainerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    drawerContentColor: Color = contentColorFor(drawerContainerColor),
    onNavigationItemClick: () -> Unit = {},
) {
    ModalDrawerSheet(
        modifier = modifier
            .sizeIn(minWidth = width.minWidth, maxWidth = width.maxWidth),
        drawerShape = drawerShape.value,
        drawerTonalElevation = 0.dp,
        drawerContainerColor = drawerContainerColor,
        drawerContentColor = drawerContentColor,
    ) {
        LFVerticalSpacer(SpaceTokens.Huge)
        label?.let {
            LFHeadingLarge(
                text = it,
                modifier = Modifier.padding(SpaceTokens.Large),
            )
        }
        items.forEachIndexed { index, item ->
            LFNavigationDrawerItem(
                viewData = item,
                modifier = Modifier.conditional(items.lastIndex != index) {
                    padding(bottom = SpaceTokens.Medium)
                },
                onClick = onNavigationItemClick,
            )
        }
    }
}

@Composable
private fun LFNavigationDrawerItem(
    viewData: LFNavigationDrawerItemViewData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val colors = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = Color.Unspecified,
    )
    viewData.run {
        NavigationDrawerItem(
            modifier = modifier,
            label = {
                LFHeadingLarge(
                    text = label,
                    color = if (selected) MaterialTheme.colorScheme.primary else Color.Unspecified,
                )
            },
            icon = icon?.let {
                {
                    LFIcon(
                        viewData = it.copy(
                            tint = if (selected) MaterialTheme.colorScheme.primary else Color.Unspecified,
                        ),
                        modifier = Modifier.size(32.dp),
                    )
                }
            },
            selected = selected,
            onClick = onClick,
            colors = colors,
        )
    }
}

@Immutable
data class LFNavigationDrawerItemViewData(
    val label: String,
    val icon: LFIconViewData? = null,
    val selected: Boolean = false,
)

enum class LFModalNavigationDrawerShape(val value: Shape) {
    Zero(RectangleShape),
    Mini(ShapeTokens.CornerMini),
    Small(ShapeTokens.CornerSmall),
    Medium(ShapeTokens.CornerMedium),
}

enum class LFModalNavigationDrawerWidth(val minWidth: Dp, val maxWidth: Dp) {
    Small(minWidth = 120.dp, maxWidth = 180.dp),
    Medium(minWidth = 180.dp, maxWidth = 280.dp),
    Large(minWidth = 280.dp, maxWidth = 360.dp),
}

@Preview("Default", "LFNavigationDrawer", showBackground = true)
@Preview("Dark theme", "LFNavigationDrawer", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFNavigationDrawerPreview() {
    val state = rememberDrawerState(DrawerValue.Open)
    val defaultItem = LFNavigationDrawerItemViewData(
        label = "Item 1",
        selected = true,
        icon = LFIconViewData(
            painter = drawableResourcePainter(Icons.Goal),
        ),
    )
    val items = listOf(
        defaultItem,
        defaultItem.copy(label = "Item 2", icon = null, selected = false),
    )
    LFTheme {
        LFNavigationDrawer(
            navigationItems = items,
            drawerState = state,
        ) {

            LFHeadingLarge("Content Content Content...")
        }
    }
}
