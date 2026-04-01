plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "com.example.network"
}

dependencies {
    api(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
}
