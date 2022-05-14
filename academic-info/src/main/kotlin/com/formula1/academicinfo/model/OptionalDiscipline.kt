package com.formula1.academicinfo.model
import javax.persistence.*

@Entity
@Table(name = "optional_discipline")
class OptionalDiscipline() {

    @Id
//    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "optional_discipline_id", nullable = false)
    var oDisciplineId: Int = 0

    @Column(name = "max_attendants")
    var maxAttendants: Int = 0

    @Column(name = "current_attendants")
    var currentAttendants: Int = 0

    @Column(name = "approved")
    var approved: Boolean = false

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "optional_discipline_id")
    var optionalDiscipline: Discipline? = null

    @OneToMany(mappedBy = "optionalsSelectionDiscipline",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST])
    var optionalsSelections: MutableSet<OptionalsSelection> = mutableSetOf()

}