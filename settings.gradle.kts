pluginManagement {
    includeBuild("convention-plugins")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MySecondAwesomeAndroidProjectPomodoroTimer"
include(":app")
include(":core:designsystem")
include(":core:di")
include(":feature")
include(":feature:auth")
include(":feature:auth:api")
include(":feature:auth:impl")
include(":core:domain")
include(":core:data")
include(":core:network")
include(":core:navigation")
include(":feature:user-profile")
include(":feature:user-profile:api")
include(":feature:user-profile:impl")
include(":core:viewmodel")
