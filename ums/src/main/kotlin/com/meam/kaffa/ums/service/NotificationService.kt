package com.musa.wp.approvalsys.kafka

import com.meam.kaffa.common.constants.KafkaConstants
import com.meam.kaffa.common.events.NotificationEvent
import lombok.extern.slf4j.Slf4j
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
@Slf4j
class NotificationMessageService(
    private val kafkaTemplate: KafkaTemplate<String, NotificationEvent>,
) {
    fun send(message: NotificationEvent) {
        kafkaTemplate.send(KafkaConstants.NOTIFICATION_TOPIC, message)
    }
}
