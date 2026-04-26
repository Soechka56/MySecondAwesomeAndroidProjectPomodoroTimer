plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.dagger)
}

android {
    namespace = "com.example.di"
}

dependencies {
    implementation(project(":core:viewmodel"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:navigation"))

    implementation(project(":feature:auth:api"))
    implementation(project(":feature:auth:impl"))

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.x.lifecycle.viewmodel.compose)
}