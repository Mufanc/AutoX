package xyz.mufanc.autox.ksp

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import xyz.mufanc.autox.annotation.XposedEntry

internal class EntryProcessor(
    private val env: SymbolProcessorEnvironment
) : SymbolProcessor {

    companion object {
        private const val MODULE_PROPS = "minApiVersion=100\ntargetApiVersion=100\nstaticScope=true"
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(XposedEntry::class.qualifiedName!!)

        symbols.forEach { symbol ->
            symbol.accept(EntryVisitor(symbol, env), Unit)
        }

        return emptyList()  // 返回无法处理的符号
    }
    override fun finish() {
        // generate module.prop
        val stream = env.codeGenerator.createNewFileByPath(
            Dependencies(false), "META-INF/xposed/module", "prop"
        )

        stream.write(MODULE_PROPS.toByteArray())
    }
}
