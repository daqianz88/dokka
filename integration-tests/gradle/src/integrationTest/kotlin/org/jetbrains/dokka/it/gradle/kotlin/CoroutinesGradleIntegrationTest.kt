package org.jetbrains.dokka.it.gradle.kotlin

import org.gradle.testkit.runner.TaskOutcome
import org.jetbrains.dokka.it.gradle.AbstractGradleIntegrationTest
import org.jetbrains.dokka.it.gradle.BuildVersions
import org.jetbrains.dokka.it.gradle.S3Project
import org.junit.runners.Parameterized
import java.io.File
import kotlin.test.*

class CoroutinesGradleIntegrationTest(override val versions: BuildVersions) : AbstractGradleIntegrationTest(), S3Project {

    companion object {
        @get:JvmStatic
        @get:Parameterized.Parameters(name = "{0}")
        val versions = BuildVersions.permutations(
            gradleVersions = listOf("6.3"),
            kotlinVersions = listOf("1.4.10")
        )
    }

    @BeforeTest
    fun prepareProjectFiles() {
        val templateProjectDir = File("projects/kotlin", "coroutines")
        templateProjectDir.listFiles().orEmpty()
            .forEach { topLevelFile -> topLevelFile.copyRecursively(File(projectDir, topLevelFile.name)) }
    }

    @Test
    fun execute() {
        val result = createGradleRunner(":dokkaHtmlMultiModule", "-i", "-s").buildRelaxed()

        assertEquals(TaskOutcome.SUCCESS, assertNotNull(result.task(":dokkaHtmlMultiModule")).outcome)

        val dokkaOutputDir = File(projectDir, "build/dokka/htmlMultiModule")
        assertTrue(dokkaOutputDir.isDirectory, "Missing dokka output directory")

        dokkaOutputDir.allHtmlFiles().forEach { file ->
//            assertContainsNoErrorClass(file)
//            assertNoUnresolvedLinks(file)
//            assertNoHrefToMissingLocalFileOrDirectory(file)
            assertNoEmptyLinks(file)
            assertNoEmptySpans(file)
        }
    }

    override val projectOutputLocation: File by lazy { File(projectDir, "build/dokka/htmlMultiModule") }
}
