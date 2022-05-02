package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Grade
import org.springframework.data.jpa.repository.JpaRepository

interface GradeRepository: JpaRepository<Grade, Int> {
}