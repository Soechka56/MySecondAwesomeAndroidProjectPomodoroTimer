package com.soechka1.designsystem.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import com.soechka1.designsystem.R

@Immutable
data class PomodoroShapeTokens(
    val card: Shape,
    val control: Shape,
    val circle: Shape,
)

@Composable
internal fun pomodoroShapes() = PomodoroShapeTokens(
    card = RoundedCornerShape(dimensionResource(R.dimen.pomodoro_shape_card)),
    control = RoundedCornerShape(dimensionResource(R.dimen.pomodoro_shape_control)),
    circle = CircleShape,
)

@Composable
internal fun materialShapes() = Shapes(
    small = RoundedCornerShape(dimensionResource(R.dimen.pomodoro_shape_material_small)),
    medium = RoundedCornerShape(dimensionResource(R.dimen.pomodoro_shape_material_medium)),
    large = RoundedCornerShape(dimensionResource(R.dimen.pomodoro_shape_material_large)),
)
