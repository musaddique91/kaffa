package com.meam.kaffa.school.core.config

import com.meam.kaffa.common.constants.KafkaConstants
import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaConfig {
    @Value("\${kafka.url}")
    var kafkaUrl: String = ""

    @Bean
    fun admin() = KafkaAdmin(mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaUrl))

    @Bean
    fun notificationTopic() =
        TopicBuilder.name(KafkaConstants.NOTIFICATION_TOPIC)
            .partitions(10)
            .replicas(1)
            .compact()
            .build()
}
