package com.meam.kaffa.ums.event

import com.meam.kaffa.common.events.MailNotificationEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ApplicationEventListenerService(
    private val mailNotificationProducer: MailNotificationProducer
) {
    @EventListener
    fun eventListener(event: MailNotificationEvent) {
        mailNotificationProducer.publish(event)
}
}