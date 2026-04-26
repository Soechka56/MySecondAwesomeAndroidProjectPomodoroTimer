plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.dagger)
}

android {
    namespace = "com.example.viewmodel"
}

dependencies {
    implementation(libs.x.lifecycle.viewmodel.compose)
}