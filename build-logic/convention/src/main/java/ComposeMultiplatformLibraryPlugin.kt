import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeMultiplatformLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        println("Applying the ${this::class.java.simpleName} to ${target.name}")

        target.repositories.mavenCentral()

        target.pluginManager.let { pluginManager ->
            pluginManager.apply("org.jetbrains.kotlin.jvm")
            pluginManager.apply("org.jetbrains.compose")
            pluginManager.apply("org.gradle.java-library")
        }

        target.dependencies.add("implementation", target.project(":shared:common"))
    }
}