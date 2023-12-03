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
            create<MavenPublication>("ksp") {
                from(components["java"])
                artifact("build/libs/ksp-${version}.jar") { classifier = "jar" }
            }
        }
    }
}

dependencies {
    implementation(libs.ksp)
    implementation(project(":annotation"))
}
