plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.compose.screen)
    alias(libs.plugins.app.dagger)
}

android {
    namespace = "com.soechka1.mysecondawesomeandroidprojectpomodorotimer"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:di"))

    // feature api
    implementation(project(":feature:auth:api"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)

    implementation(libs.x.lifecycle.runtime.ktx)
    implementation(libs.x.activity.compose)
}
