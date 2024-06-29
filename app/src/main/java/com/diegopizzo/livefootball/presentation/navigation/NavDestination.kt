package com.diegopizzo.livefootball.presentation.navigation

sealed class Destination(val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) {
        route
    } else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{$it}") }
        builder.toString()
    }

    sealed class NoArgsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    data object Splash : NoArgsDestination(route = "splash")
    data object Home : NoArgsDestination(route = "home")

    internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
        val builder = StringBuilder(this)

        params.forEach {
            it.second?.toString()?.let { arg ->
                builder.append("/$arg")
            }
        }

        return builder.toString()
    }
}
