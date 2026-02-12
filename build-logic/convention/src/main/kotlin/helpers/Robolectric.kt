package helpers

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File

/**
 * There is no global way to set the Robolectric SDK version, so we generate robolectric.properties
 * in the module's build/generated/testResources.
 *
 * Adopted from https://github.com/PaulWoitaschek/Voice/blob/main/plugins/src/main/kotlin/configureRobolectricSdk.kt
 */
fun setRobolectricSdk(target: Project) {
    val generatedResourceDir = target.file("build/generated/testResources").apply { mkdirs() }
    File(generatedResourceDir, "robolectric.properties").apply { writeText("sdk=35") }
    target.extensions.configure<CommonExtension> {
        sourceSets.named("test") {
            resources.directories.add(generatedResourceDir.path)
        }
    }
}
