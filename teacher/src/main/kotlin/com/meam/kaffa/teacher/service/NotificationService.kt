package com.meam.kaffa.teacher.service

import com.meam.kaffa.common.constants.KafkaConstants
import com.meam.kaffa.common.events.MailNotificationEvent
import lombok.extern.slf4j.Slf4j
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
@Slf4j
class NotificationMessageService(
    private val kafkaTemplate: KafkaTemplate<String, MailNotificationEvent>,
) {
    fun send(message: MailNotificationEvent) {
        kafkaTemplate.send(KafkaConstants.NOTIFICATION_TOPIC, message)
    }
}
