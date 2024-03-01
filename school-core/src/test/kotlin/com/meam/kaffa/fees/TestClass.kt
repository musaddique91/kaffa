package com.meam.kaffa.fees

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class TestClass
fun main(args: Array<String>){
    print(BCryptPasswordEncoder().encode("admin"))
}