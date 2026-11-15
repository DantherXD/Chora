package com.craftworks.music.ui.screens.tv

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.craftworks.music.R
import com.craftworks.music.data.model.Screen
import com.craftworks.music.ui.theme.tv.LocalTvColors
import com.craftworks.music.ui.theme.tv.LocalTvShapes
import com.craftworks.music.ui.theme.tv.LocalTvTypography
import com.craftworks.music.ui.theme.tv.TvDimens
import com.craftworks.music.ui.tv.LocalTvTopInset
import com.craftworks.music.ui.tv.tvFocusScale

/**
 * P1 placeholder for the Library hub (spec §6.4). The four category tiles and the Settings row
 * are already the real information architecture; P2-D replaces the tiles with the final
 * surface treatment and adds the Recently Added rail.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvLibraryScreen(navHostController: NavHostController) {
    val colors = LocalTvColors.current
    val type = LocalTvTypography.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = TvDimens.screenMarginHorizontal)
            .padding(
                top = LocalTvTopInset.current + TvDimens.screenMarginTop,
                bottom = TvDimens.screenMarginBottom,
            ),
        verticalArrangement = Arrangement.spacedBy(TvDimens.space24),
    ) {
        Text(
            text = stringResource(R.string.nav_library),
            style = type.screenTitle,
            color = colors.labelPrimary,
        )

        Row(horizontalArrangement = Arrangement.spacedBy(TvDimens.space24)) {
            LibraryTile(
                modifier = Modifier.weight(1f),
                labelRes = R.string.nav_albums,
                onClick = { navHostController.navigate(Screen.Albums) { launchSingleTop = true } },
            )
            LibraryTile(
                modifier = Modifier.weight(1f),
                labelRes = R.string.nav_artists,
                onClick = { navHostController.navigate(Screen.Artists) { launchSingleTop = true } },
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(TvDimens.space24)) {
            LibraryTile(
                modifier = Modifier.weight(1f),
                labelRes = R.string.nav_songs,
                onClick = { navHostController.navigate(Screen.Songs) { launchSingleTop = true } },
            )
            LibraryTile(
                modifier = Modifier.weight(1f),
                labelRes = R.string.nav_playlists,
                onClick = {
                    navHostController.navigate(Screen.Playlists) { launchSingleTop = true }
                },
            )
        }

        Spacer(Modifier.height(TvDimens.space24))

        // Navigate the settings *graph* (its start destination is SettingsList), the same way
        // the old drawer did, so the graph lands on the back stack with its own start entry.
        SettingsRow(
            onClick = {
                navHostController.navigate(Screen.Settings) { launchSingleTop = true }
            }
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun LibraryTile(
    @StringRes labelRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalTvColors.current
    val type = LocalTvTypography.current
    val shape = LocalTvShapes.current.sheet

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Box(
        modifier = modifier
            .height(180.dp)
            .tvFocusScale(interactionSource, focusedScale = TvDimens.focusScaleTile)
            .clip(shape)
            .background(
                color = if (isFocused) colors.surface2 else colors.surface1,
                shape = shape,
            )
            .then(
                if (isFocused) {
                    Modifier.border(TvDimens.focusRingWidth / 2, colors.focusRing, shape)
                } else {
                    Modifier
                }
            )
            .clickable(interactionSource = interactionSource, indication = null, onClick = onClick)
            .padding(TvDimens.space32),
        contentAlignment = Alignment.BottomStart,
    ) {
        Text(
            text = stringResource(labelRes),
            style = type.sectionTitle,
            color = colors.labelPrimary,
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun SettingsRow(onClick: () -> Unit) {
    val colors = LocalTvColors.current
    val type = LocalTvTypography.current
    val shape = LocalTvShapes.current.row

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .tvFocusScale(interactionSource, focusedScale = TvDimens.focusScaleButton)
            .clip(shape)
            .background(
                color = if (isFocused) colors.surface2 else colors.surface1,
                shape = shape,
            )
            .clickable(interactionSource = interactionSource, indication = null, onClick = onClick)
            .padding(horizontal = TvDimens.space32, vertical = TvDimens.space24),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(TvDimens.space16),
    ) {
        androidx.tv.material3.Icon(
            imageVector = ImageVector.vectorResource(R.drawable.rounded_settings_24),
            contentDescription = null,
            tint = colors.labelSecondary,
            modifier = Modifier.size(28.dp),
        )
        Text(
            text = stringResource(R.string.home_settings),
            style = type.body,
            color = colors.labelPrimary,
        )
    }
}
