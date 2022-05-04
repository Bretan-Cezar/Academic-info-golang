package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Curriculum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CurriculumRepository: JpaRepository<Curriculum, Int> {
    @Query("SELECT c FROM Curriculum c WHERE c.curriculumId=:id")
    fun findCurriculumById(@Param("id") id: Int): Curriculum

    @Query("SELECT c FROM Curriculum c WHERE c.curriculumYos.yosId=:yosId")
    fun findCurriculumByCurriculumYos(@Param("yosId") yosId: Int): Curriculum
}