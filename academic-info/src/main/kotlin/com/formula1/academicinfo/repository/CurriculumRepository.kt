package com.example.demo.repository
import com.example.demo.domain.Curriculum
import org.springframework.data.jpa.repository.JpaRepository

interface CurriculumRepository: JpaRepository<Curriculum, Int> {

}