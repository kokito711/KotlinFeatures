package com.example.KotlinFeatures.result

import org.springframework.stereotype.Service

@Service
class KotlinFeaturesService {
    fun serviceWithException() {
        recursiveServiceWithException(300)
    }

    private fun recursiveServiceWithException(remainingCalls: Int) {
        if (remainingCalls > 0) {
            recursiveServiceWithException(remainingCalls-1)
        } else {
            throw CustomException()
        }
    }

}
