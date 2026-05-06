package com.soechka1.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.soechka1.designsystem.R

@Immutable
data class PomodoroSpacingTokens(
    val xSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val xLarge: Dp,
    val xxLarge: Dp,
)

@Composable
internal fun defaultPomodoroSpacing() = PomodoroSpacingTokens(
    xSmall = dimensionResource(R.dimen.pomodoro_spacing_x_small),
    small = dimensionResource(R.dimen.pomodoro_spacing_small),
    medium = dimensionResource(R.dimen.pomodoro_spacing_medium),
    large = dimensionResource(R.dimen.pomodoro_spacing_large),
    xLarge = dimensionResource(R.dimen.pomodoro_spacing_x_large),
    xxLarge = dimensionResource(R.dimen.pomodoro_spacing_xx_large),
)
