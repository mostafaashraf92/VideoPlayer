plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.realeyes.common_ui"
    compileSdk = 35
    namespace = "com.realeyes.common_ui"

    defaultConfig.apply() {
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))
    api(project(":core"))
    api(libs.koin.android)
    api(libs.koin.compose)
    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(platform(libs.compose.bom)) // apply BOM
    api(libs.compose.ui)
    api(libs.compose.material)
    api(libs.compose.preview)
    api(libs.compose.runtime)
    api(libs.coil.compose)
    api(libs.koin.navigation.graph)
    api(libs.androidx.ui.navigation)
    api(libs.material)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.navigation.fragment.ktx)
    api(libs.androidx.navigation.ui.ktx)
    api(libs.glide)
    kapt(libs.glide.compiler)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}