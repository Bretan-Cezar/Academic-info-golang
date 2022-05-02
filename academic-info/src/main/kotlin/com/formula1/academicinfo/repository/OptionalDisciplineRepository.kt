package com.example.demo.repository
import com.example.demo.domain.OptionalDiscipline
import org.springframework.data.jpa.repository.JpaRepository

interface OptionalDisciplineRepository: JpaRepository<OptionalDiscipline, Int> {
}