## AutoX

Generate META-INF for your module with new Xposed api

### Usage

* in **settings.gradle.kts**

```kotlin
dependencyResolutionManagement {
    repositories {
        maven("https://jitpack.io")
    }
}
```

* in app **build.gradle.kts**

```kotlin
plugins {
    id("com.google.devtools.ksp") version "<version>"
}

dependencies {
//    ksp("xyz.mufanc.autox:ksp:master-SNAPSHOT")
//    implementation("xyz.mufanc.autox:annotation:master-SNAPSHOT")
    ksp("xyz.mufanc.autox:ksp:1.0.1")
    implementation("xyz.mufanc.autox:annotation:1.0.1")
}
```

* in your module entry

```kotlin
@XposedEntry(["com.example.app"])
class ModuleMain(ixp: XposedInterface, param: ModuleLoadedParam) : XposedModule(ixp, param) {
    // ......
}
```
