package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.AllStudentsResultDto
import com.formula1.academicinfo.dtos.StudentResultDto
import com.formula1.academicinfo.dtos.YearStudentsDto

interface AdminService {
    fun getStudentsByResults(adminId : Int) : List<StudentResultDto>
    fun checkIfUserIsAdmin(username: String): Boolean
    fun getAllStudentsByResults(adminId: Int) : List<AllStudentsResultDto>
    fun getStudentsForEachYearByResult(adminId: Int) : List<YearStudentsDto>
}