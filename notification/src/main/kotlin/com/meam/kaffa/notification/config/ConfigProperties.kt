package com.meam.kaffa.notification.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ConfigProperties {
    @Value("\${notification.from}")
    lateinit var mailFrom: String

    @Value("\${notification.server_url}")
    lateinit var serverUrl: String
}
