package com.meam.kaffa.notification.mail

import com.meam.kaffa.notification.MailComposeDTO
import com.meam.kaffa.notification.config.ConfigProperties
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class KaffaMailSender(
    private val javaMailSender: JavaMailSender,
    private val configProperties: ConfigProperties
) {
    @Async
    fun send(data: MailComposeDTO) {
        val message = JavaMailSenderImpl().createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setFrom(configProperties.mailFrom)
        helper.setTo(data.receipients.toTypedArray())
        helper.setSubject(data.subject)
        data.templateContent?.run {
            helper.setText(this, true)
        }
        javaMailSender.send(message)
    }
}