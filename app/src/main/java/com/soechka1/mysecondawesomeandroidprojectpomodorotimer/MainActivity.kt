package com.soechka1.mysecondawesomeandroidprojectpomodorotimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.soechka1.designsystem.theme.MySecondAwesomeAndroidProjectPomodoroTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySecondAwesomeAndroidProjectPomodoroTimerTheme {

            }
        }
    }
}
