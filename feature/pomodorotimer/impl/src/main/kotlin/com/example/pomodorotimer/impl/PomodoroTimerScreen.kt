package com.example.pomodorotimer.impl

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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pomodorotimer.impl.model.PomodoroSubjectUiModel
import com.example.pomodorotimer.impl.model.PomodoroTimerEvent
import com.example.pomodorotimer.impl.model.PomodoroTimerState
import com.example.pomodorotimer.impl.model.SubjectColor
import com.example.pomodorotimer.impl.model.SubjectIcon
import com.soechka1.designsystem.component.shared.BaseCard
import com.soechka1.designsystem.theme.MySecondAwesomeAndroidProjectPomodoroTimerTheme
import com.soechka1.designsystem.theme.PomodoroTheme
import com.soechka1.designsystem.R as DesignSystemR

@Composable
fun PomodoroTimerScreen(
    viewModel: PomodoroTimerViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                PomodoroTimerEvent.StartStudyRequested -> Unit
            }
        }
    }

    PomodoroTimerContent(
        state = uiState,
        onShowAddSubject = viewModel::showAddSubjectDialog,
        onDismissAddSubject = viewModel::dismissAddSubjectDialog,
        onNewSubjectNameChange = viewModel::updateNewSubjectName,
        onIconSelected = viewModel::selectIcon,
        onAddSubject = viewModel::addSubject,
        onStartStudy = viewModel::startStudy,
        modifier = modifier,
    )
}

@Composable
private fun PomodoroTimerContent(
    state: PomodoroTimerState,
    onShowAddSubject: () -> Unit,
    onDismissAddSubject: () -> Unit,
    onNewSubjectNameChange: (String) -> Unit,
    onIconSelected: (SubjectIcon) -> Unit,
    onAddSubject: () -> Unit,
    onStartStudy: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = colors.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {},
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
                text = stringResource(R.string.pomodoro_greeting_good_morning),
                style = typography.display,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(spacing.xLarge))

            Text(
                text = stringResource(R.string.pomodoro_pick_subject),
                style = typography.title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            SubjectGrid(subjects = state.subjects)

            Spacer(modifier = Modifier.height(spacing.small))

            TodayGoalCard(
                progress = state.goalProgress,
                goalText = state.goalText,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_goal_card_height)),
            )

            Spacer(modifier = Modifier.height(spacing.small))

            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StartStudyButton(
                    onClick = onStartStudy,
                    modifier = Modifier
                        .widthIn(min = dimensionResource(DesignSystemR.dimen.pomodoro_timer_start_button_min_width))
                        .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_control_height)),
                )

                AddSubjectButton(
                    onClick = onShowAddSubject,
                    modifier = Modifier.size(dimensionResource(DesignSystemR.dimen.pomodoro_timer_add_button_size)),
                )
            }
        }
    }

    if (state.isAddSubjectDialogShown) {
        AddSubjectDialog(
            subjectName = state.newSubjectName,
            selectedIcon = state.selectedIcon,
            onSubjectNameChange = onNewSubjectNameChange,
            onIconSelected = onIconSelected,
            onDismiss = onDismissAddSubject,
            onAddSubject = onAddSubject,
        )
    }
}

@Composable
private fun SubjectGrid(
    subjects: List<PomodoroSubjectUiModel>,
) {
    val spacing = PomodoroTheme.spacing
    var index = 0

    while (index < subjects.size) {
        val subject = subjects[index]

        if (index > 0) {
            Spacer(modifier = Modifier.height(spacing.small))
        }

        if (subject.isWide) {
            SubjectCard(
                subject = subject,
                showChart = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_subject_wide_height)),
            )
            index += 1
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
                modifier = Modifier.fillMaxWidth(),
            ) {
                SubjectCard(
                    subject = subject,
                    showChart = true,
                    modifier = Modifier
                        .weight(1f)
                        .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_subject_card_height)),
                )

                val nextSubject = subjects.getOrNull(index + 1)
                if (nextSubject != null && !nextSubject.isWide) {
                    SubjectCard(
                        subject = nextSubject,
                        showChart = true,
                        modifier = Modifier
                            .weight(1f)
                            .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_subject_card_height)),
                    )
                    index += 2
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                    index += 1
                }
            }
        }
    }
}

@Composable
private fun SubjectCard(
    subject: PomodoroSubjectUiModel,
    modifier: Modifier = Modifier,
    showChart: Boolean = false,
) {
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography

    BaseCard(
        modifier = modifier,
        backgroundColor = subject.color.toComposeColor(),
        contentPadding = PaddingValues(horizontal = spacing.medium, vertical = spacing.small),
    ) {
        if (showChart) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                SubjectIconView(
                    icon = subject.icon,
                    modifier = Modifier.size(dimensionResource(DesignSystemR.dimen.pomodoro_timer_subject_icon_size)),
                )
                Spacer(modifier = Modifier.height(spacing.small))
                Text(text = subject.title, style = typography.titleSmall, textAlign = TextAlign.Center)
                Text(text = subject.time, style = typography.body, textAlign = TextAlign.Center)
            }
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(text = subject.title, style = typography.titleSmall)
                Text(text = subject.time, style = typography.body)
            }
        }
    }
}

@Composable
private fun TodayGoalCard(
    progress: Float,
    goalText: String,
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
            Text(text = stringResource(R.string.pomodoro_todays_goal), style = typography.titleSmall)
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_goal_progress_height))
                        .clip(shapes.control)
                        .background(colors.background)
                        .border(dimensionResource(DesignSystemR.dimen.pomodoro_timer_border_width), colors.border, shapes.control),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(progress.coerceIn(0f, 1f))
                            .background(colors.border, shapes.control),
                    )
                }

                Text(text = goalText, style = typography.titleSmall)
            }
        }
    }
}

