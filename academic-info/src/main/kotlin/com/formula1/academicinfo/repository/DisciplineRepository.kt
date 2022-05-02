package com.example.demo.repository
import com.example.demo.domain.Discipline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DisciplineRepository: JpaRepository<Discipline, Int> {

    @Query("SELECT count(d) > 0 FROM Discipline d WHERE d.disciplineId=:did")
    fun existsDisciplineByDisciplineId(@Param("did") id: Int): Boolean

}