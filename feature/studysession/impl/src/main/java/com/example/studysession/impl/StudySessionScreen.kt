package com.example.studysession.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.MySecondAwesomeAndroidProjectPomodoroTimerTheme
import com.soechka1.designsystem.theme.PomodoroTheme
import com.soechka1.designsystem.R as DesignSystemR

@Composable
fun StudySessionScreen(
    viewModel: StudySessionViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    StudySessionContent(
        state = state,
        modifier = modifier
    )
}

@Composable
private fun StudySessionContent(
    state: StudySessionState,
    modifier: Modifier = Modifier,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colors.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(colors.background)
                .padding(horizontal = spacing.xLarge, vertical = spacing.large),
        ) {
            TimerWidget(
                timeText = state.timerText,
                progress = state.timerProgress,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(spacing.xLarge))

            Text(
                text = "Study with friends!",
                style = typography.title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
                verticalArrangement = Arrangement.spacedBy(spacing.small),
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.friends) { friend ->
                    FriendCard(
                        friend = friend,
                        modifier = Modifier.height(130.dp) // Approximate height from design
                    )
                }
            }
        }
    }
}

@Composable
private fun TimerWidget(
    timeText: String,
    progress: Float,
    modifier: Modifier = Modifier
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes

    BaseCard(
        modifier = modifier,
        backgroundColor = colors.accentYellow,
        contentPadding = PaddingValues(spacing.large)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = timeText,
                style = typography.display.copy(fontSize = 40.sp, fontWeight = FontWeight.Black),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(spacing.medium))
            
            // Progress Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(shapes.control)
                    .background(colors.background)
                    .border(2.dp, colors.border, shapes.control),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress.coerceIn(0f, 1f))
                        .background(colors.textPrimary, shapes.control),
                )
            }
            
            Spacer(modifier = Modifier.height(spacing.medium))
            
            // Controls
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ControlButton(iconResId = DesignSystemR.drawable.ic_groups) // using ic_groups as placeholder for heart if missing
                ControlButton(iconResId = DesignSystemR.drawable.ic_play)
                ControlButton(iconResId = DesignSystemR.drawable.ic_leaders) // placeholder for sun/settings
            }
        }
    }
}

@Composable
private fun ControlButton(
    iconResId: Int,
    modifier: Modifier = Modifier
) {
    val colors = PomodoroTheme.colors
    val shapes = PomodoroTheme.shapes
    
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(shapes.circle)
            .background(colors.background)
            .border(2.dp, colors.border, shapes.circle),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = colors.textPrimary,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun FriendCard(
    friend: FriendStudyState,
    modifier: Modifier = Modifier
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography

    BaseCard(
        modifier = modifier,
        backgroundColor = friend.backgroundColor.toComposeColor(),
        contentPadding = PaddingValues(spacing.small)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Avatar Placeholder
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(colors.background)
                    .border(2.dp, colors.border, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = DesignSystemR.drawable.ic_groups),
                    contentDescription = null,
                    tint = colors.border,
                    modifier = Modifier.size(32.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(spacing.small))
            
            Text(
                text = friend.name, 
                style = typography.titleSmall,
                textAlign = TextAlign.Center
            )
            Text(
                text = friend.time, 
                style = typography.body,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun StudySessionColor.toComposeColor(): Color {
    val colors = PomodoroTheme.colors
    return when (this) {
        StudySessionColor.Green -> colors.accentGreen
        StudySessionColor.Orange -> colors.accentOrange
        StudySessionColor.Yellow -> colors.accentYellow
    }
}

@Preview(showBackground = true)
@Composable
private fun StudySessionScreenPreview() {
    MySecondAwesomeAndroidProjectPomodoroTimerTheme {
        StudySessionContent(
            state = StudySessionState()
        )
    }
}
