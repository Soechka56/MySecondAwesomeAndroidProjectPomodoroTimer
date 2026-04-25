package com.soechka1.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    title: String = "Streak",
    value: String = "00:00:00",
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes

    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            //TODO()
            .heightIn(min = 140.dp),
        backgroundColor = colors.accentGreen,
        contentPadding = PaddingValues(horizontal = spacing.large, vertical = spacing.small),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.small),
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .border(3.dp, colors.border, shapes.circle),
                contentAlignment = Alignment.Center,
            ) {
                //TODO()
            }

            Text(text = title, style = typography.title, textAlign = TextAlign.Center)
            Text(text = value, style = typography.timerValue, textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun Preview_StatCard() = StatCard()
