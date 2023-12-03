plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}

fun String.exec(): String {
    return Runtime.getRuntime()
        .exec(this.split(" ").toTypedArray())
        .apply { waitFor() }
        .inputStream.bufferedReader().readText().trim()
}

val libraryVersion by extra("git rev-list --count HEAD".exec())

val androidMinSdkVersion by extra(26)
val androidTargetSdkVersion by extra(34)
val androidCompileSdkVersion by extra(34)

val androidSourceCompatibility by extra(JavaVersion.VERSION_17)
val androidTargetCompatibility by extra(JavaVersion.VERSION_17)
val androidKotlinJvmTarget by extra("17")

