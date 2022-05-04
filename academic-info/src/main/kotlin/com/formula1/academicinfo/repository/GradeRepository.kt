package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Grade
import com.formula1.academicinfo.model.composite.GradeId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GradeRepository: JpaRepository<Grade, Int> {

    @Query("SELECT g FROM Grade g WHERE g.gradeId=:gid")
    fun findGradeByGradeId(@Param("gid") gid: GradeId): Grade?
}