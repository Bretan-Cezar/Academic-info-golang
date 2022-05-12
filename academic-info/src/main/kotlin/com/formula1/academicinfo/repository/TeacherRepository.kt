package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TeacherRepository: JpaRepository<Teacher, Int> {

//    @Query("SELECT count(t) > 0 FROM Teacher t where t.teacherId=:tid")
//    fun existsTeacherByTeacherId(@Param("tid") id: Int): Boolean

    @Query("SELECT t FROM Teacher t where t.teacherId=:tid")
    fun findTeacherByTeacherId(@Param("tid") id: Int): Teacher

    @Query("SELECT t FROM Teacher t where t.facultyTeacher.facultyId=:fid")
    fun findTeachersByFacultyId(@Param("fid") id: Int): MutableSet<Teacher>
}