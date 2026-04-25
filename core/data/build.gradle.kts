plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "com.example.data"
}


dependencies {
    implementation(project(path=":core:domain"))
    implementation(project(path=":core:network"))

    implementation(libs.androidx.core.ktx)

    compileOnly(libs.javax.inject)

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)

    testImplementation(libs.retrofit)
    testImplementation(libs.retrofit.converter.gson)

    testImplementation(libs.mock.web.server)

}

tasks.withType<Test> {
    useJUnitPlatform()
}