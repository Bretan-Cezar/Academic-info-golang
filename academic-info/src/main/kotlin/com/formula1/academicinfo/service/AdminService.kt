package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.StudentResultDto

interface AdminService {
    fun getStudentsByResults(adminId : Int) : List<StudentResultDto>

    fun checkIfUserIsAdmin(username: String): Boolean
}