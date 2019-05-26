package com.example.sanction


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/search")
class SanctionController(private val service: SanctionService) {


    @GetMapping("")
    fun findSanctionedEntitiesByName(@RequestParam(name = "name",required = false) query: String): List<SanctionEntityDTO> {
        return service.findSanctionedEntities(query)
    }


}


