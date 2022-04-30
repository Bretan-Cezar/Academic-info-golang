package com.formula1.academicinfo.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "users")
class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId : Int = 0

    @Column(name = "full_name")
    var fullName: String = ""

    @Column(name = "password")
    private var password: String = ""

    @Column(name = "cnp")
    var cnp: String = ""

    @Column(name = "date_of_birth")
    var dateOfBirth: LocalDate = LocalDate.now()

    @Column(name = "email")
    var email: String = ""

    @Column(name = "phone_number")
    var phoneNumber: String = ""

    @Column(name = "username")
    private var username: String = ""
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    fun setPassword(newPassword: String) {
        password = newPassword
    }

    fun setUsername(newUsername : String) {
        username = newUsername
    }
}