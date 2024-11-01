package com.diegopizzo.design.theme

import androidx.annotation.DrawableRes
import com.diegopizzo.design.R

enum class Icons(@DrawableRes val idLightTheme: Int, @DrawableRes val idDarkTheme: Int) {
    Goal(R.drawable.ic_goal, R.drawable.ic_goal_dark_theme),
    YellowCard(R.drawable.ic_yellow_card, R.drawable.ic_yellow_card),
    RedCard(R.drawable.ic_red_card, R.drawable.ic_red_card),
    ItalyFlag(R.drawable.ic_italy_flag, R.drawable.ic_italy_flag),
    Calendar(R.drawable.ic_calendar, R.drawable.ic_calendar),
    Menu(R.drawable.ic_menu, R.drawable.ic_menu_dark_theme),
}
