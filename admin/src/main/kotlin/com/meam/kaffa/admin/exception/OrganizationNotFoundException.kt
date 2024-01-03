package com.meam.kaffa.admin.exception

class OrganizationNotFoundException(val code: String = "ORG-001", override val message: String = "Organization not found") : RuntimeException()