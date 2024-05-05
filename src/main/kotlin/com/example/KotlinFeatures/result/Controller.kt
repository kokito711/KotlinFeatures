package com.example.KotlinFeatures.result

import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit
import kotlin.math.log

@RestController
class Controller(
    private val service: KotlinFeaturesService,
    private val registry: MeterRegistry
) {

    var log: Logger = LoggerFactory.getLogger(Controller::class.java)


    @GetMapping
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR, reason = "OOPS" )
    fun getWithException(){

        callServiceWithException()
        log.info("Total time elapsed with exception: " +
                "${registry.get("service.with.exception").timer().totalTime(TimeUnit.MILLISECONDS)}")
    }

    @Timed(value = "service.with.exception")
    private fun callServiceWithException(){
        try {
            service.serviceWithException()
        } catch (ex: CustomException) {
            log.error("Exception on service: $ex")
        }
    }
}

