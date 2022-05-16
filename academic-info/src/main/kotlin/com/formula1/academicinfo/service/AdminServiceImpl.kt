package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.StudentResultDto
import com.formula1.academicinfo.repository.AdminRepository
import com.formula1.academicinfo.repository.StudentRepository
import com.formula1.academicinfo.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class AdminServiceImpl(
    val adminRepository: AdminRepository,
    val studentRepository: StudentRepository,
    val userRepository: UserRepository) : AdminService {
    override fun getStudentsByResults(adminId: Int) : List<StudentResultDto> {
        val initial = adminRepository.getStudentsByResults(adminId)
        return initial.stream().map {
            val student = studentRepository.getStudentByStudentId(it.studentId)
            val user = userRepository.findUserById(it.studentId)
            return@map StudentResultDto(user.fullName, student.group, it.gradeAverage)
        }.toList()
    }

    override fun checkIfUserIsAdmin(username: String): Boolean {
        val a = adminRepository.findAdminByUsername(username)
        return a != null
    }
}