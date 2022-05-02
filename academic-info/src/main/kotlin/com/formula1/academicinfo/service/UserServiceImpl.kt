package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.User
import com.formula1.academicinfo.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserDetailsService, UserService {

    override fun save(user : User) : User {
        return userRepository.save(user)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { userRepository.findByUsername(it) }
        if (user != null) {
            return user
        }
        return User()
    }

    override fun update(email: String, phone: String, id: Int): Int {
        return userRepository.update(email, id)
    }

    override fun getUser(id: Int): User? {
        return userRepository.findUserById(id)
    }

}