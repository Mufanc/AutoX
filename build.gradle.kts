plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}

val androidMinSdkVersion by extra(26)
val androidTargetSdkVersion by extra(34)
val androidCompileSdkVersion by extra(34)

val androidSourceCompatibility by extra(JavaVersion.VERSION_17)
val androidTargetCompatibility by extra(JavaVersion.VERSION_17)
val androidKotlinJvmTarget by extra("17")

