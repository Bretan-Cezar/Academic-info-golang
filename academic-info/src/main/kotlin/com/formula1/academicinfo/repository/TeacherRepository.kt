package com.example.demo.repository
import com.example.demo.domain.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TeacherRepository: JpaRepository<Teacher, Int> {

    @Query("SELECT count(t) > 0 FROM Teacher t where t.teacherId=:tid")
    fun existsTeacherByTeacherId(@Param("tid") id: Int): Boolean
}