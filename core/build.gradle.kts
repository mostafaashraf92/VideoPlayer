plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdk = 35
    buildToolsVersion = "29.0.3"
    namespace = "com.realeyes.core"
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

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.kotlin.stdlib)
    implementation(project(":domain"))

    api(libs.kotlin.stdlib)
    api(libs.androidx.core.ktx)
    api(libs.koin.android)
    api(libs.koin.core.extension)
    api(libs.coroutines.core)
    api(libs.coroutines.android)
    api(libs.androidx.lifecycle.viewmodel)
    testImplementation(libs.mockk)
    testImplementation(libs.core.testing)
    testImplementation(libs.koin.test)
    testImplementation(libs.mockito.kotlin)
}
