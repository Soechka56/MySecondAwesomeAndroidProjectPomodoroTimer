package com.soechka1.mysecondawesomeandroidprojectpomodorotimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.pomodorotimer.api.PomodoroTimerNavKey
import com.example.showleaders.api.ShowLeadersNavKey
import com.example.studygroup.api.StudyGroupNavKey
import com.soechka1.designsystem.component.MyExpressiveNavigation
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

                val currentScreen = navigator.backStack.lastOrNull()

                val showBottomBar = currentScreen in listOf(
                    PomodoroTimerNavKey, ShowLeadersNavKey, StudyGroupNavKey
                )

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            MyExpressiveNavigation()
                        }
                    }
                ) { innerPadding ->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = navigator.backStack,
                        onBack = { navigator.goBack() },
                        entryProvider = entryProvider {
                            entryProviderInstallers.forEach { installer ->
                                this.installer()
                            }
                        }
                    )
                }
            }
        }
    }
}