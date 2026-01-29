package com.khan366kos.database.dao

import com.khan366kos.database.tables.ClassifierGroupChildren
import com.khan366kos.database.tables.ClassifierGroups
import com.khan366kos.common.models.classifier.ClassifierGroup
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalUuidApi::class)
class ClassifierDao {

    fun insertClassifierGroup(classifierGroup: ClassifierGroup) {
        transaction {
            ClassifierGroups.insert {
                it[ClassifierGroups.id] = classifierGroup.id.toString()
                it[ClassifierGroups.min] = classifierGroup.min
                it[ClassifierGroups.max] = classifierGroup.max
                it[ClassifierGroups.level] = classifierGroup.level
                it[ClassifierGroups.name] = classifierGroup.name
                it[ClassifierGroups.parentId] = classifierGroup.parendId.toString()
            }

            // Вставляем связи "родитель-ребенок" для каждого дочернего элемента
            classifierGroup.childsId.forEach { childId ->
                ClassifierGroupChildren.insert {
                    it[ClassifierGroupChildren.parentId] = classifierGroup.id.toString()
                    it[ClassifierGroupChildren.childId] = childId.toString()
                }
            }
        }
    }

    fun getClassifierGroupById(id: Uuid): ClassifierGroup? {
        return transaction {
            ClassifierGroups.select(ClassifierGroups.id eq id.toString()).firstOrNull()?.let { row ->
                val childIds = ClassifierGroupChildren
                    .select(ClassifierGroupChildren.parentId eq id.toString())
                    .map { Uuid.parse(it[ClassifierGroupChildren.childId]) }

                ClassifierGroup(
                    id = Uuid.parse(row[ClassifierGroups.id]),
                    min = row[ClassifierGroups.min],
                    max = row[ClassifierGroups.max],
                    level = row[ClassifierGroups.level],
                    name = row[ClassifierGroups.name],
                    parendId = Uuid.parse(row[ClassifierGroups.parentId] ?: "00000000-0000-0000-0000-000000000000"),
                    childsId = childIds
                )
            }
        }
    }

    fun getAllClassifierGroups(): List<ClassifierGroup> {
        return transaction {
            val allGroups = ClassifierGroups.selectAll().associate { row ->
                val id = Uuid.parse(row[ClassifierGroups.id])
                id to ClassifierGroup(
                    id = id,
                    min = row[ClassifierGroups.min],
                    max = row[ClassifierGroups.max],
                    level = row[ClassifierGroups.level],
                    name = row[ClassifierGroups.name],
                    parendId = Uuid.parse(row[ClassifierGroups.parentId] ?: "00000000-0000-0000-0000-000000000000"),
                    childsId = emptyList() // Will populate later
                )
            }.values.toList()

            // Now populate child IDs for each group
            allGroups.map { group ->
                val childIds = ClassifierGroupChildren
                    .select(ClassifierGroupChildren.parentId eq group.id.toString())
                    .map { Uuid.parse(it[ClassifierGroupChildren.childId]) }

                group.copy(childsId = childIds)
            }
        }
    }

    fun getChildIdsByParentId(parentId: Uuid): List<Uuid> {
        return transaction {
            ClassifierGroupChildren
                .select(ClassifierGroupChildren.parentId eq parentId.toString())
                .map { Uuid.parse(it[ClassifierGroupChildren.childId]) }
        }
    }

    fun updateClassifierGroup(classifierGroup: ClassifierGroup) {
        transaction {
            ClassifierGroups.update({ ClassifierGroups.id eq classifierGroup.id.toString() }) {
                it[ClassifierGroups.min] = classifierGroup.min
                it[ClassifierGroups.max] = classifierGroup.max
                it[ClassifierGroups.level] = classifierGroup.level
                it[ClassifierGroups.name] = classifierGroup.name
                it[ClassifierGroups.parentId] = classifierGroup.parendId.toString()
            }

            // Удаляем старые связи "родитель-ребенок"
            ClassifierGroupChildren.deleteWhere { ClassifierGroupChildren.parentId eq classifierGroup.id.toString() }

            // Вставляем новые связи "родитель-ребенок"
            classifierGroup.childsId.forEach { childId ->
                ClassifierGroupChildren.insert {
                    it[ClassifierGroupChildren.parentId] = classifierGroup.id.toString()
                    it[ClassifierGroupChildren.childId] = childId.toString()
                }
            }
        }
    }

    fun deleteClassifierGroup(id: Uuid) {
        transaction {
            // Сначала удаляем все связи "родитель-ребенок" для этой группы
            ClassifierGroupChildren.deleteWhere { ClassifierGroupChildren.parentId eq id.toString() }
            ClassifierGroupChildren.deleteWhere { ClassifierGroupChildren.childId eq id.toString() }

            // Затем удаляем саму группу
            ClassifierGroups.deleteWhere { ClassifierGroups.id eq id.toString() }
        }
    }
}