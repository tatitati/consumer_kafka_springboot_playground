package producer

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.jupiter.api.Test
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate

class BasicProducerWithTemplate {
    fun producerTemplate(): KafkaTemplate<String?, String?> {
		val props: MutableMap<String, String> = HashMap()
		props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
		props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
		props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"

		val factory: DefaultKafkaProducerFactory<String, String> = DefaultKafkaProducerFactory(props as Map<String, Any>)
		return KafkaTemplate(factory)
	}

	@Test
	fun `can producer msgs to a topic`(){
		producerTemplate()
				.send(
                        ProducerRecord<String?, String?>("spring", "this is the msgggg again"))

		Thread.sleep(6000)
	}
}
