package com.soechka1.designsystem.target

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import com.soechka1.designsystem.theme.PomodoroSizes
import com.soechka1.designsystem.theme.SizesCompact
import com.soechka1.designsystem.theme.SizesMedium
import com.soechka1.designsystem.theme.SizesExpanded

/** layout only config (not business logic) ;) */

@Immutable
data class DeviceUiConfig(
    val type: DeviceTypeEnum,
    val sizes: PomodoroSizes,
    val widthDp: Int,
    val heightDp: Int,
)

@Composable
fun getDeviceUiConfig(): DeviceUiConfig {
    val configuration = LocalConfiguration.current

    val widthDp = configuration.screenWidthDp
    val heightDp = configuration.screenHeightDp

    // cache ui configuration
    val type = remember(widthDp) {
        when {
            widthDp < 600 -> DeviceTypeEnum.Compact
            widthDp < 840 -> DeviceTypeEnum.Medium
            else -> DeviceTypeEnum.Expanded
        }
    }

    val sizes = remember(type) {
        when (type) {
            DeviceTypeEnum.Compact -> SizesCompact
            DeviceTypeEnum.Medium -> SizesMedium
            DeviceTypeEnum.Expanded -> SizesExpanded
        }
    }

    return remember(type, sizes, widthDp, heightDp) {
        DeviceUiConfig(type, sizes, widthDp, heightDp)
    }
}
