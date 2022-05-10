package com.formula1.academicinfo.model
import com.fasterxml.jackson.annotation.JsonIgnore
import com.formula1.academicinfo.model.Curriculum
import javax.persistence.*

@Entity
@Table(name = "student")
class Student(){
    @Id
    @Column(name = "student_id", nullable = false)
    var studentId: Int = 0

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "student_id")
    @JsonIgnore
    var studentUser: User = User()

    @Column(name = "group", nullable = false)
    var group: String = ""

    @OneToMany(mappedBy = "optionalsSelectionStudent")
    var optionalsSelections: MutableSet<OptionalsSelection> = mutableSetOf()

    @OneToMany(mappedBy = "gradeStudent")
    @JsonIgnore
    var grades: MutableSet<Grade> = mutableSetOf()

    @OneToMany(mappedBy = "studentYos")
    @JsonIgnore
    var yearsOfStudyStudent: MutableSet<YearOfStudy> = mutableSetOf()

    fun addOptional(optional: OptionalDiscipline, priority: Int) {
        val selection = OptionalsSelection()
        selection.optionalsSelectionId?. let()
        {
            it.studentId = this.studentId
            it.oDisciplineId = optional.oDisciplineId
        }
        selection.priority = priority
        optionalsSelections.add(selection)
        optional.optionalsSelections.add(selection)
    }

}