package com.example.sanction

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/status")
class StatusController(private val service: StatusService) {


    @GetMapping("")
    fun status(): ResponseEntity<Status> {

        val status = service.status()
        return ResponseEntity.status(status.code).body(status)
    }


}

