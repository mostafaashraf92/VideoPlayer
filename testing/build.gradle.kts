plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 35
    buildToolsVersion = "29.0.3"
    namespace = "com.realeyes.testing"

    defaultConfig.apply {
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.kotlin.stdlib)
    implementation(project(":domain"))
    implementation(project(":feature"))
    implementation(project(":core"))
    testImplementation(libs.junit)
}
