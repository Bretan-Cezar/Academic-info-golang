package com.example.demo.repository
import com.example.demo.domain.OptionalsSelection
import org.springframework.data.jpa.repository.JpaRepository

interface OptionalsSelectionRepository: JpaRepository<OptionalsSelection, Int> {
}