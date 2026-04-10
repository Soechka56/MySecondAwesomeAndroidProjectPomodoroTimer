package com.soechka1.mysecondawesomeandroidprojectpomodorotimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.soechka1.designsystem.theme.MySecondAwesomeAndroidProjectPomodoroTimerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appComponent = (application as PomodoroApp).appComponent
        val navigator = appComponent.navigator()
        val entryProviderInstallers = appComponent.entryProviderInstallers()

        setContent {
            MySecondAwesomeAndroidProjectPomodoroTimerTheme {
                NavDisplay(
                    backStack = navigator.backStack,
                    onBack = { navigator.goBack() },
                    entryProvider = entryProvider {
                        entryProviderInstallers.forEach { installer -> this.installer() }
                    }
                )
            }
        }
    }
}
