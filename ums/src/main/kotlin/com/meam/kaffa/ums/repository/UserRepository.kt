package com.meam.kaffa.ums.repository

import com.meam.kaffa.ums.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserAuthId(authId: Long): User?
}