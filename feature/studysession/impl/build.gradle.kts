plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.dagger)
    alias(libs.plugins.app.compose.screen)
}

android {
    namespace = "com.example.studysession.impl"
}

dependencies {
    implementation(project(":feature:studysession:api"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:viewmodel"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
}
