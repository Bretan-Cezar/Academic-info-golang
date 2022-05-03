package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.User
import com.formula1.academicinfo.model.exceptions.EmailNotValidException
import com.formula1.academicinfo.model.exceptions.PhoneNumberNotValidException
import com.formula1.academicinfo.model.matchesEmail
import com.formula1.academicinfo.model.matchesPhoneNumber
import com.formula1.academicinfo.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

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

    override fun update(email: String, phone: String, username: String): Int {
        if (!email.matchesEmail())
            throw EmailNotValidException("Given email does not match the format of an email")
        if (!phone.matchesPhoneNumber())
            throw PhoneNumberNotValidException("Given phone number does not match the format of a phone number")
        return userRepository.update(email, phone, username)
    }

    override fun getUser(id: Int): User? {
        return userRepository.findUserById(id)
    }

}