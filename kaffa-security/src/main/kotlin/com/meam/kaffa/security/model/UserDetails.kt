package com.meam.kaffa.security.model

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Data
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Data
@lombok.Builder
class UserDetails(
    val dbUsername: String,
    val dbEnabled: Boolean = true,
    val dbAuthorities: MutableSet<String>,
    val email: String?,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val organizationId: Long?,
    val gender: String?,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.dbAuthorities.map { SimpleGrantedAuthority(it) }.toMutableList()
    }

    @JsonIgnore
    override fun getPassword(): String {
        return "";
    }

    override fun getUsername() = this.dbUsername

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = this.dbEnabled
}