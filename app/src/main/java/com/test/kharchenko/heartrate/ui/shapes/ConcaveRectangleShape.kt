package com.test.kharchenko.heartrate.ui.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ConcaveRectangleShape(private val concave: Float = 100f) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {

        val p = Path().apply {
            moveTo(0f, size.height)
            lineTo(size.width, size.height)
            lineTo(size.width, 0f)

            quadraticBezierTo(size.width * 0.5f, concave, 0f, 0f)
            lineTo(0f, size.height)
        }
        return Outline.Generic(p)
    }
}