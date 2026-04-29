plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "com.example.utils"
}

dependencies {
    implementation(libs.javax.inject)
}