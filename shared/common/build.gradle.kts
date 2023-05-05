plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.compose")
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    /**
     * Transform @Composable functions and enable optimizations with a Kotlin compiler plugin.
     */
    api(compose.compiler.auto)

    /**
     * Write Jetpack Compose applications with ready to use building blocks
     * and extend foundation to build your own design system pieces.
     */
    api(compose.foundation)

    /**
     * Fundamental building blocks of Compose's programming model and state management,
     * and core runtime for the Compose Compiler Plugin to target.
     */
    api(compose.runtime)

    /**
     * Fundamental components of compose UI needed to interact with the device,
     * including layout, drawing, and input.
     */
    api(compose.ui)

    // Tooling and support for IDE
    api(compose.preview)
}
