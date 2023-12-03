package xyz.mufanc.autox

import io.github.libxposed.api.XposedInterface
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface
import xyz.mufanc.autox.annotation.XposedEntry

@XposedEntry(["com.android.settings"])
class ModuleMain(ixp: XposedInterface, param: XposedModuleInterface.ModuleLoadedParam) : XposedModule(ixp, param) {
    override fun onPackageLoaded(param: XposedModuleInterface.PackageLoadedParam) {
        // Todo:
    }
}
