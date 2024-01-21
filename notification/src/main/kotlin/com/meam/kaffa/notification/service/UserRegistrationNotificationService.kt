package com.meam.kaffa.notification.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.meam.kaffa.common.dto.admin.organization.OrganizationDTO
import com.meam.kaffa.common.dto.ums.UserDTO
import com.meam.kaffa.notification.MailComposeDTO
import com.meam.kaffa.notification.config.ConfigProperties
import com.meam.kaffa.notification.mail.MailSender
import freemarker.template.Configuration
import org.springframework.stereotype.Service
import java.io.StringWriter

@Service
class UserRegistrationNotificationService(
    private val mailSender: MailSender,
    private val freemarkerConfiguration: Configuration,
    private val configProperties: ConfigProperties,
    private val objectMapper: ObjectMapper
) {
    fun userRegisteredWithPasswordMail(toEmail: MutableList<String>, modalMapData: MutableMap<String, String>) {
        val user = objectMapper.readValue(modalMapData["user"], UserDTO::class.java)
        val organization = objectMapper.readValue(modalMapData["organization"], OrganizationDTO::class.java)
        val modalMap = mutableMapOf(
            "user" to user,
            "organization" to organization,
            "password" to modalMapData["password"]
        )

        val writer = StringWriter()
        modalMapData["server_url"] = configProperties.serverUrl
        freemarkerConfiguration.getTemplate("new_user_registration.html").process(modalMap, writer)
        val data = MailComposeDTO(
            receipients = toEmail,
            subject = "Approval System: Your account information",
            templateContent = writer.buffer.toString()
        )
        mailSender.send(data)
    }
}