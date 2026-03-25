plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.compose)
}

android {
    namespace = "com.soechka1.mysecondawesomeandroidprojectpomodorotimer"

    defaultConfig {
        applicationId = "com.soechka1.mysecondawesomeandroidprojectpomodorotimer"
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.x.lifecycle.runtime.ktx)
    implementation(libs.x.activity.compose)
}
