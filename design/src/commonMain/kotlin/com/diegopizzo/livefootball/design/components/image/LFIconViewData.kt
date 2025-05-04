package com.diegopizzo.livefootball.design.components.image

data class LFIconViewData(
    val painter: PainterResource,
    val contentDescription: String? = null,
    val tintHex: String? = null,
    val enabled: Boolean = true,
)

sealed interface PainterResource {
    data class Url(val url: String) : PainterResource
    data class DrawableResource(val id: Int) : PainterResource
}
