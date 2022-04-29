package com.formula1.academicinfo.security.jwtutils

import java.io.Serializable

class JwtRequestModel(val username: String, val password: String) : Serializable