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
dependencies {
    ksp("xyz.mufanc.autox:ksp:1.0.0")
    implementation("xyz.mufanc.autox:annotation:1.0.0")
}
```

* in your module entry

```kotlin
@XposedEntry(["com.example.app"])
class ModuleMain(ixp: XposedInterface, param: ModuleLoadedParam) : XposedModule(ixp, param) {
    // ......
}
```
