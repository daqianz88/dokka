package org.jetbrains.dokka.it.gradle.kotlin

import org.gradle.testkit.runner.TaskOutcome
import org.jetbrains.dokka.it.gradle.AbstractGradleIntegrationTest
import org.jetbrains.dokka.it.gradle.BuildVersions
import org.jetbrains.dokka.it.gradle.S3Project
import org.junit.runners.Parameterized
import java.io.File
import kotlin.test.*

class StdlibGradleIntegrationTest(override val versions: BuildVersions) : AbstractGradleIntegrationTest(), S3Project {

    companion object {
        @get:JvmStatic
        @get:Parameterized.Parameters(name = "{0}")
        val versions = BuildVersions.permutations(
            gradleVersions = listOf("5.6"),
            kotlinVersions = listOf("1.4.10")
        )
    }

    override val projectOutputLocation: File by lazy { File(projectDir, "build/dokka/kotlin-stdlib") }

    @BeforeTest
    fun prepareProjectFiles() {
        val templateProjectDir = File("projects/kotlin", "stdlib")
        templateProjectDir.listFiles().orEmpty()
            .forEach { topLevelFile -> topLevelFile.copyRecursively(File(projectDir, topLevelFile.name)) }
    }

    @Test
    fun execute() {
        val result = createGradleRunner("callDokka", "-i", "-s").buildRelaxed()

        assertEquals(TaskOutcome.SUCCESS, assertNotNull(result.task(":callDokka")).outcome)

        assertTrue(projectOutputLocation.isDirectory, "Missing dokka output directory")

        projectOutputLocation.allHtmlFiles().forEach { file ->
            assertContainsNoErrorClass(file)
//            assertNoUnresolvedLinks(file)
//            assertNoHrefToMissingLocalFileOrDirectory(file)
            assertNoEmptyLinks(file)
            assertNoEmptySpans(file)
        }
    }
}
