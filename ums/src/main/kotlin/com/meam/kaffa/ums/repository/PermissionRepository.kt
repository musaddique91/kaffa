package com.meam.kaffa.ums.repository

import com.meam.kaffa.ums.entity.Permission
import com.meam.kaffa.ums.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository : JpaRepository<Permission, String> {
}