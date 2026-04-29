plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.gradle.secrets)
}
var keyValue = ""
val propertyFile = File("properties")
if(propertyFile.exists()){
    keyValue = propertyFile.readText()
}

android {
    namespace = "com.example.build_config"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "BACKEND_URL", "\"$keyValue\"")
    }
}
dependencies{
    implementation(libs.javax.inject)
}