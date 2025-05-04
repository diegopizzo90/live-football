package com.diegopizzo.livefootball.design.components.snackbar

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.diegopizzo.livefootball.design.R
import com.diegopizzo.livefootball.design.theme.LFTheme

@Composable
fun LFSnackbar(
    viewData: LFSnackBarViewData,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onPerformAction: () -> Unit = {},
) {
    val adaptedViewData = viewData.copy(
        actionLabel = viewData.actionLabel ?: if (!viewData.withDismissActionIcon && viewData.withDismissActionText) {
            stringResource(R.string.dismiss)
        } else {
            null
        },
    )

    Snackbar(
        snackbarData = AndroidSnackbarDataAdapter(adaptedViewData, onDismiss, onPerformAction),
        modifier = modifier,
        shape = SnackbarDefaults.shape,
        containerColor = MaterialTheme.colorScheme.inverseSurface,
        contentColor = MaterialTheme.colorScheme.inverseOnSurface,
        actionColor = MaterialTheme.colorScheme.inverseOnSurface,
        actionContentColor = MaterialTheme.colorScheme.inverseOnSurface,
        dismissActionContentColor = MaterialTheme.colorScheme.inverseOnSurface,
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
