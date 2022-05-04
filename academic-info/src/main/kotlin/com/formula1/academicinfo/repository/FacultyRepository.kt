package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Faculty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FacultyRepository: JpaRepository<Faculty, Int> {

    @Query("SELECT f FROM Faculty f where f.facultyId=:id")
    fun getFacultiesByFacultyId(@Param("id") id: Int):Faculty

}