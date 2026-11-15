package com.craftworks.music.ui.tv

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.craftworks.music.ui.theme.tv.LocalReduceMotion
import com.craftworks.music.ui.theme.tv.LocalTvMotion
import com.craftworks.music.ui.theme.tv.TvDimens

/**
 * Distance from the top of the screen to where screen content may start: the tab bar's
 * height plus its gap (spec §4.4/§5.3).
 *
 * Screens add this to their own top padding. The shell draws content edge-to-edge underneath
 * the bar so the glass has something to sample while scrolling; until a screen adopts the
 * inset, its first row sits behind the bar.
 */
val LocalTvTopInset = compositionLocalOf { TvDimens.tabBarHeight + TvDimens.tabBarTopInset }

/**
 * True while Search is the active tab, so `TvSearchScreen` can put the cursor in the field on
 * arrival (spec §5.1). Consumed by the search screen in P2; provided here so the intent is
 * carried by the shell rather than by a click handler on the screen.
 */
val LocalTvFocusSearchOnEntry = compositionLocalOf { false }

/**
 * The TV focus language for elements that are not tv-material3 surfaces (spec §4.6):
 * scale only, with the 180 ms in / 140 ms out springs, snapped under reduce-motion.
 *
 * tv-material3 components animate their own focus scale, so new code attaches this to a raw
 * `clickable` instead — never both, or the two scales multiply.
 */
@Composable
fun Modifier.tvFocusScale(
    interactionSource: MutableInteractionSource,
    focusedScale: Float = TvDimens.focusScaleTab,
    unfocusedScale: Float = 1f,
): Modifier {
    val isFocused by interactionSource.collectIsFocusedAsState()
    val motion = LocalTvMotion.current
    val reduceMotion = LocalReduceMotion.current

    val spec: FiniteAnimationSpec<Float> = when {
        reduceMotion -> snap()
        isFocused -> motion.focusIn
        else -> motion.focusOut
    }

    val scale by animateFloatAsState(
        targetValue = if (isFocused) focusedScale else unfocusedScale,
        animationSpec = spec,
        label = "tvFocusScale",
    )

    return graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}
