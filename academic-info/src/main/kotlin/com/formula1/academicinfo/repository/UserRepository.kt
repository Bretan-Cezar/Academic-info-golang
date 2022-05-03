package com.formula1.academicinfo.repository

import com.formula1.academicinfo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT u from User u WHERE u.userId=:id")
    fun findUserById(@Param("id") id: Int): User?

    @Query("SELECT u from User u WHERE u.username=:uName")
    fun findUserByUsername(@Param("uName") username: String): User

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE User u set u.email = :email, u.phoneNumber = :phone_number WHERE u.username=:username")
    fun update(@Param("email") email: String, @Param("phone_number") phoneNumber: String,
               @Param("username") username: String): Int

    fun findByUsername(username: String): User?
}