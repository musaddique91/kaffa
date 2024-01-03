package com.meam.kaffa.admin.repository

import com.meam.kaffa.admin.entity.Module
import org.springframework.data.jpa.repository.JpaRepository

interface ModuleRepository : JpaRepository<Module, String> {
    fun findByActiveTrue(): MutableList<Module>
}