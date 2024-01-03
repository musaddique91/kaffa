package com.meam.kaffa.admin.repository

import com.meam.kaffa.admin.entity.Organization
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationRepository : JpaRepository<Organization, Long>