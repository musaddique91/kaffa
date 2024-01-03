package com.meam.kaffa.common.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import lombok.experimental.SuperBuilder
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
open class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreatedDate
    var createdAt: LocalDateTime? = null
        protected set

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
        protected set

    @CreatedBy
    var createdBy: String? = null
        protected set

    @LastModifiedBy
    var modifiedBy: String? = null
        protected set

    @Version
    var version: Int? = null
        protected set
}