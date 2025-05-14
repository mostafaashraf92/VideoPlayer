rootProject.name = "VideoPlayer"

pluginManagement {
    plugins {
        id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    }
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}


include(":app", ":feature")
include(":core")
include(":domain")
include(":data")
include(":testing")
include(":common-ui")
