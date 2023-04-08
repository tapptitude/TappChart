plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10" apply false
    id("org.jetbrains.compose") version "1.3.1" apply false
    id("com.android.application") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0")
    }
}