@Composable
private fun StartStudyButton(
    onClick: () -> Unit,
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
            .border(dimensionResource(DesignSystemR.dimen.pomodoro_timer_border_width), colors.border, shapes.control)
            .clickable(onClick = onClick)
            .padding(horizontal = spacing.large),
    ) {
        Text(text = stringResource(R.string.pomodoro_start_study), style = typography.titleSmall)
        Spacer(modifier = Modifier.width(spacing.small))
        VectorAssetIcon(
            iconResId = R.drawable.ic_play_arrow_24,
            modifier = Modifier.size(dimensionResource(DesignSystemR.dimen.pomodoro_timer_icon_small)),
            tint = colors.border,
        )
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
            .border(dimensionResource(DesignSystemR.dimen.pomodoro_timer_border_width), colors.border, shapes.circle)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        VectorAssetIcon(
            iconResId = R.drawable.ic_add_24,
            modifier = Modifier.size(dimensionResource(DesignSystemR.dimen.pomodoro_timer_icon_medium)),
            tint = colors.border,
        )
    }
}

@Composable
private fun AddSubjectDialog(
    subjectName: String,
    selectedIcon: SubjectIcon,
    onSubjectNameChange: (String) -> Unit,
    onIconSelected: (SubjectIcon) -> Unit,
    onDismiss: () -> Unit,
    onAddSubject: () -> Unit,
) {
    val colors = PomodoroTheme.colors
    val spacing = PomodoroTheme.spacing
    val typography = PomodoroTheme.typography
    val shapes = PomodoroTheme.shapes
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
                Text(text = stringResource(R.string.pomodoro_add_subject_title), style = typography.title)

                BasicTextField(
                    value = subjectName,
                    onValueChange = onSubjectNameChange,
                    singleLine = true,
                    textStyle = typography.titleSmall.copy(color = colors.textPrimary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_text_field_height))
                        .clip(shapes.control)
                        .background(colors.surface, shapes.control)
                        .border(dimensionResource(DesignSystemR.dimen.pomodoro_timer_border_width), colors.border, shapes.control)
                        .padding(horizontal = spacing.medium, vertical = spacing.medium),
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            if (subjectName.isBlank()) {
                                Text(
                                    text = stringResource(R.string.pomodoro_subject_name_hint),
                                    style = typography.titleSmall.copy(color = colors.textSecondary),
                                )
                            }
                            innerTextField()
                        }
                    },
                )

                Text(text = stringResource(R.string.pomodoro_choose_icon), style = typography.titleSmall)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    SubjectIcon.entries.forEach { icon ->
                        SubjectIconChoice(
                            icon = icon,
                            selected = selectedIcon == icon,
                            onClick = { onIconSelected(icon) },
                            modifier = Modifier
                                .weight(1f)
                                .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_icon_choice_height)),
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.small),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    DialogActionButton(
                        text = stringResource(R.string.pomodoro_cancel),
                        backgroundColor = colors.surface,
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_control_height)),
                    )
                    DialogActionButton(
                        text = stringResource(R.string.pomodoro_add),
                        backgroundColor = colors.accentGreen,
                        enabled = trimmedName.isNotBlank(),
                        onClick = onAddSubject,
                        modifier = Modifier
                            .weight(1f)
                            .height(dimensionResource(DesignSystemR.dimen.pomodoro_timer_control_height)),
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
            .border(dimensionResource(DesignSystemR.dimen.pomodoro_timer_border_width), colors.border, shapes.control)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        SubjectIconView(
            icon = icon,
            modifier = Modifier.size(dimensionResource(DesignSystemR.dimen.pomodoro_timer_icon_choice_size)),
        )
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
            .border(dimensionResource(DesignSystemR.dimen.pomodoro_timer_border_width), colors.border, shapes.control)
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
        SubjectIcon.Trend -> VectorAssetIcon(
            iconResId = R.drawable.ic_show_chart_24,
            modifier = modifier,
            tint = colors.border,
        )
        SubjectIcon.Spark -> VectorAssetIcon(
            iconResId = R.drawable.ic_asterisk_24,
            modifier = modifier,
            tint = colors.border,
        )
        SubjectIcon.Play -> VectorAssetIcon(
            iconResId = R.drawable.ic_play_arrow_24,
            modifier = modifier,
            tint = colors.border,
        )
        SubjectIcon.Dot -> VectorAssetIcon(
            iconResId = R.drawable.ic_target_24,
            modifier = modifier,
            tint = colors.border,
        )
    }
}

@Composable
private fun VectorAssetIcon(
    iconResId: Int,
    modifier: Modifier = Modifier,
    tint: Color = PomodoroTheme.colors.border,
) {
    Icon(
        painter = painterResource(id = iconResId),
        contentDescription = null,
        tint = tint,
        modifier = modifier,
    )
}

@Composable
private fun SubjectColor.toComposeColor(): Color {
    val colors = PomodoroTheme.colors
    return when (this) {
        SubjectColor.Blue -> colors.accentBlue
        SubjectColor.Green -> colors.accentGreen
        SubjectColor.Yellow -> colors.accentYellow
        SubjectColor.Orange -> colors.accentOrange
        SubjectColor.Purple -> colors.accentPink
    }
}

@Preview(showBackground = true)
@Composable
private fun PomodoroTimerScreenPreview() {
    MySecondAwesomeAndroidProjectPomodoroTimerTheme {
        PomodoroTimerContent(
            state = PomodoroTimerState(),
            onShowAddSubject = {},
            onDismissAddSubject = {},
            onNewSubjectNameChange = {},
            onIconSelected = {},
            onAddSubject = {},
            onStartStudy = {},
        )
    }
}
