plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

repositories {
    google()
    mavenCentral()
}

android {
    namespace = "com.tappchart.android"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.tappchart.android"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared:common"))
    implementation(project(":shared:bar"))

    configurations.implementation {
        exclude(group = "org.jetbrains.kotlin", module="kotlin-stdlib-jdk8" )
    }

    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.foundation:foundation:1.4.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
}