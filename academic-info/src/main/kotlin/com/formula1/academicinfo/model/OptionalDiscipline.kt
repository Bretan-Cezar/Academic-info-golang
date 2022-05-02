package com.formula1.academicinfo.model
import com.formula1.academicinfo.model.Curriculum
import javax.persistence.*

@Entity
@Table(name = "optional_discipline")
class OptionalDiscipline(){

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "optional_discipline_id", nullable = false)
    var oDisciplineId: Int = 0

    @Column(name = "max_attendants", nullable = false)
    var maxAttendants: Int = 0

    @ManyToOne
    @JoinColumn(name = "curriculum_id", nullable = false)
    var optionalDisciplineCurriculum: Curriculum? = null

    @ManyToOne
    @JoinColumn(name = "optional_group_id", nullable = false)
    var optionalDisciplineGroup: OptionalsGroup? = null

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "optional_discipline_id")
    var optionalDiscipline: Discipline? = null

    @OneToMany(mappedBy = "optionalsSelectionDiscipline")
    var optionalsSelections: MutableSet<OptionalsSelection> = mutableSetOf()

    @ManyToMany(mappedBy = "optionalDisciplines", fetch = FetchType.LAZY)
    var students: MutableSet<Student> = mutableSetOf();

}