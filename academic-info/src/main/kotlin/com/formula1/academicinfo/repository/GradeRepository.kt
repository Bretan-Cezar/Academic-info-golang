package com.example.demo.repository
import com.example.demo.domain.Grade
import org.springframework.data.jpa.repository.JpaRepository

interface GradeRepository: JpaRepository<Grade, Int> {
}