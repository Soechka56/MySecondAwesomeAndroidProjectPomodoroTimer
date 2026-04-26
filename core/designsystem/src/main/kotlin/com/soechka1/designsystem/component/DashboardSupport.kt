package com.soechka1.designsystem.component

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.cos
import kotlin.math.sin

data class CalendarDay(
    val dayName: String,
    val time: String,
    val colorVariant: Int,
)

@Deprecated("ONLY FOR TEST")
fun generateSampleCalendarDays(): List<CalendarDay> =
    List(30) { i ->
        CalendarDay(
            dayName = "%02d".format((i % 31) + 1),
            time = "00:00:00",
            colorVariant = i % 5,
        )
    }

