package com.soechka1.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun StatCardM(
    modifier: Modifier = Modifier,
    title: String = "Total time",
    subtitle: String = "last 5 days..",
    barHeights: List<Float> = listOf(0.3f, 0.5f, 0.8f, 0.4f, 0.6f, 0.3f, 0.7f, 0.9f),
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes

    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            //TODO()
            .heightIn(min = 256.dp),
        backgroundColor = colors.background,
        contentPadding = PaddingValues(horizontal = spacing.large, vertical = spacing.large),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(spacing.large),
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = title, style = typography.title)
                Box(
                    modifier = Modifier
                        //TODO()
                        .size(30.dp)
                        .background(colors.accentOrange, shapes.circle)
                        .border(2.dp, colors.border, shapes.circle),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = "★", style = typography.bodyLarge.copy(color = colors.textOnAccent))
                }
            }

            Text(
                text = subtitle,
                style = typography.titleSmall.copy(color = colors.accentOrange),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.xSmall),
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                barHeights.forEachIndexed { index, height ->
                    //TODO()
                    val barColor = if (index % 2 == 0) colors.accentGreen else colors.accentOrange
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(height)
                            .background(barColor, shapes.control)
                            .border(2.dp, colors.border, shapes.control),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview_StatCardM() = StatCardM()
