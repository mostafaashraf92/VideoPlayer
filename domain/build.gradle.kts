plugins {
    id("com.android.library")
    id("kotlin-android")
}

configurations.all {
    resolutionStrategy {
        force(libs.kotlinx.serialization.json)
        force(libs.kotlinx.serialization.core)
    }
}
android {
    compileSdk = 35
    buildToolsVersion = "29.0.3"
    namespace = "com.realeyes.domain"

    defaultConfig.apply {
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
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
    api(libs.kotlinx.serialization.json)
    implementation(libs.coroutines.core)
}
