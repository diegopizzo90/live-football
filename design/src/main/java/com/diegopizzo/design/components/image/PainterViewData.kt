package com.diegopizzo.design.components.image

import androidx.compose.foundation.isSystemInDarkTheme
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
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.diegopizzo.design.theme.Icons

data class PainterViewData private constructor(
    private val imageVector: ImageVector? = null,
    private val url: String? = null,
    private val urlOptions: PainterUrlOptionsViewData? = null,
    private val drawableRes: Icons? = null,
    private val color: Color? = null,
) {

    @Composable
    operator fun invoke(): Painter =
        when {
            imageVector != null -> {
                rememberVectorPainter(image = imageVector)
            }

            url != null -> {
                val imageRequest = getUrlImageRequest()
                rememberAsyncImagePainter(
                    model = imageRequest,
                    placeholder = urlOptions?.placeholder?.invoke(),
                    error = urlOptions?.error?.invoke(),
                    contentScale = urlOptions?.contentScale ?: ContentScale.Fit,
                )
            }

            drawableRes != null -> {
                painterResource(id = if (isSystemInDarkTheme()) drawableRes.idDarkTheme else drawableRes.idLightTheme)
            }

            else -> {
                ColorPainter(color = color!!)
            }
        }

    @Composable
    private fun getUrlImageRequest() = ImageRequest.Builder(LocalContext.current).apply {
        data(url)
        urlOptions?.apply {
            when (contentScale) {
                ContentScale.Fit -> {
                    scale(Scale.FIT)
                }

                ContentScale.Crop,
                ContentScale.FillBounds,
                ContentScale.FillWidth,
                ContentScale.FillHeight,
                -> {
                    scale(Scale.FILL)
                }

                ContentScale.None -> Unit
            }
            allowHardware(allowHardwareAcceleration)
            crossfade(crossfade)
            size?.let {
                val coilSize = if (it.isUnspecified) {
                    coil.size.Size.ORIGINAL
                } else {
                    coil.size.Size(
                        width = it.width.toInt(),
                        height = it.height.toInt(),
                    )
                }
                size(coilSize)
            }
        }
    }.build()

    companion object {
        fun vectorPainter(imageVector: ImageVector) = PainterViewData(imageVector = imageVector)

        fun urlPainter(
            url: String,
            options: PainterUrlOptionsViewData = PainterUrlOptionsViewData(),
        ) = PainterViewData(
            url = url,
            urlOptions = options,
        )

        fun drawableResourcePainter(drawableRes: Icons) =
            PainterViewData(drawableRes = drawableRes)

        fun colorPainter(color: Color) = PainterViewData(color = color)
    }

    data class PainterUrlOptionsViewData(
        val placeholder: PainterViewData? = null,
        val error: PainterViewData? = null,
        val contentScale: ContentScale = ContentScale.Fit,
        val allowHardwareAcceleration: Boolean = true,
        val crossfade: Boolean = false,
        val size: Size? = null,
    )
}
