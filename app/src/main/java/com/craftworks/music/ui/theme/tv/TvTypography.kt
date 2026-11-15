package com.craftworks.music.ui.theme.tv

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * The 10-foot type scale (spec §4.3).
 *
 * Every style uses [TvFonts.Display]; the optical-size split was dropped in favour of
 * negative tracking at large sizes and max-lines/ellipsis in layout. Colors are left
 * unspecified on purpose so a style can be reused under any label token
 * (`LocalTvColors.labelSecondary` and friends).
 */
@Immutable
data class TvTypeScale(
    val displayHero: TextStyle,
    val screenTitle: TextStyle,
    val sectionTitle: TextStyle,
    val cardTitle: TextStyle,
    val cardSubtitle: TextStyle,
    val body: TextStyle,
    val caption: TextStyle,
    val button: TextStyle,
    val tab: TextStyle,
    val npTitle: TextStyle,
    val npArtist: TextStyle,
    val lyricsActive: TextStyle,
    val lyricsInactive: TextStyle,
)

/** The bundled TV typeface; the `fonts` flavor decides which files answer these names. */
val TvFontFamily: FontFamily
    get() = TvFonts.Display

private fun tvStyle(
    size: Float,
    lineHeight: Float,
    tracking: Float,
    weight: FontWeight,
): TextStyle = TextStyle(
    fontFamily = TvFontFamily,
    fontWeight = weight,
    fontSize = size.sp,
    lineHeight = lineHeight.sp,
    letterSpacing = tracking.sp,
)

internal fun defaultTvTypeScale(): TvTypeScale = TvTypeScale(
    displayHero = tvStyle(76f, 80f, -1.5f, FontWeight.Bold),
    screenTitle = tvStyle(56f, 60f, -1.0f, FontWeight.Bold),
    sectionTitle = tvStyle(34f, 40f, -0.5f, FontWeight.SemiBold),
    cardTitle = tvStyle(28f, 32f, -0.3f, FontWeight.SemiBold),
    cardSubtitle = tvStyle(22f, 28f, 0f, FontWeight.Normal),
    body = tvStyle(24f, 32f, 0f, FontWeight.Normal),
    caption = tvStyle(20f, 24f, 0f, FontWeight.Normal),
    button = tvStyle(24f, 24f, 0f, FontWeight.SemiBold),
    tab = tvStyle(22f, 24f, -0.2f, FontWeight.SemiBold),
    npTitle = tvStyle(48f, 52f, -0.8f, FontWeight.Bold),
    npArtist = tvStyle(30f, 36f, 0f, FontWeight.Normal),
    lyricsActive = tvStyle(44f, 52f, -0.5f, FontWeight.Bold),
    lyricsInactive = tvStyle(36f, 44f, -0.3f, FontWeight.SemiBold),
)

val LocalTvTypography = staticCompositionLocalOf { defaultTvTypeScale() }
