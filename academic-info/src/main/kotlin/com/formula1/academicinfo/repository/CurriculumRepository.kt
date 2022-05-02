package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Curriculum
import org.springframework.data.jpa.repository.JpaRepository

interface CurriculumRepository: JpaRepository<Curriculum, Int> {

}