package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.User
import org.springframework.security.core.userdetails.UserDetails

interface UserService {
    fun update(email: String, phone: String, id: Int): Int

    fun getUser(id: Int): User?

    fun save(user : User) : User

}