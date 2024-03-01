package com.meam.kaffa.school.core.event

import com.meam.kaffa.common.events.MailNotificationEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ApplicationEventListenerService(
    private val mailNotificationProducer: com.meam.kaffa.school.core.event.MailNotificationProducer
) {
    @EventListener
    fun eventListener(event: MailNotificationEvent) {
        mailNotificationProducer.publish(event)
}
}