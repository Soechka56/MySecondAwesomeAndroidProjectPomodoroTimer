plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.dagger)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.data"
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(project(path=":core:domain"))
    implementation(project(path=":core:network"))
    implementation(project(path=":core:utils"))
    implementation(project(path=":core:common"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.core.ktx)

    // скоро настанет твой час =)
    //implementation(libs.room)
    //implementation(libs.room.ktx)

    implementation(libs.x.datastore.preferences)

    ksp(libs.room.compiler)

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