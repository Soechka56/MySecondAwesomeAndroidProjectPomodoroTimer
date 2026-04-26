import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "ru.itis.android.core.plugin.ext"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApp") {
            id = libs.plugins.app.android.application.get().pluginId
            implementationClass = "AndroidAppConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.app.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidAppComposeBase") {
            id = libs.plugins.app.compose.base.get().pluginId
            implementationClass = "ComposeConventionPlugin"
        }
        register("androidDagger") {
            id = libs.plugins.app.dagger.get().pluginId
            implementationClass = "DaggerConventionPlugin"
        }
        register("androidAppComposeScreen"){
            id = libs.plugins.app.compose.screen.get().pluginId
            implementationClass = "ComposeScreenConventionPlugin"
        }
    }
}