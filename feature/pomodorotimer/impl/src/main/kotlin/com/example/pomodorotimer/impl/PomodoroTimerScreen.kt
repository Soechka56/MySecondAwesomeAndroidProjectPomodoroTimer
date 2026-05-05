package com.example.pomodorotimer.impl

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.MySecondAwesomeAndroidProjectPomodoroTimerTheme
import com.soechka1.designsystem.theme.PomodoroTheme

private data class SubjectUiModel(
    val title: String,
    val icon: SubjectIcon,
    val color: Color,
)

private enum class SubjectIcon {
    Trend,
    Spark,
    Play,
    Dot,
}

@Composable
fun PomodorotimerScreen(
    modifier: Modifier = Modifier,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val scrollState = rememberScrollState()
    var isAddSubjectDialogShown by remember { mutableStateOf(false) }
    val subjectIcons = remember {
        listOf(SubjectIcon.Trend, SubjectIcon.Spark, SubjectIcon.Play, SubjectIcon.Dot)
    }
    val subjectColors = listOf(
        colors.accentBlue,
        colors.accentGreen,
        colors.accentYellow,
        colors.accentOrange,
        Color(0xFFBDA0F6),
    )
    val addedSubjects = remember { mutableStateListOf<SubjectUiModel>() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colors.background,
        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = {
        },
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(colors.background)
                .verticalScroll(scrollState)
                .padding(horizontal = spacing.xLarge, vertical = spacing.large),
        ) {
            Text(
                text = "Good morning!",
                style = typography.display,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(spacing.xLarge))

            Text(
                text = "Pick a subject!",
                style = typography.title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            SubjectCard(
                title = "Vibe Coding",
                time = "00:00:00",
                backgroundColor = colors.accentBlue,
                icon = SubjectIcon.Trend,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            )

            Spacer(modifier = Modifier.height(spacing.small))

            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
                modifier = Modifier.fillMaxWidth(),
            ) {
                SubjectCard(
                    title = "Math",
                    time = "00:00:00",
                    backgroundColor = Color(0xFFBDA0F6),
                    icon = SubjectIcon.Trend,
                    showChart = true,
                    modifier = Modifier
                        .weight(1f)
                        .height(92.dp),
                )
                SubjectCard(
                    title = "Math",
                    time = "00:00:00",
                    backgroundColor = colors.accentGreen,
                    icon = SubjectIcon.Trend,
                    showChart = true,
                    modifier = Modifier
                        .weight(1f)
                        .height(92.dp),
                )
            }

            Spacer(modifier = Modifier.height(spacing.small))

            SubjectCard(
                title = "Vibe Coding",
                time = "00:00:00",
                backgroundColor = colors.accentOrange,
                icon = SubjectIcon.Spark,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            )

            Spacer(modifier = Modifier.height(spacing.small))

            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
                modifier = Modifier.fillMaxWidth(),
            ) {
                SubjectCard(
                    title = "Math",
                    time = "00:00:00",
                    backgroundColor = colors.accentGreen,
                    icon = SubjectIcon.Trend,
                    showChart = true,
                    modifier = Modifier
                        .weight(1f)
                        .height(92.dp),
                )
                SubjectCard(
                    title = "Math",
                    time = "00:00:00",
                    backgroundColor = colors.accentYellow,
                    icon = SubjectIcon.Trend,
                    showChart = true,
                    modifier = Modifier
                        .weight(1f)
                        .height(92.dp),
                )
            }

            addedSubjects.chunked(2).forEach { rowSubjects ->
                Spacer(modifier = Modifier.height(spacing.small))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    rowSubjects.forEach { subject ->
                        SubjectCard(
                            title = subject.title,
                            time = "00:00:00",
                            backgroundColor = subject.color,
                            icon = subject.icon,
                            showChart = true,
                            modifier = Modifier
                                .weight(1f)
                                .height(92.dp),
                        )
                    }

                    if (rowSubjects.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing.small))

            TodayGoalCard(
                progress = 0.38f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
            )

            Spacer(modifier = Modifier.height(spacing.small))

            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StartStudyButton(
                    modifier = Modifier
                        .widthIn(min = 148.dp)
                        .height(38.dp),
                )

                AddSubjectButton(
                    onClick = { isAddSubjectDialogShown = true },
                    modifier = Modifier.size(38.dp),
                )
            }
        }
    }

    if (isAddSubjectDialogShown) {
        AddSubjectDialog(
            icons = subjectIcons,
            onDismiss = { isAddSubjectDialogShown = false },
            onAddSubject = { title, iconIndex ->
                addedSubjects += SubjectUiModel(
                    title = title,
                    icon = subjectIcons[iconIndex],
                    color = subjectColors[addedSubjects.size % subjectColors.size],
                )
                isAddSubjectDialogShown = false
            },
        )
    }
}

