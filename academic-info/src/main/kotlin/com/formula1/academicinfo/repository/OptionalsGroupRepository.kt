package com.formula1.academicinfo.repository
import com.formula1.academicinfo.model.OptionalsGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OptionalsGroupRepository: JpaRepository<OptionalsGroup, Int> {

    @Query("SELECT count(o) > 0 FROM OptionalsGroup o WHERE o.oGroupId=:oid")
    fun existsOptionalsGroupByOGroupId(@Param("oid") id: Int): Boolean

}