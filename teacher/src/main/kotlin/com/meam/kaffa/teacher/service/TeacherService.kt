package com.meam.kaffa.teacher.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.meam.kaffa.common.dto.admin.organization.OrganizationDTO
import com.meam.kaffa.common.dto.student.StudentDTO
import com.meam.kaffa.common.dto.teacher.TeacherDTO
import com.meam.kaffa.common.enumrate.UserType
import com.meam.kaffa.common.events.MailNotificationEvent
import com.meam.kaffa.common.events.NotificationType
import com.meam.kaffa.common.model.KeyValue
import com.meam.kaffa.security.util.SecurityHelper
import com.meam.kaffa.teacher.client.AdminClient
import com.meam.kaffa.teacher.client.UMSClient
import com.meam.kaffa.teacher.config.ConfigProperties
import com.meam.kaffa.teacher.constants.TeacherCustomeFields
import com.meam.kaffa.teacher.mapper.TeacherMapper
import lombok.extern.slf4j.Slf4j
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Slf4j
class TeacherService(
    private val umsClient: UMSClient,
    private val adminClient: AdminClient,
    private val configProperties: ConfigProperties,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val objectMapper: ObjectMapper,
    private val teacherMapper: TeacherMapper
) {
    fun create(teacherDTO: TeacherDTO): TeacherDTO {
        return teacherDTO
            .let { teacherMapper.toUserDTO(it) }
            .let {
                SecurityHelper.sessionUser?.let { user -> it.organizationId = user.organizationId }
                it.userType = UserType.TEACHER
                it.customFields = mapAndSetCustomerFields(teacherDTO)
                it
            }.let { umsClient.createUser(it) }
            .let {
                val teacherDTO1 = teacherMapper.toTeacherDTO(it)
                mapAndSetCustomerFields(teacherDTO1, it.customFields)
                teacherDTO1
            }
    }

    private fun mapAndSetCustomerFields(teacherDTO: TeacherDTO): MutableList<KeyValue> {
        val customFields = mutableListOf<KeyValue>()
        teacherDTO.teacherId?.let { customFields.add(KeyValue(key = TeacherCustomeFields.TEACHER_ID, value = it)) }
        teacherDTO.department?.let { customFields.add(KeyValue(key = TeacherCustomeFields.DEPARTMENT, value = it)) }
        teacherDTO.division?.let { customFields.add(KeyValue(key = TeacherCustomeFields.DIVISION, value = it)) }
        teacherDTO.joiningDate?.let { customFields.add(KeyValue(key = TeacherCustomeFields.JOINING_DATE, value = it.toString())) }
        teacherDTO.subjects?.let { customFields.add(KeyValue(key = TeacherCustomeFields.SUBJECTS, value = it)) }
        teacherDTO.qualifications?.let { customFields.add(KeyValue(key = TeacherCustomeFields.QUALIFICATIONS, value = it)) }
        teacherDTO.nationality?.let { customFields.add(KeyValue(key = TeacherCustomeFields.NATIONALITY, value = it)) }
        teacherDTO.languageSpoken?.let { customFields.add(KeyValue(key = TeacherCustomeFields.LANGUAGE_SPOKEN, value = it)) }
        teacherDTO.note?.let { customFields.add(KeyValue(key = TeacherCustomeFields.NOTE, value = it)) }
        teacherDTO.yearOfExperience?.let { customFields.add(KeyValue(key = TeacherCustomeFields.YEARS_OF_EXPERIENCE, value = it)) }
        return customFields;
    }

    private fun mapAndSetCustomerFields(
        teacherDTO: TeacherDTO,
        customFields: MutableList<KeyValue>?
    ) {
        customFields?.let {
            customFields.map {
                when (it.key){
                    TeacherCustomeFields.TEACHER_ID -> teacherDTO.teacherId = it.value
                    TeacherCustomeFields.DEPARTMENT -> teacherDTO.department = it.value
                    TeacherCustomeFields.DIVISION -> teacherDTO.division = it.value
                    TeacherCustomeFields.JOINING_DATE -> teacherDTO.joiningDate = LocalDateTime.parse(it.value)
                    TeacherCustomeFields.SUBJECTS -> teacherDTO.subjects = it.value
                    TeacherCustomeFields.QUALIFICATIONS -> teacherDTO.qualifications = it.value
                    TeacherCustomeFields.NATIONALITY -> teacherDTO.nationality = it.value
                    TeacherCustomeFields.LANGUAGE_SPOKEN -> teacherDTO.languageSpoken = it.value
                    TeacherCustomeFields.NOTE -> teacherDTO.note = it.value
                    TeacherCustomeFields.YEARS_OF_EXPERIENCE -> teacherDTO.yearOfExperience = it.value
                    else -> println("Unknown custom field key: ${it.key}")
                }
            }
        }

    }


    private fun sendAccountCreatedMail(user: StudentDTO) {
        var organization: OrganizationDTO? = null
        user.organizationId?.let {
            organization = adminClient.getOrganizationById(it)
        }

        val event = MailNotificationEvent(
            source = "USER_PASSWORD",
            to = mutableListOf(user.email!!),
            modalMapData = mutableMapOf(
                "user" to objectMapper.writeValueAsString(user),
                "organization" to objectMapper.writeValueAsString(organization),
            ),
            title = "Welcome to ${organization?.name}",
        )
        if (organization?.organizationConfig != null) {
            if (organization?.organizationConfig?.createUserWithPassword == true) {
                event.modalMapData["password"] = configProperties.defaultPassword
                event.type = NotificationType.NEW_USER_MAIL_WITH_PASSWORD
            } else {
                event.type = NotificationType.NEW_USER_WITH_PASSWORD_RESET_MAIL
            }
        } else {
            event.type = NotificationType.NEW_USER_WITH_PASSWORD_RESET_MAIL
        }
        applicationEventPublisher.publishEvent(event)
    }

    fun update(id: Long, teacherDTO: TeacherDTO): TeacherDTO {
        return teacherDTO
            .let { teacherMapper.toUserDTO(it) }
            .let {
                SecurityHelper.sessionUser?.let { user -> it.organizationId = user.organizationId }
                it.userType = UserType.TEACHER
                it.customFields = mapAndSetCustomerFields(teacherDTO)
                it
            }.let { umsClient.update(id,it) }
            .let {
                val teacherDTO1 = teacherMapper.toTeacherDTO(it)
                mapAndSetCustomerFields(teacherDTO1, it.customFields)
                teacherDTO1
            }
    }

    fun getUser(id: Long): TeacherDTO {
        return umsClient.getUser(id).let {
            val teacherDTO = teacherMapper.toTeacherDTO(it)
            mapAndSetCustomerFields(teacherDTO, it.customFields)
            teacherDTO
        }
    }

    fun delete(id: Long): Boolean {
        umsClient.deleteUser(id)
        return true
    }

}