package com.example.demo.consumer

import com.example.demo.domain.LibraryEvent
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFutureCallback

@Component
class LibraryEventConsumer {

    @KafkaListener(topics = arrayOf("library-events"))
    fun myconsumer(consumerRecord: ConsumerRecord<Int, String>) {
        
    }
}
