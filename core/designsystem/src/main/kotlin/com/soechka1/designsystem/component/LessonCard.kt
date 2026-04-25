package com.soechka1.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
    title: String = "Vibe Coding",
    time: String = "00:00:00",
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography

    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            //TODO()
            .heightIn(min = 90.dp),
        backgroundColor = colors.accentBlue,
        contentPadding = PaddingValues(horizontal = spacing.medium, vertical = spacing.large),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.small),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(spacing.xSmall),
                modifier = Modifier.weight(1f),
            ) {
                Text(text = title, style = typography.title)
                Text(text = time, style = typography.timerValue)
            }
        }
    }
}

@Preview
@Composable
fun Preview_LessonCard() = LessonCard()
