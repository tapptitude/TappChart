plugins {
    id("com.tapptitude.sharedcommon")
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