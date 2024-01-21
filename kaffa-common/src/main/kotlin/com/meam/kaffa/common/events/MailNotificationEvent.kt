package com.meam.kaffa.common.events

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.context.ApplicationEvent


data class MailNotificationEvent @JsonCreator constructor(
    val source: String,
    val to: MutableList<String>,
    var type: NotificationType? = null,
    val modalMapData: MutableMap<String, String>
) : ApplicationEvent(source)
