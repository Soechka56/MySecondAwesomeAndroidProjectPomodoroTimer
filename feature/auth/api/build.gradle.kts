plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.api"
}

dependencies{
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation3.runtime)

    implementation(libs.x.lifecycle.viewmodel.compose)
}
