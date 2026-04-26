plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.feature.auth.api"
}

dependencies{
    implementation(project(path=":core:navigation"))
    implementation(project(path=":core:domain"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.x.lifecycle.viewmodel.compose)
}
