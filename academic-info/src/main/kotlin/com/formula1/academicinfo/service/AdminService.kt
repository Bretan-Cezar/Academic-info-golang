package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.AllStudentsResultDto
import com.formula1.academicinfo.dtos.StudentResultDto
import com.formula1.academicinfo.dtos.YearStudentsDto

interface AdminService {
    fun getStudentsByResults(username: String) : List<StudentResultDto>
    fun checkIfUserIsAdmin(username: String): Boolean
    fun getAllStudentsByResults(username: String) : List<AllStudentsResultDto>
    fun getStudentsForEachYearByResult(username: String) : List<YearStudentsDto>
}