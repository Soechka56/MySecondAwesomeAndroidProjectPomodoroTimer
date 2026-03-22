package com.soechka1.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.target.DeviceTypeEnum
import com.soechka1.designsystem.target.getDeviceUiConfig
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun SimpleInfoCard(
    modifier: Modifier = Modifier,
    title: String = "Bagel Fat One",
    text: String = "Bagel Fat One #333333",
) {
    val ui = getDeviceUiConfig()
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography

    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 68.dp),
        backgroundColor = colors.accentGreen,
        contentPadding = PaddingValues(horizontal = spacing.large, vertical = spacing.large),
    ) {
        if (ui.type == DeviceTypeEnum.Compact) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.small),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = title, style = typography.title)
                Text(text = text, style = typography.body)
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = title, style = typography.title)
                Text(text = text, style = typography.body)
            }
        }
    }
}

@Preview
@Composable
fun Preview_SimpleInfoCard() = SimpleInfoCard()
