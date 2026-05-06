package com.soechka1.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.soechka1.designsystem.R


private val BagelFontFamily = FontFamily(
    Font(
        resId = R.font.bagelfont,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    )
)

private val YrsaFontFamily = FontFamily(
    Font(
        resId = R.font.yrsafont,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    )
)

private val AksharFontFamily = FontFamily(
    Font(
        resId = R.font.aksharfont,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    )
)

@Immutable
data class PomodoroTypographyTokens(
    val display: TextStyle,
    val title: TextStyle,
    val titleSmall: TextStyle,
    val body: TextStyle,
    val bodyLarge: TextStyle,
    val label: TextStyle,
    val timerValue: TextStyle,
)

@Composable
private fun textUnitResource(id: Int): TextUnit {
    val resources = LocalContext.current.resources
    val density = LocalDensity.current
    return with(density) {
        resources.getDimension(id).toSp()
    }
}

@Composable
internal fun pomodoroTypography(colors: PomodoroColorPalette) = PomodoroTypographyTokens(
    display = TextStyle(
        fontFamily = BagelFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textUnitResource(R.dimen.pomodoro_type_display_font_size),
        lineHeight = textUnitResource(R.dimen.pomodoro_type_display_line_height),
        color = colors.textPrimary,
    ),
    title = TextStyle(
        fontFamily = BagelFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textUnitResource(R.dimen.pomodoro_type_title_font_size),
        lineHeight = textUnitResource(R.dimen.pomodoro_type_title_line_height),
        color = colors.textPrimary,
    ),
    titleSmall = TextStyle(
        fontFamily = BagelFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textUnitResource(R.dimen.pomodoro_type_title_small_font_size),
        lineHeight = textUnitResource(R.dimen.pomodoro_type_title_small_line_height),
        color = colors.textPrimary,
    ),
    body = TextStyle(
        fontFamily = YrsaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textUnitResource(R.dimen.pomodoro_type_body_font_size),
        lineHeight = textUnitResource(R.dimen.pomodoro_type_body_line_height),
        color = colors.textSecondary,
    ),
    bodyLarge = TextStyle(
        fontFamily = YrsaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textUnitResource(R.dimen.pomodoro_type_body_large_font_size),
        lineHeight = textUnitResource(R.dimen.pomodoro_type_body_large_line_height),
        color = colors.textSecondary,
    ),
    label = TextStyle(
        fontFamily = AksharFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = textUnitResource(R.dimen.pomodoro_type_label_font_size),
        lineHeight = textUnitResource(R.dimen.pomodoro_type_label_line_height),
        color = colors.textPrimary,
    ),
    timerValue = TextStyle(
        fontFamily = YrsaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textUnitResource(R.dimen.pomodoro_type_timer_value_font_size),
        lineHeight = textUnitResource(R.dimen.pomodoro_type_timer_value_line_height),
        color = colors.textSecondary,
    ),
)

@Composable
internal fun materialTypography(colors: PomodoroColorPalette): Typography {
    val typography = pomodoroTypography(colors)
    return Typography(
        displayLarge = typography.display,
        titleLarge = typography.title,
        bodyLarge = typography.bodyLarge,
        bodyMedium = typography.body,
        labelLarge = typography.label,
    )
}
