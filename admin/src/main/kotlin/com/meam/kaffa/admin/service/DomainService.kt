package com.meam.kaffa.admin.service

import com.meam.kaffa.admin.repository.ModuleRepository
import com.meam.kaffa.common.model.KeyValue
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class DomainService(private val moduleRepository: ModuleRepository) {
    fun getModules() = moduleRepository.findByActiveTrue().map { KeyValue(it.code, it.name) }
}