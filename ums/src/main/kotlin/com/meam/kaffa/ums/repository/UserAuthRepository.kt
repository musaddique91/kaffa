package com.meam.kaffa.ums.repository

import com.meam.kaffa.ums.entity.UserAuth
import org.springframework.data.jpa.repository.JpaRepository

interface UserAuthRepository : JpaRepository<UserAuth, Long> {
    fun findByUsername(username: String): UserAuth?
}