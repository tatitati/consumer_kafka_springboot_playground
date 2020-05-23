package com.example.demo.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class LibraryEventConsumer {

    @KafkaListener(topics = arrayOf("library-events"))
    fun myconsumer(consumerRecord: ConsumerRecord<Int, String>) {
        println(consumerRecord)
    }
}
