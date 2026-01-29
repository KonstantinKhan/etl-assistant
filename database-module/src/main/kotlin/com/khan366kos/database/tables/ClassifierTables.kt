package com.khan366kos.database.tables

import org.jetbrains.exposed.sql.Table

object ClassifierGroups : Table("classifier_groups") {
    val id = varchar("id", 36) // UUID в виде строки
    val min = long("min_value")
    val max = long("max_value")
    val level = long("level_value")
    val name = varchar("name", 255)
    val parentId = varchar("parent_id", 36).nullable()

    override val primaryKey = PrimaryKey(id)
}

object ClassifierGroupChildren : Table("classifier_group_children") {
    val parentId = varchar("parent_id", 36)
    val childId = varchar("child_id", 36)

    override val primaryKey = PrimaryKey(parentId, childId)
}