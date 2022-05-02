package com.example.demo.repository
import com.example.demo.domain.Faculty
import org.springframework.data.jpa.repository.JpaRepository

interface FacultyRepository: JpaRepository<Faculty, Int> {
}