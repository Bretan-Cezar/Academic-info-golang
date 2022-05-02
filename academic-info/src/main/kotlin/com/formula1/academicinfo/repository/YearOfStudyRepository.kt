package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.YearOfStudy
import org.springframework.data.jpa.repository.JpaRepository

interface YearOfStudyRepository: JpaRepository<YearOfStudy, Int> {
}