package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.User
import com.formula1.academicinfo.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    fun save(user : User) : User {
        return userRepository.save(user)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { userRepository.findByUsername(it) }
        if (user != null) {
            return user
        }
        return User()
    }
}