package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table

interface Curriculum : Entity<Curriculum> {
}

object Curriculums : Table<Curriculum>("Curriculum") {

}