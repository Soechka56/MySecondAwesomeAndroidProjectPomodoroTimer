plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.compose.base)
}

android {
    namespace = "com.soechka1.designsystem"

    buildFeatures {
        compose = true
    }
}

dependencies {
}
