package com.meam.kaffa.notification.kafka

import com.meam.kaffa.common.constants.KafkaConstants
import com.meam.kaffa.common.events.MailNotificationEvent
import com.meam.kaffa.common.events.NotificationType
import com.meam.kaffa.notification.service.UserRegistrationNotificationService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageListener(
    private val userRegistrationNotificationService: UserRegistrationNotificationService
) {
    @KafkaListener(
        topics = [KafkaConstants.NOTIFICATION_TOPIC],
        groupId = KafkaConstants.NOTIFICATION_GROUP
    )
    fun listen(message: MailNotificationEvent) {
        try {
            println("msg recieved: $message")
            val done = when (message.type) {
                NotificationType.NEW_USER_WITH_PASSWORD_RESET_MAIL -> true
                NotificationType.NEW_USER_MAIL_WITH_PASSWORD -> {
                    userRegistrationNotificationService.userRegisteredWithPasswordMail(message.to, message.modalMapData)
                    true
                }

                else -> {
                    println("other msg recieved: $message")
                    true
                }
            }
        } catch (e: Exception) {
            error("Error: " + e.message)
        }
    }
}