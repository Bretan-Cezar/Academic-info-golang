package com.example.demo.repository
import com.example.demo.domain.MandatoryDiscipline
import org.springframework.data.jpa.repository.JpaRepository

interface MandatoryDisciplineRepository: JpaRepository<MandatoryDiscipline, Int> {
}