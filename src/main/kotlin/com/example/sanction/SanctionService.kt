package com.example.sanction

import org.simmetrics.builders.StringMetricBuilder
import org.simmetrics.metrics.CosineSimilarity
import org.simmetrics.simplifiers.Simplifiers
import org.simmetrics.tokenizers.Tokenizers
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.stereotype.Service
import java.util.*


interface SanctionService{

    fun findSanctionedEntities(query: String): List<SanctionEntityDTO>
}

@Service
class  DefaultSanctionService(private val repository: SanctionRepository): SanctionService{

    var similarity = StringMetricBuilder.with(CosineSimilarity<String>())
            .simplify(Simplifiers.toLowerCase(Locale.ENGLISH))
            .simplify(Simplifiers.removeDiacritics())
            .simplify(Simplifiers.removeNonWord())
            .tokenize(Tokenizers.whitespace())
            .tokenize(Tokenizers.qGram(3))
            .build()

    override fun findSanctionedEntities(query: String): List<SanctionEntityDTO> {

        if(query.isBlank()) return listOf()

        val words = query.toLowerCase().split("\\s+".toRegex()).toTypedArray()
        val criteria = TextCriteria.forLanguage("english").matchingAny(*words)
        val results = repository.findAllBy(criteria)


        return results.map {
            val score = it.aliases.map { similarity.compare(it, query) }.max() ?: 0.0F
            SanctionEntityDTO(entityId = it.entityId, aliases = it.aliases, relevance = score)
        }.sortedByDescending { it.relevance }

    }


}

data class SanctionEntityDTO(val entityId: String,
                             val aliases: Set<String>,
                             val sanctioned: Boolean = true,
                             val list: String = "european_sanctions_list",
                             val relevance: Float)