package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.MandatoryDiscipline
import org.springframework.data.jpa.repository.JpaRepository

interface MandatoryDisciplineRepository: JpaRepository<MandatoryDiscipline, Int> {
}