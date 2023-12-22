import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("com.tapptitude.sharedcommon")
}

kotlin {
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.shared.common)
                implementation(projects.shared.bar)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.tappchart.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TappChartComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
    }
}