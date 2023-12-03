plugins {
    id("java-library")
    id("maven-publish")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

val libraryVersion: String by rootProject.extra
val androidSourceCompatibility: JavaVersion by rootProject.extra
val androidTargetCompatibility: JavaVersion by rootProject.extra

java {
    sourceCompatibility = androidSourceCompatibility
    targetCompatibility = androidTargetCompatibility
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("annotation") {
                from(components["java"])
                artifact("build/libs/annotation-${version}.jar") { classifier = "jar" }
            }
        }
    }
}
