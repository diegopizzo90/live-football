package com.diegopizzo.design.components.snackbar

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.diegopizzo.design.R
import com.diegopizzo.design.theme.LFTheme

@Composable
fun LFSnackbar(
    viewData: LFSnackBarViewData,
    modifier: Modifier = Modifier,
) {

    val label = viewData.actionLabel ?: if (!viewData.withDismissActionIcon && viewData.withDismissActionText) {
        stringResource(R.string.dismiss)
    } else null

    val snackbarViewData = viewData.copy(
        actionLabel = label,
    )

    Snackbar(
        snackbarData = snackbarViewData,
        modifier = modifier,
        shape = SnackbarDefaults.shape,
        containerColor = MaterialTheme.colorScheme.inverseSurface,
        contentColor = MaterialTheme.colorScheme.inverseOnSurface,
        actionColor = MaterialTheme.colorScheme.inverseOnSurface,
        actionContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        dismissActionContentColor = MaterialTheme.colorScheme.inverseOnSurface,
    )
}

@Immutable
data class LFSnackBarViewData(
    val message: String,
    val actionLabel: String? = null,
    val withDismissActionIcon: Boolean = false,
    val withDismissActionText: Boolean = true,
    val duration: LFSnackBarDuration = LFSnackBarDuration.Short,
    val onDismiss: () -> Unit = {},
    val onPerformAction: () -> Unit = {},
) : SnackbarData {
    override val visuals: SnackbarVisuals = object : SnackbarVisuals {
        override val actionLabel: String? = this@LFSnackBarViewData.actionLabel?.toUpperCase(Locale.current)
        override val duration: SnackbarDuration = this@LFSnackBarViewData.duration.toSnackbarDuration()
        override val message: String = this@LFSnackBarViewData.message
        override val withDismissAction: Boolean = this@LFSnackBarViewData.withDismissActionIcon
    }

    override fun dismiss() = onDismiss()
    override fun performAction() = onPerformAction()
}

@Immutable
enum class LFSnackBarDuration {
    /** Show the Snackbar for a short period of time */
    Short,

    /** Show the Snackbar for a long period of time */
    Long,

    /** Show the Snackbar indefinitely until explicitly dismissed or action is clicked */
    Indefinite,
    ;

    internal fun toSnackbarDuration() = when (this) {
        Short -> SnackbarDuration.Short
        Long -> SnackbarDuration.Long
        Indefinite -> SnackbarDuration.Indefinite
    }
}

fun SnackbarData.toLFSnackbarViewData(): LFSnackBarViewData {
    return LFSnackBarViewData(
        message = visuals.message,
        actionLabel = visuals.actionLabel,
        withDismissActionIcon = visuals.withDismissAction,
        duration = LFSnackBarDuration.Short,
        onDismiss = { dismiss() },
        onPerformAction = { performAction() },
    )
}

@Preview("Default", "LFSurface")
@Preview("Dark theme", "LFSurface", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LFSnackbarPreview(
    @PreviewParameter(LFSnackbarPreviewParameterProvider::class)
    viewData: LFSnackBarViewData,
) {
    LFTheme {
        LFSnackbar(
            viewData = viewData,
        )
    }
}

private class LFSnackbarPreviewParameterProvider : PreviewParameterProvider<LFSnackBarViewData> {
    val default = LFSnackBarViewData(
        message = "Snackbar message",
    )
    override val values: Sequence<LFSnackBarViewData>
        get() = listOf(
            default,
            default.copy(actionLabel = "Action"),
            default.copy(
                message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                    "incididunt ut labore et dolore magna aliqua",
            ),
            default.copy(
                message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                    "incididunt ut labore et dolore magna aliqua",
                actionLabel = "Action",
            ),
            default.copy(withDismissActionIcon = true),
            default.copy(withDismissActionText = false),
        ).asSequence()
}
