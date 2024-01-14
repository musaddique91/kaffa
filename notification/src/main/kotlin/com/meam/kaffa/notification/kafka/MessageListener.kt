package com.meam.kaffa.notification.kafka

import com.meam.kaffa.common.constants.KafkaConstants
import com.meam.kaffa.common.events.MailNotificationEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageListener {
    @KafkaListener(
        topics = [KafkaConstants.NOTIFICATION_TOPIC],
        groupId = KafkaConstants.NOTIFICATION_GROUP
    )
    fun listen(message: MailNotificationEvent) {
        try {
            println("msg recieved: $message")
        } catch (e: Exception) {
            error("Error: " + e.message)
        }
    }
}