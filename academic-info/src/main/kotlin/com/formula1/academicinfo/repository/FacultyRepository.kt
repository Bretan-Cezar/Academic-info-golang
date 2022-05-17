package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.Faculty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FacultyRepository: JpaRepository<Faculty, Int> {
    @Query(
        "select f " +
        "from Faculty f join Teacher t on f.teacherFaculty.teacherId = t.teacherId " +
        "where t.teacherId = :chiefId"
    )
    fun findFacultyByChiefOfDepartmentId(@Param("chiefId") chiefId: Int): Faculty?
}