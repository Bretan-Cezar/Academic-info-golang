package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Specialization
import org.springframework.data.jpa.repository.JpaRepository

interface SpecializationRepository: JpaRepository<Specialization, Int> {
}