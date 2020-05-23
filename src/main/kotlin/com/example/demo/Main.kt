package com.example.demo

import com.example.demo.config.AutoCreateConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
class Main

fun main(args: Array<String>) {
	runApplication<Main>(*args)
}
