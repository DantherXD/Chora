package com.craftworks.music.ui.screens.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.craftworks.music.R
import com.craftworks.music.ui.theme.tv.LocalTvColors
import com.craftworks.music.ui.theme.tv.LocalTvShapes
import com.craftworks.music.ui.theme.tv.LocalTvTypography
import com.craftworks.music.ui.theme.tv.TvDimens
import com.craftworks.music.ui.tv.LocalTvTopInset

/**
 * P1 placeholder for Browse (spec §6.2): the route, the title and the empty state, so the tab is
 * not a dead end. Rails, genres and the configurable grid arrive in P2-B/P2-F.
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvBrowseScreen() {
    val colors = LocalTvColors.current
    val type = LocalTvTypography.current
    val shape = LocalTvShapes.current.panel

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = TvDimens.screenMarginHorizontal)
            .padding(
                top = LocalTvTopInset.current + TvDimens.screenMarginTop,
                bottom = TvDimens.screenMarginBottom,
            ),
        verticalArrangement = Arrangement.spacedBy(TvDimens.space32),
    ) {
        Text(
            text = stringResource(R.string.nav_browse),
            style = type.screenTitle,
            color = colors.labelPrimary,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(colors.surface1, shape)
                .padding(TvDimens.space48),
            verticalArrangement = Arrangement.spacedBy(TvDimens.space12),
        ) {
            Text(
                text = stringResource(R.string.tv_browse_empty_title),
                style = type.sectionTitle,
                color = colors.labelPrimary,
            )
            Text(
                text = stringResource(R.string.tv_browse_empty_body),
                style = type.body,
                color = colors.labelSecondary,
            )
        }
    }
}
