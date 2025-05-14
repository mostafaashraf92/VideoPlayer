// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.8.0")
        classpath(libs.kotlin.gradle.plugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlin.plugin.serialization") version ("1.9.0") apply false
}
subprojects {
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
