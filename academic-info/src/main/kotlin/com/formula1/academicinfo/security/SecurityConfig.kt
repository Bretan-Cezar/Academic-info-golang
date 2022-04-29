package com.formula1.academicinfo.security

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig(private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
                     private val userDetailsService: UserDetailsService,
                     private val jwtFilter: JwtFilter) : WebSecurityConfigurerAdapter() {
    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity?) {
        http?.cors()?.and()?.csrf()?.disable()

        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http?.exceptionHandling()?.authenticationEntryPoint(jwtAuthenticationEntryPoint)

        http?.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        http?.authorizeRequests()
            ?.antMatchers("/auth/**")?.permitAll()
            ?.anyRequest()?.authenticated()
    }
}