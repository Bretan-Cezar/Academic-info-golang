package com.example.demo.repository
import com.example.demo.domain.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Int> {
}