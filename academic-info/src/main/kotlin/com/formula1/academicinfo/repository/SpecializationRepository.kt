package com.example.demo.repository
import com.example.demo.domain.Specialization
import org.springframework.data.jpa.repository.JpaRepository

interface SpecializationRepository: JpaRepository<Specialization, Int> {
}