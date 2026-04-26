plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "com.example.domain"
}


dependencies {
    compileOnly(libs.javax.inject)
    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
}

tasks.withType<Test> {
    useJUnitPlatform()
}