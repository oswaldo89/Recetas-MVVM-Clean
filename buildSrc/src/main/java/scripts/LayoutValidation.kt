package scripts

import org.gradle.api.GradleException
import org.gradle.api.Project
import java.io.File

fun validateLayoutDimensions(project: Project) {
    val layoutFiles = File(project.projectDir, "src/main/res/layout").walk().filter { it.isFile && it.extension == "xml" }

    layoutFiles.forEach { layoutFile ->
        val lines = layoutFile.readLines()
        lines.forEachIndexed { index, line ->
            val regex = """android:layout_(width|height)\s*=\s*["'](?!(0dp|wrap_content|match_parent|0{1,}\d*dp|@dimen/[^"']*))[^\n]*["']""".toRegex()
            val matches = regex.findAll(line.trim())
            if (matches.any()) {
                println("Archivo: ${layoutFile.name}, Linea: ${index + 1}, Contenido: $line")
                throw GradleException("Validacion fallida: Dimensiones hardcodeadas!")
            }
        }
    }
}
