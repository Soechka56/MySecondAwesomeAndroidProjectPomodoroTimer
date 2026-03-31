plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "com.example.data"
}

dependencies {
    implementation(project(path=":core:domain"))
    implementation(project(path=":core:network"))

}