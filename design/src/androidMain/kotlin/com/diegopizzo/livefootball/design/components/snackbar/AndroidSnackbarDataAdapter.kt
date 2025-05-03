package com.diegopizzo.livefootball.design.components.snackbar

import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase

class AndroidSnackbarDataAdapter(
    private val viewData: LFSnackBarViewData,
    private val onDismiss: () -> Unit = {},
    private val onPerformAction: () -> Unit = {},
) : SnackbarData {

    override val visuals: SnackbarVisuals = object : SnackbarVisuals {
        override val actionLabel: String? = viewData.actionLabel?.toUpperCase(Locale.current)
        override val duration: SnackbarDuration = viewData.duration.toSnackbarDuration()
        override val message: String = viewData.message
        override val withDismissAction: Boolean = viewData.withDismissActionIcon
    }

    override fun dismiss() = onDismiss()
    override fun performAction() = onPerformAction()

    fun LFSnackBarDuration.toSnackbarDuration() = when (this) {
        LFSnackBarDuration.Short -> SnackbarDuration.Short
        LFSnackBarDuration.Long -> SnackbarDuration.Long
        LFSnackBarDuration.Indefinite -> SnackbarDuration.Indefinite
    }

}

fun LFSnackBarViewData.asSnackbarVisuals(
    onDismiss: () -> Unit = {},
    onPerformAction: () -> Unit = {},
): SnackbarVisuals = AndroidSnackbarDataAdapter(this, onDismiss, onPerformAction).visuals

fun SnackbarData.toLFSnackbarViewData(): LFSnackBarViewData {
    return LFSnackBarViewData(
        message = visuals.message,
        actionLabel = visuals.actionLabel,
        withDismissActionIcon = visuals.withDismissAction,
        duration = LFSnackBarDuration.Short,
    )
}
