package com.example.sanction

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SanctionConfiguration {


    @Bean
    fun databaseInitializer(
            sanctionRepository: SanctionRepository,
            statusService: StatusService,
            properties: SanctionProperties) = ApplicationRunner {

        val sanctionEntities = parseSanctionEntities(properties.seed.path)

        sanctionRepository.saveAll(sanctionEntities)

        Thread.sleep(5000)
        statusService.makeAvailable()

    }

}