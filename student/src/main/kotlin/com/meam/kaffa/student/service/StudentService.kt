package com.meam.kaffa.student.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.meam.kaffa.common.dto.admin.organization.OrganizationDTO
import com.meam.kaffa.common.dto.student.StudentDTO
import com.meam.kaffa.common.dto.ums.ContactNumbers
import com.meam.kaffa.common.enumrate.UserType
import com.meam.kaffa.common.events.MailNotificationEvent
import com.meam.kaffa.common.events.NotificationType
import com.meam.kaffa.common.model.KeyValue
import com.meam.kaffa.security.util.SecurityHelper
import com.meam.kaffa.student.client.AdminClient
import com.meam.kaffa.student.client.UMSClient
import com.meam.kaffa.student.config.ConfigProperties
import com.meam.kaffa.student.constants.StudentCustomeFields
import com.meam.kaffa.student.mapper.StudentMapper
import lombok.extern.slf4j.Slf4j
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Slf4j
class StudentService(
    private val umsClient: UMSClient,
    private val adminClient: AdminClient,
    private val configProperties: ConfigProperties,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val objectMapper: ObjectMapper,
    private val studentMapper: StudentMapper
) {
    fun create(studentDTO: StudentDTO): StudentDTO {
        return studentDTO
            .let { studentMapper.toUserDTO(it) }
            .let {
                SecurityHelper.sessionUser?.let { user -> it.organizationId = user.organizationId }
                it.userType = UserType.STUDENT
                it.customFields = mapAndSetCustomerFields(studentDTO)
                it
            }.let { umsClient.createUser(it) }
            .let {
                val studentDTO1 = studentMapper.toStudentDTO(it)
                mapAndSetCustomerFields(studentDTO1, it.customFields)
                studentDTO1
            }
    }

    private fun mapAndSetCustomerFields(studentDTO: StudentDTO): MutableList<KeyValue> {
        val customFields = mutableListOf(
            KeyValue(key = StudentCustomeFields.ROLL_NUMBER, value = studentDTO.rollNumber),
            KeyValue(key = StudentCustomeFields.GRADE, value = studentDTO.grade),
            KeyValue(key = StudentCustomeFields.DIVISION, value = studentDTO.division),
            KeyValue(key = StudentCustomeFields.REGISTRATION_DATE, value = studentDTO.registrationDate.toString())
        )

        studentDTO.fatherName?.let { customFields.add(KeyValue(key = StudentCustomeFields.FATHER_NAME, value = it)) }
        studentDTO.motherName?.let { customFields.add(KeyValue(key = StudentCustomeFields.MOTHER_NAME, value = it)) }
        studentDTO.guardianName?.let {
            customFields.add(
                KeyValue(
                    key = StudentCustomeFields.GUARDIAN_NAME,
                    value = it
                )
            )
        }
        studentDTO.contactNumbers?.map {
            if (it.type == StudentCustomeFields.FATHER_MOBILE_NUMBER) {
                customFields.add(KeyValue(key = StudentCustomeFields.FATHER_MOBILE_NUMBER, value = it.number))
            } else if (it.type == StudentCustomeFields.MOTHER_MOBILE_NUMBER) {
                customFields.add(KeyValue(key = StudentCustomeFields.MOTHER_MOBILE_NUMBER, value = it.number))
            } else {
                customFields.add(KeyValue(key = StudentCustomeFields.GUARDIAN_MOBILE_NUMBER, value = it.number))
            }
        }

        studentDTO.nationality?.let { customFields.add(KeyValue(key = StudentCustomeFields.NATIONALITY, value = it)) }
        studentDTO.languageSpoken?.let {
            customFields.add(
                KeyValue(
                    key = StudentCustomeFields.LANGUAGE_SPOKEN,
                    value = it
                )
            )
        }
        return customFields;
    }

    private fun mapAndSetCustomerFields(
        studentDTO: StudentDTO,
        customFields: MutableList<KeyValue>?
    ) {
        customFields?.let {
            val contactNumbers = mutableListOf<ContactNumbers>()
            studentDTO.contactNumbers = contactNumbers
            customFields.map {
                when (it.key) {
                    StudentCustomeFields.ROLL_NUMBER -> studentDTO.rollNumber = it.value
                    StudentCustomeFields.GRADE -> studentDTO.grade = it.value
                    StudentCustomeFields.DIVISION -> studentDTO.division = it.value
                    StudentCustomeFields.REGISTRATION_DATE -> studentDTO.registrationDate =
                        LocalDateTime.parse(it.value)

                    StudentCustomeFields.FATHER_NAME -> studentDTO.fatherName = it.value
                    StudentCustomeFields.MOTHER_NAME -> studentDTO.motherName = it.value
                    StudentCustomeFields.GUARDIAN_NAME -> studentDTO.guardianName = it.value
                    StudentCustomeFields.FATHER_MOBILE_NUMBER -> studentDTO.contactNumbers!!.add(
                        ContactNumbers(
                            type = StudentCustomeFields.FATHER_MOBILE_NUMBER,
                            number = it.value
                        )
                    )

                    StudentCustomeFields.MOTHER_MOBILE_NUMBER -> studentDTO.contactNumbers!!.add(
                        ContactNumbers(
                            type = StudentCustomeFields.MOTHER_MOBILE_NUMBER,
                            number = it.value
                        )
                    )

                    StudentCustomeFields.GUARDIAN_MOBILE_NUMBER -> studentDTO.contactNumbers!!.add(
                        ContactNumbers(
                            type = StudentCustomeFields.GUARDIAN_MOBILE_NUMBER,
                            number = it.value
                        )
                    )

                    StudentCustomeFields.NATIONALITY -> studentDTO.nationality = it.value
                    StudentCustomeFields.LANGUAGE_SPOKEN -> studentDTO.languageSpoken = it.value
                    else -> {}
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

    fun update(id: Long, studentDTO: StudentDTO): StudentDTO {
        return studentDTO
            .let { studentMapper.toUserDTO(it) }
            .let {
                SecurityHelper.sessionUser?.let { user -> it.organizationId = user.organizationId }
                it.userType = UserType.STUDENT
                it.id = id
                it.customFields = mapAndSetCustomerFields(studentDTO)
                it
            }.let { umsClient.update(id, it) }
            .let {
                val studentDTO1 = studentMapper.toStudentDTO(it)
                mapAndSetCustomerFields(studentDTO1, it.customFields)
                studentDTO1
            }
    }

    fun getUser(id: Long): StudentDTO {
        return umsClient.getUser(id).let {
            val studentDTO = studentMapper.toStudentDTO(it)
            mapAndSetCustomerFields(studentDTO, it.customFields)
            studentDTO
        }
    }

    fun delete(id: Long): Boolean {
        umsClient.deleteUser(id)
        return true
    }

}