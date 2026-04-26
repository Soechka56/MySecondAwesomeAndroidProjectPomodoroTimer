package com.soechka1.designsystem.autolayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.soechka1.designsystem.component.TimerCardS
import com.soechka1.designsystem.target.getDeviceUiConfig
import com.soechka1.designsystem.theme.PomodoroTheme

@Composable
fun CustomStaggeredGrid(
    items: List<@Composable (() -> Unit)>,
    modifier: Modifier = Modifier,
    columnCount: Int = 2,
    horizontalSpacing: Dp = PomodoroTheme.spacing.large,
    verticalSpacing: Dp = PomodoroTheme.spacing.large,
) {

    val ui = getDeviceUiConfig()
    val screenWidthDp = ui.widthDp.toFloat()
    val minClickableDp = ui.sizes.minTouchTarget.value

    // reduce the column count until each column is at least the minimum clickable width
    var columns = columnCount.coerceAtLeast(1)
    while (columns > 1 && (screenWidthDp / columns) < minClickableDp) {
        columns -= 1
    }

    val distributedItems = List(columns) { columnIndex ->
        items.filterIndexed { index, _ -> (index % columns) == columnIndex }
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
    ) {
        // pinterest like feed
        for (i in 0 until columns) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(verticalSpacing),
            ) {
                distributedItems[i].forEach { compFunc ->
                    compFunc()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TestGrid() {
    val itemsList = List(12) {
        @Composable { TimerCardS() }
    }
    CustomStaggeredGrid(items = itemsList, columnCount = 3)
}
