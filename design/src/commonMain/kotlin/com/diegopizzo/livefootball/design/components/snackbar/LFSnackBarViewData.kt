package com.diegopizzo.livefootball.design.components.snackbar

data class LFSnackBarViewData(
    val message: String,
    val actionLabel: String? = null,
    val withDismissActionIcon: Boolean = false,
    val withDismissActionText: Boolean = true,
    val duration: LFSnackBarDuration = LFSnackBarDuration.Short,
)

enum class LFSnackBarDuration {
    Short,
    Long,
    Indefinite,
}
