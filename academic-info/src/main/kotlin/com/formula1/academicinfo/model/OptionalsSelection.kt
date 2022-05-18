package com.formula1.academicinfo.model


import com.fasterxml.jackson.annotation.JsonIgnore
import com.formula1.academicinfo.model.composite.OptionalsSelectionId
import javax.persistence.*

@Entity
@Table(name = "optionals_selection")
data class OptionalsSelection(
    @EmbeddedId
    var optionalsSelectionId: OptionalsSelectionId? = null,

    @Column(name = "priority", nullable = false)
    var priority: Int = 0,

//    @ManyToOne(
//        fetch = FetchType.LAZY
//    )
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    @JsonIgnore
    var optionalsSelectionStudent: Student? = null,

//    @ManyToOne(
//        fetch = FetchType.LAZY
//    )
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @MapsId("oDisciplineId")
    @JoinColumn(name = "optional_discipline_id")
    @JsonIgnore
    var optionalsSelectionDiscipline: OptionalDiscipline? = null,
) {
    constructor(student: Student, optionalDiscipline: OptionalDiscipline) : this() {
        optionalsSelectionStudent = student
        optionalsSelectionDiscipline = optionalDiscipline
        optionalsSelectionId = OptionalsSelectionId(student.studentId, optionalDiscipline.oDisciplineId)
    }

}



