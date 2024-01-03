package com.meam.kaffa.common.events

import org.springframework.context.ApplicationEvent


data class WPEvent(
    val id: Long,
    val startedBy: String,
) : ApplicationEvent(id)
