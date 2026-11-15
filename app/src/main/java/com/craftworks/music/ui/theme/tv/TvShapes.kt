package com.craftworks.music.ui.theme.tv

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/** Radius tokens (spec §4.4). */
@Immutable
data class TvShapes(
    val chip: Shape = RoundedCornerShape(8.dp),
    val row: Shape = RoundedCornerShape(12.dp),
    val tile: Shape = RoundedCornerShape(16.dp),
    val panel: Shape = RoundedCornerShape(24.dp),
    val sheet: Shape = RoundedCornerShape(28.dp),
    val pill: Shape = RoundedCornerShape(percent = 50),
)

/** Spacing / size tokens (spec §4.4). Dp constants only — no colors or effects here. */
object TvDimens {
    val space4 = 4.dp
    val space8 = 8.dp
    val space12 = 12.dp
    val space16 = 16.dp
    val space24 = 24.dp
    val space32 = 32.dp
    val space48 = 48.dp
    val space64 = 64.dp

    /** Overscan safe area: content stays inside 5%. */
    val safeAreaPercent = 0.05f

    val screenMarginHorizontal = 64.dp
    val screenMarginTop = 48.dp
    val screenMarginBottom = 48.dp

    val railGap = 32.dp
    val railTitleGap = 20.dp

    val tabBarHeight = 96.dp
    val tabBarTopInset = 24.dp
    val tabBarItemGap = 40.dp

    val tileSize = 220.dp
    val artistTileSize = 200.dp

    /** Focus language (spec §4.6). */
    val focusRingWidth = 3.dp
    val focusGlowWidth = 8.dp
    val focusLift = 8.dp
    val focusScaleTile = 1.06f
    val focusScaleTab = 1.04f
    val focusScaleButton = 1.03f
    val pressScale = 0.97f
    val unfocusedTileAlpha = 0.82f
}

val LocalTvShapes = staticCompositionLocalOf { TvShapes() }
