package com.example.demo.repository
import com.example.demo.domain.YearOfStudy
import org.springframework.data.jpa.repository.JpaRepository

interface YearOfStudyRepository: JpaRepository<YearOfStudy, Int> {
}