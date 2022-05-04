package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.OptionalDiscipline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface OptionalDisciplineRepository: JpaRepository<OptionalDiscipline, Int> {
//    @Query("SELECT d FROM OptionalDiscipline d WHERE d.optionalDisciplineCurriculum=:cid")
//    fun getOptionalDisciplinesByCurriculumId(@Param("cid") id: Int): List<OptionalDiscipline>

    @Query("SELECT d From OptionalDiscipline d WHERE d.oDisciplineId=:id")
    fun getOptionalDisciplineByODisciplineId(@Param ("id") id: Int): OptionalDiscipline


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("Update OptionalDiscipline o set o.approved=:approved, o.maxAttendants=:maxAttendants WHERE o.oDisciplineId=:oid")
    fun update(@Param("oid") oid: Int, @Param("approved") approved: Boolean, @Param("maxAttendants") maxAttendants: Int): Int
}
