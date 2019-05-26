package com.example.sanction

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.TextScore

@Document(collection = "sanctions")
data class SanctionEntity(
        @Id val entityId: String,
        @TextIndexed val aliases: Set<String>)

