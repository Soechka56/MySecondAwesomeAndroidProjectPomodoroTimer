package com.soechka1.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soechka1.designsystem.autolayout.CustomStaggeredGrid
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun CalendarCard(
    modifier: Modifier = Modifier,
    title: String = "example",
    days: List<CalendarDay> = generateSampleCalendarDays(),
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes

    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            //TODO()
            .heightIn(min = 400.dp),
        backgroundColor = colors.accentBlue,
        contentPadding = PaddingValues(spacing.small),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.medium),
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                //TODO()
                modifier = Modifier.height(50.dp),
            ) {
                Text(text = title, style = typography.title)
            }

            CustomStaggeredGrid(
                items = days.map { day ->
                    @Composable {
                        DayCell(day = day)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                columnCount = 5,
                horizontalSpacing = spacing.xSmall,
                verticalSpacing = spacing.xSmall,
            )
        }
    }
}

@Composable
private fun DayCell(day: CalendarDay) {
    val colors = PomodoroTheme.colors
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes
    val dayColor = when (day.colorVariant) {
        0 -> colors.accentYellow
        1 -> colors.accentGreen
        2 -> colors.accentOrange
        3 -> colors.surface
        else -> colors.background
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 54.dp)
            .background(dayColor, shapes.control)
            .border(2.dp, colors.border, shapes.control),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = day.dayName, style = typography.label)
            Text(text = day.time, style = typography.body, textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun Preview_CalendarCard() = CalendarCard()
