package com.soechka1.mysecondawesomeandroidprojectpomodorotimer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.api.contract.AuthResult
import com.example.api.contract.AuthType
import com.example.impl.navigation.AuthEntryProvider
import com.soechka1.designsystem.theme.MySecondAwesomeAndroidProjectPomodoroTimerTheme

class MainActivity : ComponentActivity() {
    private val authEntryProvider = AuthEntryProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySecondAwesomeAndroidProjectPomodoroTimerTheme {
                authEntryProvider.Content(
                    factory = (application as PomodoroApp).appComponent.authViewModelFactory(),
                    onResult = { result ->
                        when (result) {
                            is AuthResult.Success -> {
                                val message = when (result.type) {
                                    AuthType.LOGIN -> "Вход выполнен"
                                    AuthType.REGISTRATION -> "Регистрация выполнена"
                                }
                                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                            }

                            is AuthResult.Error -> {
                                Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                            }

                            AuthResult.Cancelled -> Unit
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
