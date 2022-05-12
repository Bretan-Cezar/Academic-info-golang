package com.formula1.academicinfo.model
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "student")
class Student{
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

    @OneToMany(mappedBy = "optionalsSelectionStudent", cascade = [CascadeType.ALL])
    var optionalsSelections: MutableSet<OptionalsSelection> = mutableSetOf()

    @OneToMany(mappedBy = "gradeStudent")
    @JsonIgnore
    var grades: MutableSet<Grade> = mutableSetOf()

    @OneToMany(mappedBy = "studentYos")
    @JsonIgnore
    var yearsOfStudyStudent: MutableSet<YearOfStudy> = mutableSetOf()

    fun createOptional(optional: OptionalDiscipline, priority: Int) : OptionalsSelection {
        val selection = OptionalsSelection(this, optional)
        selection.priority = priority

        return selection
    }

    fun removeOptional(optional: OptionalDiscipline) {
        optionalsSelections.forEach()
        {
            if (it.optionalsSelectionStudent == this && it.optionalsSelectionDiscipline == optional)
            {
                optionalsSelections.remove(it)
                it.optionalsSelectionDiscipline!!.optionalsSelections.remove(it)
                it.optionalsSelectionStudent = null
                it.optionalsSelectionDiscipline = null
            }
        }
    }

}