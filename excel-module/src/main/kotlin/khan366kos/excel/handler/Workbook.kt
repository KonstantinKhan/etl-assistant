package khan366kos.excel.handler

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.io.IOException
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Workbook private constructor() {
    companion object {
        @Volatile
        private var instance: Pair<String, XSSFWorkbook>? = null
        private val mutex = Mutex()

        suspend fun getInstance(filePath: String): XSSFWorkbook {
            val cached = instance
            if (cached != null && cached.first == filePath) {
                return cached.second
            }

            return mutex.withLock {
                val currentCached = instance
                if (currentCached != null && currentCached.first == filePath) {
                    currentCached.second
                } else {
                    loadWorkbook(filePath).also { workbook ->
                        instance = Pair(filePath, workbook)
                    }
                }
            }
        }

        @Throws(IOException::class)
        private fun loadWorkbook(filePath: String): XSSFWorkbook {
            return XSSFWorkbook(FileInputStream(filePath))
        }

        suspend fun closeInstance() {
            mutex.withLock {
                instance?.second?.close()
                instance = null
            }
        }
    }

}