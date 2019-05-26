package com.example.sanction

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("sanction")
class SanctionProperties {

    val seed = Seed()

    class Seed{
     lateinit var path: String
     lateinit var name: String

    }

}