@Composable
private fun SubjectCard(
    title: String,
    time: String,
    backgroundColor: Color,
    icon: SubjectIcon,
    modifier: Modifier = Modifier,
    showChart: Boolean = false,
) {
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography

    BaseCard(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentPadding = PaddingValues(horizontal = spacing.medium, vertical = spacing.small),
    ) {
        if (showChart) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                SubjectIconView(icon = icon, modifier = Modifier.size(46.dp))
                Spacer(modifier = Modifier.height(spacing.small))
                Text(text = title, style = typography.titleSmall, textAlign = TextAlign.Center)
                Text(text = time, style = typography.body, textAlign = TextAlign.Center)
            }
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(text = title, style = typography.titleSmall)
                Text(text = time, style = typography.body)
            }
        }
    }
}

@Composable
private fun TodayGoalCard(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes

    BaseCard(
        modifier = modifier,
        backgroundColor = colors.accentYellow,
        contentPadding = PaddingValues(horizontal = spacing.large, vertical = spacing.xSmall),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(text = "Today's goal", style = typography.titleSmall)
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(16.dp)
                        .clip(shapes.control)
                        .background(colors.background)
                        .border(2.dp, colors.border, shapes.control),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(progress.coerceIn(0f, 1f))
                            .background(colors.border, shapes.control),
                    )
                }

                Text(text = "3/5h", style = typography.titleSmall)
            }
        }
    }
}

@Composable
private fun StartStudyButton(
    modifier: Modifier = Modifier,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(shapes.control)
            .background(colors.accentGreen, shapes.control)
            .border(2.dp, colors.border, shapes.control)
            .padding(horizontal = spacing.large),
    ) {
        Text(text = "Start Study", style = typography.titleSmall)
        Spacer(modifier = Modifier.width(spacing.small))
        PlayIcon(modifier = Modifier.size(14.dp), color = colors.border)
    }
}

@Composable
private fun AddSubjectButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = PomodoroTheme.colors
    val shapes = PomodoroTheme.shapes

    Box(
        modifier = modifier
            .clip(shapes.circle)
            .background(colors.accentYellow, shapes.circle)
            .border(2.dp, colors.border, shapes.circle)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        PlusIcon(modifier = Modifier.size(18.dp), color = colors.border)
    }
}

