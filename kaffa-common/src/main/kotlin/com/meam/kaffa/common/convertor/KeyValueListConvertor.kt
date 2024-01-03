package com.meam.kaffa.common.convertor

import com.fasterxml.jackson.databind.ObjectMapper
import com.meam.kaffa.common.model.KeyValue
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import lombok.RequiredArgsConstructor

@Converter
@RequiredArgsConstructor
class KeyValueListConvertor(private val objectMapper: ObjectMapper) : AttributeConverter<MutableList<KeyValue>, String> {


    override fun convertToDatabaseColumn(attribute: MutableList<KeyValue>?): String? {
        return attribute?.let { objectMapper.writeValueAsString(it) }
    }

    override fun convertToEntityAttribute(dbData: String?): MutableList<KeyValue>? {
        return dbData?.let {
            val listType =
                objectMapper.typeFactory.constructCollectionType(MutableList::class.java, KeyValue::class.java)
            objectMapper.readValue(it, listType)
        }
    }
}