import com.khan366kos.common.models.classifier.ClassifierGroup
import com.khan366kos.common.models.classifier.RawClassifierGroup
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun main() {
    val filePath = "C:\\Users\\han\\Desktop\\classifier-structure.xlsx"
    val workbook = XSSFWorkbook(FileInputStream(filePath))
    workbook.use { workbook ->
        val sheet = workbook.getSheetAt(0)
        val rawGroups = (1 until sheet.physicalNumberOfRows)
            .map { rowIndex ->
                val row = sheet.getRow(rowIndex)
                RawClassifierGroup(
                    id = Uuid.random(),
                    min = row.getCell(1).stringCellValue.replace(".", "").replace(" ", "").takeIf { it.isNotEmpty() }
                        ?.toLong() ?: 0L,
                    max = row.getCell(2).stringCellValue.replace(".", "").replace(" ", "").takeIf { it.isNotEmpty() }
                        ?.toLong() ?: 0L,
                    level = row.getCell(3).numericCellValue.toLong(),
                    name = row.getCell(4).stringCellValue
                )
            }
        val groups = rawGroups.map { rawGroup ->
            ClassifierGroup(
                id = rawGroup.id,
                min = rawGroup.min,
                max = rawGroup.max,
                level = rawGroup.level,
                name = rawGroup.name,
                parendId = parentId(rawGroup.level, rawGroup.max, rawGroups),
                childsId = childsId(rawGroup.level, rawGroup.min, rawGroup.max, rawGroups),
            )
        }
        println(findGroupNameById(101021516100027951, groups))
    }
}

@OptIn(ExperimentalUuidApi::class)
fun parentId(level: Long, max: Long, rawGroups: List<RawClassifierGroup>): Uuid =
    rawGroups.filter { max < it.max }
        .filter { level > it.level }
        .takeIf { it.isNotEmpty() }
        ?.minBy { it.max }?.id ?: Uuid.random()


@OptIn(ExperimentalUuidApi::class)
fun childsId(level: Long, min: Long, max: Long, rawGroups: List<RawClassifierGroup>): List<Uuid> =
    rawGroups.filter { max > it.max && min < it.min }
        .filter { level < it.level && it.level - level == 1L }
        .takeIf { it.isNotEmpty() }
        ?.map { it.id } ?:
        listOf(Uuid.random())

fun findGroupNameById(id: Long, groups: List<ClassifierGroup>): String? {
    return groups.filter { group ->
        id >= group.min && id <= group.max
    }.minByOrNull { group ->
        group.max - group.min
    }?.name
}