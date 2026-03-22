plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.compose)
}

android {
    namespace = "com.soechka1.designsystem"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx) // deprecated
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.foundation)
    testImplementation(libs.junit)
}
