package com.soechka1.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.soechka1.designsystem.R

@Immutable
data class PomodoroSizes(
    val cardS: DpSize,
    val cardM: DpSize,
    val cardXL: DpSize,

    val avatar: Dp,
    val iconS: Dp,
    val iconM: Dp,
    val iconL: Dp,

    val minTouchTarget: Dp,

    )

val SizesCompact: PomodoroSizes
    @Composable
    get() = PomodoroSizes(
    cardS = DpSize(
        dimensionResource(R.dimen.pomodoro_size_card_s_width),
        dimensionResource(R.dimen.pomodoro_size_card_s_height),
    ),
    cardM = DpSize(
        dimensionResource(R.dimen.pomodoro_size_card_m_compact_width),
        dimensionResource(R.dimen.pomodoro_size_card_m_compact_height),
    ),
    cardXL = DpSize(
        dimensionResource(R.dimen.pomodoro_size_card_xl_compact_width),
        dimensionResource(R.dimen.pomodoro_size_card_xl_compact_height),
    ),

    avatar = dimensionResource(R.dimen.pomodoro_size_avatar),
    iconS = dimensionResource(R.dimen.pomodoro_size_icon_s),
    iconM = dimensionResource(R.dimen.pomodoro_size_icon_m),
    iconL = dimensionResource(R.dimen.pomodoro_size_icon_l),

    minTouchTarget = dimensionResource(R.dimen.pomodoro_size_min_touch_target)
)

val SizesMedium: PomodoroSizes
    @Composable
    get() = SizesCompact.copy(
        cardM = DpSize(
            dimensionResource(R.dimen.pomodoro_size_card_m_medium_width),
            dimensionResource(R.dimen.pomodoro_size_card_m_medium_height),
        ),
        cardXL = DpSize(
            dimensionResource(R.dimen.pomodoro_size_card_xl_medium_width),
            dimensionResource(R.dimen.pomodoro_size_card_xl_medium_height),
        ),
    )

val SizesExpanded: PomodoroSizes
    @Composable
    get() = SizesCompact.copy(
        cardM = DpSize(
            dimensionResource(R.dimen.pomodoro_size_card_m_expanded_width),
            dimensionResource(R.dimen.pomodoro_size_card_m_expanded_height),
        ),
        cardXL = DpSize(
            dimensionResource(R.dimen.pomodoro_size_card_xl_expanded_width),
            dimensionResource(R.dimen.pomodoro_size_card_xl_expanded_height),
        ),
    )
