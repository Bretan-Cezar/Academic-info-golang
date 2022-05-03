package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService {
    fun update(email: String, phone: String, username: String): Int

    fun getUser(id: Int): User?

    fun save(user : User) : User
}