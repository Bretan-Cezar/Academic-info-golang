package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.OptionalDiscipline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OptionalDisciplineRepository: JpaRepository<OptionalDiscipline, Int> {
//    @Query("SELECT d FROM OptionalDiscipline d WHERE d.optionalDisciplineCurriculum=:cid")
//    fun getOptionalDisciplinesByCurriculumId(@Param("cid") id: Int): List<OptionalDiscipline>

    @Query("SELECT d From OptionalDiscipline d WHERE d.oDisciplineId=:id")
    fun getOptionalDisciplineByODisciplineId(@Param ("id") id: Int): OptionalDiscipline
}