plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.dagger)
}

android {
    namespace = "com.example.network"
}

dependencies {
    api(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.javax.inject)

    implementation(project(":core:domain"))
    implementation(project(":core:build-config"))

    androidTestImplementation(libs.androidx.junit)
}
