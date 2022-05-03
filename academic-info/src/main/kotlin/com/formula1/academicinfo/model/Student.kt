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
    var studentUser: User? = null

    @Column(name = "group", nullable = false)
    var group: String = ""

    @OneToMany(mappedBy = "optionalsSelectionStudent")
    var optionalsSelections: MutableSet<OptionalsSelection> = mutableSetOf()

    @OneToMany(mappedBy = "gradeStudent")
    var grades: MutableSet<Grade> = mutableSetOf()

    @OneToMany(mappedBy = "studentYos")
    var yearsOfStudyStudent: MutableSet<YearOfStudy> = mutableSetOf()

}