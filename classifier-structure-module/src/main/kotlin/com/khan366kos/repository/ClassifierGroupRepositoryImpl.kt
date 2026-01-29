package com.khan366kos.repository

import com.khan366kos.common.models.classifier.ClassifierGroup
import com.khan366kos.common.repo.ClassifierGroupRepository
import com.khan366kos.database.dao.ClassifierDao
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ClassifierGroupRepositoryImpl(
    private val classifierDao: ClassifierDao
) : ClassifierGroupRepository {

    override fun save(classifierGroup: ClassifierGroup) {
        classifierDao.insertClassifierGroup(classifierGroup)
    }

    override fun saveAll(classifierGroups: List<ClassifierGroup>) {
        classifierGroups.forEach { classifierGroup ->
            classifierDao.insertClassifierGroup(classifierGroup)
        }
    }

    override fun findById(id: Uuid): ClassifierGroup? {
        return classifierDao.getClassifierGroupById(id)
    }

    override fun findAll(): List<ClassifierGroup> {
        return classifierDao.getAllClassifierGroups()
    }

    override fun update(classifierGroup: ClassifierGroup) {
        classifierDao.updateClassifierGroup(classifierGroup)
    }

    override fun delete(id: Uuid) {
        classifierDao.deleteClassifierGroup(id)
    }
}