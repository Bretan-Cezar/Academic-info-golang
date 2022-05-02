package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.MandatoryDiscipline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MandatoryDisciplineRepository: JpaRepository<MandatoryDiscipline, Int> {
    @Query("SELECT d FROM MandatoryDiscipline d WHERE d.mandatoryDisciplineCurriculum=:cid")
    fun getMandatoryDisciplinesByCurriculumId(@Param("cid") id: Int): List<MandatoryDiscipline>
}