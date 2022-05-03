package com.formula1.academicinfo.security

import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.UserServiceImpl
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpHeaders
import org.springframework.security.access.AuthorizationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(private val userService: UserServiceImpl,
                private val tokenManager: TokenManager) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse,
                                  filterChain: FilterChain) {
        val requestMatcher = AntPathRequestMatcher("/auth/**")

        if (requestMatcher.matches(request)) {
            filterChain.doFilter(request, response)
            return
        }

        val tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        val username: String?
        val token: String?

        if (tokenHeader.isEmpty() || !tokenHeader.startsWith("Bearer ")) {
            logger.error("Bearer string not found in token")
            filterChain.doFilter(request, response)
            return
        }

        token = tokenHeader.substring(7)
        try {
            username = tokenManager.getUsernameFromToken(token)
        }
        catch (e : IllegalArgumentException) {
            logger.error("Unable to get JWT Token")
            throw AuthorizationServiceException("JWT Token not found")
        } catch (e : ExpiredJwtException) {
            logger.error("JWT Token has expired")
            throw AuthorizationServiceException("Token has expired")
        }

        if (SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userService.loadUserByUsername(username)

            if (tokenManager.validateJwtToken(token, userDetails)) {
                val authenticationToken = UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.authorities)
                authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        }

        filterChain.doFilter(request, response)
    }
}