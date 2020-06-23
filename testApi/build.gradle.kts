import org.jetbrains.configureBintrayPublication

plugins {
    `maven-publish`
    id("com.jfrog.bintray")
}

dependencies {
    api(project(":core"))
    api(project(":plugins:base"))
    implementation("junit:junit:4.13") // TODO: remove dependency to junit
    implementation(kotlin("stdlib"))
}

val sourceJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

publishing {
    publications {
        register<MavenPublication>("dokkaTestAPI") {
            artifactId = "dokka-test-api"
            from(components["java"])
            artifact(sourceJar.get())
        }
    }
}

configureBintrayPublication("dokkaTestAPI")