package com.formula1.academicinfo.repository
import com.formula1.academicinfo.dtos.TeacherPerfDto
import com.formula1.academicinfo.dtos.TeacherPerformanceDto
import com.formula1.academicinfo.model.Teacher
import com.formula1.academicinfo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TeacherRepository: JpaRepository<Teacher, Int> {
    @Query("SELECT count(t) > 0 FROM Teacher t where t.teacherId=:tid")
    fun existsTeacherByTeacherId(@Param("tid") id: Int): Boolean

    @Query("SELECT t FROM Teacher t where t.teacherId=:tid")
    fun findTeacherByTeacherId(@Param("tid") id: Int): Teacher

    @Query("SELECT t FROM Teacher t where t.facultyTeacher.facultyId=:fid")
    fun findTeachersByFacultyId(@Param("fid") id: Int): Set<Teacher>

    @Query("SELECT t.teacherUser FROM Teacher t where t.facultyTeacher.facultyId=:fid")
    fun findTeachersWithName(@Param("fid") id: Int): Set<User>

    @Query(
        "select t " +
        "from Teacher t join User u on t.teacherId = u.userId " +
        "where u.username = :username"
    )
    fun findTeacherByUsername(@Param("username") username: String): Teacher?

    @Query("SELECT new com.formula1.academicinfo.dtos.TeacherPerfDto(t.teacherId, avg(g.value)) " +
            "FROM Teacher t " +
            "JOIN Faculty f on t.facultyTeacher.facultyId = f.facultyId " +
            "JOIN Discipline d ON t.teacherId = d.teacherDiscipline.teacherId " +
            "JOIN Grade g ON d.disciplineId = g.gradeDiscipline.disciplineId " +
            "WHERE f.teacherFaculty.teacherId = :chiefId " +
            "GROUP BY t.teacherId " +
            "ORDER BY avg(g.value) DESC")
    fun findBestTeachers(@Param("chiefId") chiefId: Int): Set<TeacherPerfDto>
}
