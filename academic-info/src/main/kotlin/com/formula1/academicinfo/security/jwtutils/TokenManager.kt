package com.formula1.academicinfo.security.jwtutils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*

@Component
class TokenManager : Serializable {
    val validity = 60 * 60 * 60 * 24 * 7

    @Value("\${secret}")
    var jwtSecret: String = ""

    fun generateJwtToken(user: UserDetails) : String {
        val claims = Jwts.claims().setSubject(user.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + validity * 1000))
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    fun validateJwtToken(token: String?, user: UserDetails) : Boolean {
        return getUsernameFromToken(token) == user.username && !isTokenExpired(token)
    }

    fun getUsernameFromToken(token: String?) : String {
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
        return claims.subject
    }

    fun isTokenExpired(token: String?) : Boolean {
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body
        return claims.expiration.before(Date())
    }
}