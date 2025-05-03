package com.diegopizzo.livefootball.design.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.isUnspecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size as CoilSize

sealed interface AndroidPainterResource {

    @Composable
    fun toPainter(): Painter

    data class VectorPainter(val imageVector: ImageVector) : AndroidPainterResource {
        @Composable
        override fun toPainter() = rememberVectorPainter(imageVector)
    }

    data class UrlPainter(
        val url: String,
        val options: PainterUrlOptionsViewData = PainterUrlOptionsViewData(),
    ) : AndroidPainterResource {
        @Composable
        override fun toPainter() = rememberAsyncImagePainter(
            model = options.createImageRequest(url),
            placeholder = options.placeholder?.toPainter(),
            error = options.error?.toPainter(),
            contentScale = options.contentScale,
        )
    }

    data class DrawableResourcePainter(val drawableRes: Int) : AndroidPainterResource {
        @Composable
        override fun toPainter() = painterResource(id = drawableRes)
    }

    data class ColorBasedPainter(val color: Color) : AndroidPainterResource {
        @Composable
        override fun toPainter() = ColorPainter(color)
    }

    data class PainterUrlOptionsViewData(
        val placeholder: AndroidPainterResource? = null,
        val error: AndroidPainterResource? = null,
        val contentScale: ContentScale = ContentScale.Fit,
        val allowHardwareAcceleration: Boolean = true,
        val crossfade: Boolean = false,
        val size: Size? = null,
    ) {
        @Composable
        fun createImageRequest(url: String): ImageRequest = ImageRequest.Builder(LocalContext.current).apply {
            data(url)
            when (contentScale) {
                ContentScale.Fit -> scale(Scale.FIT)
                ContentScale.Crop,
                ContentScale.FillBounds,
                ContentScale.FillWidth,
                ContentScale.FillHeight,
                    -> scale(Scale.FILL)

                ContentScale.None -> Unit
            }
            allowHardware(allowHardwareAcceleration)
            crossfade(crossfade)
            size?.let {
                size(
                    if (it.isUnspecified) CoilSize.ORIGINAL
                    else CoilSize(it.width.toInt(), it.height.toInt()),
                )
            }
        }.build()
    }
}

@Composable
fun PainterResource.toPainter(): Painter = when (this) {
    is PainterResource.Url -> AndroidPainterResource.UrlPainter(url).toPainter()
    is PainterResource.DrawableResource -> AndroidPainterResource.DrawableResourcePainter(id).toPainter()
}

fun String.toComposableColor(): Color = Color(this.toColorInt())
