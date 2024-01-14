package com.meam.kaffa.ums.event

import com.meam.kaffa.common.constants.KafkaConstants
import com.meam.kaffa.common.events.MailNotificationEvent
import lombok.extern.slf4j.Slf4j
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
@Slf4j
class MailNotificationProducer(
    private val kafkaTemplate: KafkaTemplate<String, MailNotificationEvent>
) {
    fun publish(kaffaEvent: MailNotificationEvent) {
        kafkaTemplate.send(KafkaConstants.NOTIFICATION_TOPIC, kaffaEvent)
    }
}