plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 35
    buildToolsVersion = "29.0.3"
    namespace = "com.realeyes.videoplayer"
    defaultConfig {
        applicationId = "com.realeyes.videoplayer"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packagingOptions {
        resources {
            pickFirsts += listOf(
                "META-INF/kotlinx-io.kotlin_module",
                "META-INF/atomicfu.kotlin_module",
                "META-INF/kotlinx-coroutines-io.kotlin_module"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.kotlin.stdlib)
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature"))
    implementation(project(":testing"))
    implementation(project(":common-ui"))
}
