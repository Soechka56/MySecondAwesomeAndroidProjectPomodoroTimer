package com.soechka1.designsystem.component

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun TimerCardS(
    modifier: Modifier = Modifier,
    title: String = "Streak",
    progress: Float = 0.6f,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes

    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 160.dp),
        backgroundColor = colors.accentYellow,
        contentPadding = PaddingValues(spacing.medium),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.xLarge),
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(text = title, style = typography.display, textAlign = TextAlign.Center)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    //TODO()
                    .height(30.dp)
                    .background(colors.background, shapes.control)
                    .border(2.dp, colors.border, shapes.control),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawBehind {
                            val width = size.width * progress
                            //TODO()
                        },
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.xxLarge),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(31.dp)
                            .background(colors.background, shapes.circle)
                            .border(2.dp, colors.border, shapes.circle),
                        contentAlignment = Alignment.Center,
                    ) {
                        //TODO()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview_TimerCardS() = TimerCardS()
