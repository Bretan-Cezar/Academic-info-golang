package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.OptionalsSelection
import org.springframework.data.jpa.repository.JpaRepository

interface OptionalsSelectionRepository: JpaRepository<OptionalsSelection, Int> {
}