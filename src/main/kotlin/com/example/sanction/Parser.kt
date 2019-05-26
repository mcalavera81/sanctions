package com.example.sanction

import org.jsoup.Jsoup
import java.io.File


fun parseSanctionEntities(path: String): List<SanctionEntity> {
    val file = File(path)


    val doc = Jsoup.parse(file, "UTF-8")
    val entities = doc.select("export sanctionEntity").map {

        val logicalId = it.attr("logicalId").toString()
        val aliases = it.select("nameAlias[wholeName]")
                .map { it.attr("wholeName") }
                .filter { it.isNotBlank() }
                .toSet()

        SanctionEntity(logicalId, aliases)
    }.fold(mapOf(), { acc: Map<String, List<SanctionEntity>>, entity: SanctionEntity ->
        val newEntry = Pair(entity.entityId, acc.getOrDefault(entity.entityId, listOf()) + entity)
        acc + newEntry
    }).mapValues {
        val aliases = it.value.fold(setOf<String>()) { acc: Set<String>, sanctionEntity: SanctionEntity -> acc + sanctionEntity.aliases }
        SanctionEntity(it.key, aliases)
    }.values.toList()

    return entities
}
