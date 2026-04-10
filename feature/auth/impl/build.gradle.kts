plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.dagger)
    alias(libs.plugins.app.compose.screen)
}

android {
    namespace = "com.example.impl"
}

dependencies {
    implementation(project(":feature:auth:api"))
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
}
