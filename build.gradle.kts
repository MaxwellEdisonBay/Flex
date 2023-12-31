// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()  // Google"s Maven repository
        mavenCentral()  // Maven Central repository
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}

plugins {
    id("com.android.application") version "8.0.0" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}
