import org.gradle.api.JavaVersion
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.*
import org.gradle.plugin.use.PluginDependenciesSpec

object Plugins {

    object Dependencies {

        fun addTo(config: PluginDependenciesSpec) {

            with(config) {
                kotlin("jvm") version Versions.Kotlin.value
                id("com.palantir.git-version") version Versions.Plugins.Palantir.Git.value
                `java-library`
                kotlin("kapt") version Versions.Kotlin.value
                kotlin("plugin.allopen") version Versions.Kotlin.value apply false
                id("io.micronaut.application") version Versions.Micronaut.Gradle.Plugin.value apply false
                idea
            }
        }
    }

    object JavaPlugin {
        fun configure(plugin: JavaPluginExtension) {
            with(plugin) {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }
}