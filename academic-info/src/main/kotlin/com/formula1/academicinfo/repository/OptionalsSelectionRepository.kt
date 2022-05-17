package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.OptionalsSelection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OptionalsSelectionRepository: JpaRepository<OptionalsSelection, Int> {

    @Query("select os from " +
            "OptionalsSelection os " +
            "order by os.priority")
    fun getOptionalsSelectionsOrderedByPriority() : List<OptionalsSelection>
}