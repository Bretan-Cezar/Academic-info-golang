package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Faculty
import org.springframework.data.jpa.repository.JpaRepository

interface FacultyRepository: JpaRepository<Faculty, Int> {
}