plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "com.example.domain"
}

dependencies {
    implementation(libs.x.lifecycle.runtime.ktx)
}