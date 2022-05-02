package com.example.demo.repository
import com.example.demo.domain.OptionalsGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OptionalsGroupRepository: JpaRepository<OptionalsGroup, Int> {

    @Query("SELECT count(o) > 0 FROM OptionalsGroup o WHERE o.oGroupId=:oid")
    fun existsOptionalsGroupByOGroupId(@Param("oid") id: Int): Boolean

}