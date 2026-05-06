package com.soechka1.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.ShortNavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.soechka1.designsystem.theme.PomodoroTheme
import com.soechka1.designsystem.R
import com.soechka1.designsystem.target.getDeviceUiConfig

data class NavigationItem(
    val label: String,
    val iconRes: Int,
    val contentDescription: String? = null
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MyExpressiveNavigation(
    navigationItems: List<NavigationItem>? = null,
) {
    val resolvedNavigationItems = navigationItems ?: listOf(
        NavigationItem(stringResource(R.string.navigation_timer), R.drawable.ic_play),
        NavigationItem(stringResource(R.string.navigation_leaders), R.drawable.ic_leaders),
        NavigationItem(stringResource(R.string.navigation_groups), R.drawable.ic_groups)
    )
    val adaptiveUi = getDeviceUiConfig()
    val pomodoroColors = PomodoroTheme.colors
    
    var selectedIndex by remember { mutableIntStateOf(0) }

    Box(contentAlignment = Alignment.Center) {
        ShortNavigationBar(
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(R.dimen.pomodoro_spacing_xx_large))),
            containerColor = pomodoroColors.surface,
        ) {
            resolvedNavigationItems.forEachIndexed { index, item ->
                ShortNavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    colors = ShortNavigationBarItemDefaults.colors(
                        selectedIconColor = pomodoroColors.accentGreen,
                        unselectedIconColor = pomodoroColors.accentOrange,
                        selectedTextColor = pomodoroColors.textPrimary,
                        selectedIndicatorColor = pomodoroColors.surface
                    ),
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(item.iconRes),
                            contentDescription = item.contentDescription,
                            modifier = Modifier.size(adaptiveUi.sizes.iconS)
                        )
                    },
                    label = { Text(item.label) }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowNavigation() {
    MyExpressiveNavigation()
}
