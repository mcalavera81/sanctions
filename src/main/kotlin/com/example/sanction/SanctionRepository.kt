package com.example.sanction

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.core.query.TextCriteria



interface SanctionRepository: MongoRepository<SanctionEntity, String>{
    fun findAllBy(criteria: TextCriteria): List<SanctionEntity>

}

