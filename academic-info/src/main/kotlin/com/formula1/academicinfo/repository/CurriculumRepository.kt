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

    @Query("select c " +
            "from Curriculum c join YearOfStudy yos on c.curriculumYos.yosId = yos.yosId " +
            "join Student s on s.studentId = yos.studentYos.studentId " +
            "where s.studentId = :studentId")
    fun getCurriculumByStudentId(@Param("studentId") studentId: Int) : List<Curriculum>

    @Query("select count(d) " +
            "from Curriculum c join " +
            "c.disciplines d " +
            "where d.isOptional = true and c.curriculumId = :curriculumId")
    fun getOptionalsCount(@Param("curriculumId") curriculumId: Int) : Int

    @Query("select c " +
            "from Discipline d join Teacher t on d.teacherDiscipline.teacherId = t.teacherId " +
            "join Faculty f on t.facultyTeacher.facultyId = f.facultyId " +
            "join YearOfStudy yos on yos.facultyYos.facultyId = f.facultyId " +
            "join Curriculum c on yos.curriculum.curriculumId = c.curriculumId " +
            "where d.disciplineId = :disciplineId")
    fun findCurriculumByDisciplineId(@Param("disciplineId") disciplineId: Int) : List<Curriculum>
}