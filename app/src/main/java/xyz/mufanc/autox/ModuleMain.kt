package xyz.mufanc.autox

import xyz.mufanc.autox.annotation.XposedEntry

@XposedEntry(["com.android.settings"])
class ModuleMain {
    fun onPackageLoaded(param: Any) {
        // Todo:
    }
}
