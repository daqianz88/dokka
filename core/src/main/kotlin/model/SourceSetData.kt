package org.jetbrains.dokka.model

import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.Platform

data class SourceSetData(
    val moduleName: String,
    val sourceSetID: String,
    val displayName: String,
    val platform: Platform,
    val sourceRoots: List<DokkaConfiguration.SourceRoot> = emptyList(),
    val dependentSourceSets: List<String> = emptyList()
)

class SourceSetCache {
    private val sourceSets = HashMap<String, SourceSetData>()

    val allSourceSets: List<SourceSetData>
        get() = sourceSets.values.toList()

    fun getSourceSet(pass: DokkaConfiguration.PassConfiguration) =
        sourceSets.getOrPut(pass.sourceSetID,
            {
                SourceSetData(
                    pass.moduleName,
                    pass.sourceSetID,
                    pass.displayName,
                    pass.analysisPlatform,
                    pass.sourceRoots,
                    pass.dependentSourceSets
                )
            }
        )
}

