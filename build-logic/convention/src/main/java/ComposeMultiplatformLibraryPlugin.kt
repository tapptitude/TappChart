import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(ExperimentalKotlinGradlePluginApi::class)
class ComposeMultiplatformLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        println("Applying the ${this::class.java.simpleName} to ${target.name}")

        pluginManager.apply {
            apply("org.jetbrains.kotlin.multiplatform")
            apply("org.jetbrains.compose")
        }

        with(extensions.getByType<ComposeExtension>()) {
            kotlinCompilerPlugin.set("1.5.0")
        }

        target.extensions.configure<KotlinMultiplatformExtension> {
            targetHierarchy.default()

            jvm()
            if (target.pluginManager.hasPlugin("com.android.library")) {
                androidTarget()
            }
        }
    }
}