package helpers

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal sealed interface Namespace {
    object Auto : Namespace

    @JvmInline
    value class Manual(val namespace: String) : Namespace
}

internal fun Project.applyBaseConfig(excludeMetaInfResources: Boolean = true, namespace: Namespace = Namespace.Auto) {
    pluginManager.applyPlugins("ktlint", catalog = catalog)
    setRobolectricSdk(this)

    extensions.apply {
        configure<KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
                freeCompilerArgs.addAll(
                    "-opt-in=kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi",
                    "-Xannotation-default-target=param-property",
                    "-Xwhen-guards",
                    "-XXLanguage:+ExplicitBackingFields",
                    "-Xnested-type-aliases",
                    "-Xcontext-sensitive-resolution",
                    "-Xcontext-parameters"
                )
            }
        }
        configure<CommonExtension> {
            this.namespace = when (namespace) {
                is Namespace.Auto -> "com.w2sv." + path
                    .removePrefix(":")
                    .replace(':', '.')
                    .replace('-', '_')  // Sets namespace to "com.w2sv.<module-name>"
                is Namespace.Manual -> namespace.namespace
            }

            defaultConfig.apply {
                minSdk = catalog.findVersionInt("minSdk")
                compileSdk = catalog.findVersionInt("compileSdk")
                if (this is ApplicationDefaultConfig) {
                    targetSdk = catalog.findVersionInt("compileSdk")
                }
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions.apply {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            testOptions.apply {
                unitTests {
                    isReturnDefaultValues = true
                    isIncludeAndroidResources = true
                    all { test -> test.failOnNoDiscoveredTests.set(false) }
                }
                animationsDisabled = true
            }
            if (excludeMetaInfResources) {
                packaging.resources.excludes.add("/META-INF/*")
            }
        }
    }
}
