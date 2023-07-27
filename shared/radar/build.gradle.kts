plugins {
    id("com.tapptitude.sharedcommon")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:common"))
            }
        }
    }
}