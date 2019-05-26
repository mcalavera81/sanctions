package com.example.sanction

import com.nhaarman.mockito_kotlin.any
import io.kotlintest.matchers.containAll
import org.hamcrest.Matchers.*
import org.hamcrest.collection.IsIterableContainingInAnyOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class])
class SanctionTest{


    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var repo: SanctionRepository

    @Test
    fun shouldReturnDefaultMessage() {

        val corpus = listOf(
                SanctionEntity("1",setOf("First Name", "Richardson")),
                SanctionEntity("2",setOf("Last Name", "Uncle Bob"))
        )


        BDDMockito.given(repo.findAllBy(any())).willReturn(corpus)

        this.mockMvc
                .perform(get("/search").param("name","First Name"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$", hasSize<Int>(2)))
                .andExpect(jsonPath("\$.[0].relevance").value(1.0))
                .andExpect(jsonPath("\$.[0].aliases", containsInAnyOrder("First Name", "Richardson")))


    }

}