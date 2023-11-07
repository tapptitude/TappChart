plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.1.0")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    compileOnly("org.jetbrains.compose:compose-gradle-plugin:1.4.3")
}

gradlePlugin {
    plugins.register("sharedCommon") {
        id = "com.tapptitude.sharedcommon"
        implementationClass = "ComposeMultiplatformLibraryPlugin"
    }
}