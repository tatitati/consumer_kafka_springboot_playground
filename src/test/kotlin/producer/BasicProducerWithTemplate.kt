package producer

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.streams.state.RocksDBConfigSetter.LOG
import org.junit.jupiter.api.Test
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.ProducerListener
import java.lang.Exception


class BasicProducerWithTemplate {
    fun kafkaTemplateWithDefaultProducer(): KafkaTemplate<String?, String?> {
		val props: MutableMap<String, String> = HashMap()
		props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
		props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
		props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"

		val producer: DefaultKafkaProducerFactory<String, String> = DefaultKafkaProducerFactory(props as Map<String, Any>)
		return KafkaTemplate(producer)
	}

	fun kafkaTemplateWithProducerListener(): KafkaTemplate<String?, String?> {
		val props: MutableMap<String, String> = HashMap()
		props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
		props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
		props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"

		val producer: DefaultKafkaProducerFactory<String, String> = DefaultKafkaProducerFactory(props as Map<String, Any>)
		val kafkaTemplate = KafkaTemplate(producer)

		kafkaTemplate.setProducerListener(object : ProducerListener<String?, String?> {
			override fun onSuccess(producerRecord: ProducerRecord<String?, String?>, recordMetadata: RecordMetadata) {
				println("ACK from ProducerListener message-------> ${producerRecord.value()} offset:  ${recordMetadata.offset()}")
			}
			override fun onError(producerRecord: ProducerRecord<String?, String?>, exception: Exception) {
				println("error -----> ${exception.message}")
			}
		})
		return kafkaTemplate
	}

	@Test
	fun `can producer msgs to a topic with kafkaTemplateWithDefaultProducer`(){
		kafkaTemplateWithDefaultProducer()
				.send(
						ProducerRecord<String?, String?>("spring", "this is the msgggg again"))

		Thread.sleep(6000)
	}

	@Test
	fun `can producer msgs to a topic with kafkaTemplateWithProducerListener`(){
		kafkaTemplateWithProducerListener()
				.send(
						ProducerRecord<String?, String?>("spring", "this is the msgggg again"))
	}
}
