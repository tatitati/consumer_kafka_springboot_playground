package com.example.demo.ui

import com.example.demo.consumer.LibraryEventProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController


@RestController
class EventsController{

    @Autowired
    lateinit var libraryEventConsumer: LibraryEventConsumer

}
