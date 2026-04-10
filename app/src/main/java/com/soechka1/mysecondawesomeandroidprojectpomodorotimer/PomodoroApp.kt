package com.soechka1.mysecondawesomeandroidprojectpomodorotimer

import android.app.Application
import com.example.api.AuthNavKey
import com.example.di.AppComponent
import com.example.di.DaggerAppComponent

class PomodoroApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext, AuthNavKey)
    }
}
