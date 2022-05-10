package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.YearOfStudy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface YearOfStudyRepository: JpaRepository<YearOfStudy, Int> {

    @Query("SELECT y FROM YearOfStudy y WHERE y.studentYos.studentUser.username = :username")
    fun getYearsOfStudyByUsername(username: String): MutableSet<YearOfStudy>
}