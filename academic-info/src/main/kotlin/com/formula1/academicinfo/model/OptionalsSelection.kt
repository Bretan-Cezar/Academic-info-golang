package com.formula1.academicinfo.model


import com.formula1.academicinfo.model.composite.OptionalsSelectionId
import javax.persistence.*

@Entity
@Table(name = "optionals_selection")
class OptionalsSelection(){

    @EmbeddedId
    var optionalsSelectionId: OptionalsSelectionId? = null

    @Column(name = "priority", nullable = false)
    var priority: Int = 0

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    var optionalsSelectionStudent: Student? = null

    @ManyToOne
    @MapsId("oDisciplineId")
    @JoinColumn(name = "optional_discipline_id")
    var optionalsSelectionDiscipline: OptionalDiscipline? = null
}