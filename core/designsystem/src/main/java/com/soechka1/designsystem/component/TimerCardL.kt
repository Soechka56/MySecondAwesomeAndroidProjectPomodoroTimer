package com.soechka1.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun TimerCardL(
    modifier: Modifier = Modifier,
    title: String = "F6D361",
    timer: String = "2080",
    progress: Float = 0.7f,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes

    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            //TODO()
            .heightIn(min = 80.dp),
        backgroundColor = colors.accentYellow,
        contentPadding = PaddingValues(horizontal = spacing.medium, vertical = spacing.medium),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.small),
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(text = title, style = typography.display)
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.large),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        //TODO()
                        .height(24.dp)
                        .background(colors.background, shapes.control)
                        .border(2.dp, colors.border, shapes.control),
                ) {
                    Box(){
                        // TODO
                    }
                }
                Text(text = timer, style = typography.title)
            }
        }
    }
}

@Preview
@Composable
fun Preview_TimerCardL() = TimerCardL()
