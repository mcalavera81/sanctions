package com.example.sanction

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

interface StatusService{

    fun status(): Status
    fun makeAvailable()
}

@Service
class  DefaultStatusService(): StatusService {

    var status = Status(HttpStatus.SERVICE_UNAVAILABLE.value(), "Not Available")

    override fun status(): Status {
        return status
    }

    @Synchronized override fun makeAvailable() {
        status = Status(HttpStatus.OK.value(), "Available")
    }
}

data class Status(val code: Int, val message:String)
