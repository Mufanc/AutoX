package xyz.mufanc.autox.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class XposedEntry(val scope: Array<String> = [])
