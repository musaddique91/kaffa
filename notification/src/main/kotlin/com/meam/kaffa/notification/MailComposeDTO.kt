package com.meam.kaffa.notification

data class MailComposeDTO(
    var receipients: MutableList<String>,
    var cc: MutableList<String>? = null,
    var subject: String,
    var attachmentsPath: MutableList<String>? = null,
    var templateContent: String? = null
)