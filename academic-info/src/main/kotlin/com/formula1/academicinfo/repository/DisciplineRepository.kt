package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Curriculum
import com.formula1.academicinfo.model.Discipline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DisciplineRepository: JpaRepository<Discipline, Int> {
//    @Query("SELECT count(d) > 0 FROM Discipline d WHERE d.disciplineId=:did")
//    fun existsDisciplineByDisciplineId(@Param("did") id: Int): Boolean

    @Query("SELECT d FROM Discipline d WHERE d.disciplineName=:uName")
    fun findDisciplineByDisciplineName(@Param("uName") name: String): Discipline?

    @Query("SELECT d FROM Discipline d WHERE d.disciplineId=:id")
    fun findDisciplineByDisciplineId(@Param("id") id: Int): Discipline

    @Query("select d " +
            "from Discipline d join Teacher t on d.teacherDiscipline.teacherId = t.teacherId " +
            "where t.teacherId = :teacherId")
    fun findDisciplinesByTeacherId(@Param("teacherId") teacherId: Int) : List<Discipline>

    @Query("SELECT d FROM Discipline d WHERE :curriculum IN (d.curriculums)")
    fun findDisciplinesByCurriculum(@Param("curriculum") curriculum: Curriculum) :MutableSet<Discipline>
}