@Composable
private fun AddSubjectDialog(
    icons: List<SubjectIcon>,
    onDismiss: () -> Unit,
    onAddSubject: (String, Int) -> Unit,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes
    var subjectName by remember { mutableStateOf("") }
    var selectedIconIndex by remember { mutableIntStateOf(0) }
    val trimmedName = subjectName.trim()

    Dialog(onDismissRequest = onDismiss) {
        BaseCard(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = colors.background,
            contentPadding = PaddingValues(spacing.large),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.medium),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Add subject", style = typography.title)

                BasicTextField(
                    value = subjectName,
                    onValueChange = { subjectName = it },
                    singleLine = true,
                    textStyle = typography.titleSmall.copy(color = colors.textPrimary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(shapes.control)
                        .background(colors.surface, shapes.control)
                        .border(2.dp, colors.border, shapes.control)
                        .padding(horizontal = spacing.medium, vertical = spacing.medium),
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            if (subjectName.isBlank()) {
                                Text(
                                    text = "Subject name",
                                    style = typography.titleSmall.copy(color = colors.textSecondary),
                                )
                            }
                            innerTextField()
                        }
                    },
                )

                Text(text = "Choose icon", style = typography.titleSmall)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    icons.forEachIndexed { index, icon ->
                        SubjectIconChoice(
                            icon = icon,
                            selected = selectedIconIndex == index,
                            onClick = { selectedIconIndex = index },
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp),
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    DialogActionButton(
                        text = "Cancel",
                        backgroundColor = colors.surface,
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(38.dp),
                    )
                    DialogActionButton(
                        text = "Add",
                        backgroundColor = colors.accentGreen,
                        enabled = trimmedName.isNotBlank(),
                        onClick = { onAddSubject(trimmedName, selectedIconIndex) },
                        modifier = Modifier
                            .weight(1f)
                            .height(38.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun SubjectIconChoice(
    icon: SubjectIcon,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = PomodoroTheme.colors
    val shapes = PomodoroTheme.shapes
    val backgroundColor = if (selected) colors.accentBlue else colors.surface

    Box(
        modifier = modifier
            .clip(shapes.control)
            .background(backgroundColor, shapes.control)
            .border(2.dp, colors.border, shapes.control)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        SubjectIconView(icon = icon, modifier = Modifier.size(26.dp))
    }
}

@Composable
private fun DialogActionButton(
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes
    val resolvedBackground = if (enabled) backgroundColor else colors.surface
    val textColor = if (enabled) colors.textPrimary else colors.textSecondary

    Box(
        modifier = modifier
            .clip(shapes.control)
            .background(resolvedBackground, shapes.control)
            .border(2.dp, colors.border, shapes.control)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = spacing.medium),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text, style = typography.titleSmall.copy(color = textColor))
    }
}



@Composable
private fun SubjectIconView(
    icon: SubjectIcon,
    modifier: Modifier = Modifier,
) {
    val colors = PomodoroTheme.colors

    when (icon) {
        SubjectIcon.Trend -> TrendIcon(modifier = modifier, color = colors.border)
        SubjectIcon.Spark -> SparkIcon(modifier = modifier, color = colors.border)
        SubjectIcon.Play -> PlayIcon(modifier = modifier, color = colors.border)
        SubjectIcon.Dot -> Box(
            modifier = modifier
                .padding(8.dp)
                .clip(PomodoroTheme.shapes.circle)
                .background(colors.border),
        )
    }
}

@Composable
private fun TrendIcon(
    modifier: Modifier = Modifier,
    color: Color = PomodoroTheme.colors.border,
) {
    Canvas(modifier = modifier) {
        val strokeWidth = 2.4.dp.toPx()
        val path = Path().apply {
            moveTo(size.width * 0.12f, size.height * 0.72f)
            lineTo(size.width * 0.42f, size.height * 0.42f)
            lineTo(size.width * 0.58f, size.height * 0.58f)
            lineTo(size.width * 0.86f, size.height * 0.30f)
        }
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.86f, size.height * 0.30f),
            end = Offset(size.width * 0.86f, size.height * 0.52f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.86f, size.height * 0.30f),
            end = Offset(size.width * 0.64f, size.height * 0.30f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun PlusIcon(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Canvas(modifier = modifier) {
        val strokeWidth = 2.4.dp.toPx()
        drawLine(
            color = color,
            start = Offset(size.width * 0.50f, size.height * 0.12f),
            end = Offset(size.width * 0.50f, size.height * 0.88f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.12f, size.height * 0.50f),
            end = Offset(size.width * 0.88f, size.height * 0.50f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun PlayIcon(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width * 0.25f, size.height * 0.12f)
            lineTo(size.width * 0.25f, size.height * 0.88f)
            lineTo(size.width * 0.86f, size.height * 0.50f)
            close()
        }
        drawPath(path = path, color = color)
    }
}

@Composable
private fun SparkIcon(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Canvas(modifier = modifier) {
        val strokeWidth = 1.8.dp.toPx()
        drawLine(
            color = color,
            start = Offset(size.width * 0.50f, 0f),
            end = Offset(size.width * 0.50f, size.height),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(0f, size.height * 0.50f),
            end = Offset(size.width, size.height * 0.50f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.18f, size.height * 0.18f),
            end = Offset(size.width * 0.82f, size.height * 0.82f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.82f, size.height * 0.18f),
            end = Offset(size.width * 0.18f, size.height * 0.82f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PomodorotimerScreenPreview() {
    MySecondAwesomeAndroidProjectPomodoroTimerTheme {
        PomodorotimerScreen()
    }
}
