package com.soechka1.mysecondawesomeandroidprojectpomodorotimer

import android.app.Application
import com.example.di.AppComponent
import com.example.di.DaggerAppComponent

class PomodoroApp : Application() {
    val appComponent: AppComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.create()
    }
}
