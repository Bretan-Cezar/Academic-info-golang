package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface StudentRepository: JpaRepository<Student, Int> {
//    @Query("SELECT s.curriculums FROM Student s WHERE s.studentId=:sid")
//    fun getCurriculumsByStudentId(@Param("sid") id: Int): MutableSet<Curriculum>

    @Query("SELECT s FROM Student s WHERE s.studentId=:sid")
    fun getStudentByStudentId(@Param("sid") id: Int): Student

    @Query(
        "select s " +
        "from Student s join User u on s.studentId = u.userId " +
        "where u.username = :username")
    fun getStudentByUsername(@Param("username") username: String) : Student?
}