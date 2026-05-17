package com.soechka1.mysecondawesomeandroidprojectpomodorotimer

import android.app.Application
import com.example.api.AuthNavKey
import com.example.di.AppComponent
import com.example.di.DaggerAppComponent
import com.example.studysession.api.StudySessionNavKey

class PomodoroApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext, StudySessionNavKey)
    }
}
