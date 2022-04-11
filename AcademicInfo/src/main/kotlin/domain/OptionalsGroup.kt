package domain

import org.ktorm.entity.Entity
import org.ktorm.schema.Table

interface OptionalsGroup : Entity<OptionalsGroup> {
}

object OptionalsGroups : Table<OptionalsGroup>("Optionals_Group") {

}