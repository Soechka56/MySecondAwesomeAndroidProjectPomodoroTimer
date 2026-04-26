package com.soechka1.designsystem.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    name: String = "Bagel Fat One",
    time: String = "00:00:00",
) {
    val colors = PomodoroTheme.colors
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes

    BaseCard(
        modifier = modifier
            .fillMaxWidth()
            //TODO()
            .heightIn(max = 250.dp),
        backgroundColor = colors.accentGreen,
        //TODO()
        contentPadding = PaddingValues(12.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    //TODO()
                    .size(68.dp)
                    .background(colors.surface, shapes.circle)
                    .border(2.dp, colors.border, shapes.circle),
            )

            Text(
                text = name,
                style = typography.title,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )

            Text(text = time, style = typography.timerValue, textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun Preview_UserCard() = UserCard()
