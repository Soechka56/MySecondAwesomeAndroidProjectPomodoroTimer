plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "com.example.navigation"

}

dependencies {
    api(libs.androidx.navigation3.runtime)
    implementation(libs.javax.inject)
}
