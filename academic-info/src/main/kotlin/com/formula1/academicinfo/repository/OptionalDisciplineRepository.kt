package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.OptionalDiscipline
import org.springframework.data.jpa.repository.JpaRepository

interface OptionalDisciplineRepository: JpaRepository<OptionalDiscipline, Int> {
}