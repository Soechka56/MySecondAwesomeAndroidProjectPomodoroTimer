plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.compose.screen)
}

android {
    namespace = "com.soechka1.mysecondawesomeandroidprojectpomodorotimer"
}

dependencies {
    implementation(project(":core:designsystem"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.x.lifecycle.runtime.ktx)
    implementation(libs.x.activity.compose)
}
