package com.soechka1.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.target.DeviceTypeEnum
import com.soechka1.designsystem.target.getDeviceUiConfig
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    title: String = "POMODORO",
    pomodoroCount: Int = 5,
) {
    val ui = getDeviceUiConfig()
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography

    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            //TODO()
            .heightIn(min = 90.dp),
        backgroundColor = colors.accentPink,
        contentPadding = PaddingValues(horizontal = spacing.medium, vertical = spacing.large),
    ) {
        if (ui.type == DeviceTypeEnum.Compact) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.medium),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = title, style = typography.title)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.xSmall),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    repeat(pomodoroCount) {
                        PomodoroIcon()
                    }
                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = title, style = typography.title)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.xSmall),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    repeat(pomodoroCount) {
                        PomodoroIcon()
                    }
                }
            }
        }
    }
}

@Composable
private fun PomodoroIcon() {
    Box(){
        //TODO()
    }

}

@Preview
@Composable
fun Preview_InfoCard() = InfoCard()
