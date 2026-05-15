plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.database.impl"
}

dependencies {
    implementation(project(":core:database:api"))

    implementation(libs.room.compiler)
    implementation(libs.room.ktx)
}