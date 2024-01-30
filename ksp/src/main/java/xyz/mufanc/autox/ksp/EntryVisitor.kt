package xyz.mufanc.autox.ksp

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import xyz.mufanc.autox.annotation.XposedEntry
import java.io.FileOutputStream
import java.io.OutputStream

@OptIn(KspExperimental::class)
class EntryVisitor(
    private val symbol: KSAnnotated,
    private val env: SymbolProcessorEnvironment
) : KSVisitorVoid() {

    private fun openScopeList(declaration: KSClassDeclaration): OutputStream {
        val exists = env.codeGenerator.generatedFile.find { file ->
            file.path.endsWith("META-INF/xposed/scope.list")
        }

        if (exists != null) {
            return FileOutputStream(exists, true)
        }

        return env.codeGenerator.createNewFileByPath(
            Dependencies(true, declaration.containingFile!!),
            "META-INF/xposed/scope", "list"
        )
    }

    private fun openXposedInitList(declaration: KSClassDeclaration): OutputStream {
        val exists = env.codeGenerator.generatedFile.find { file ->
            file.path.endsWith("META-INF/xposed/java_init.list")
        }

        if (exists != null) {
            return FileOutputStream(exists, true)
        }

        return env.codeGenerator.createNewFileByPath(
            Dependencies(true, declaration.containingFile!!),
            "META-INF/xposed/java_init", "list"
        )
    }

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        // generate scope.list
        val entries = symbol.getAnnotationsByType(XposedEntry::class).toList()
        if (entries.isNotEmpty()) {
            val scope = entries.first().scope
            if (scope.isNotEmpty()) {
                openScopeList(classDeclaration).write(scope.joinToString("\n").toByteArray())
            }
        }

        // generate java_init.list
        val packageName = classDeclaration.containingFile!!.packageName.asString()
        val className = classDeclaration.simpleName.asString()

        openXposedInitList(classDeclaration).write("${packageName}.${className}\n".toByteArray())
    }
}
