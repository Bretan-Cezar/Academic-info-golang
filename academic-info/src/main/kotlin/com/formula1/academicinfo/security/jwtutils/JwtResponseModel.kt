package com.formula1.academicinfo.security.jwtutils

import java.io.Serializable

class JwtResponseModel(token: String) : Serializable {
    var token: String = token
        private set
}