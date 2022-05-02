package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Int> {
}