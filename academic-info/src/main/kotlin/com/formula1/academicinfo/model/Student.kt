package com.formula1.academicinfo.model
import com.formula1.academicinfo.model.Curriculum
import javax.persistence.*

@Entity
@Table(name = "student")
class Student(){
    @Id
    @Column(name = "student_id", nullable = false)
    var studentId: Int = 0

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    var studentUser: User? = null

    @Column(name = "group", nullable = false)
    var group: String = ""

    @Column(name = "funding_level", nullable = false)
    var fundingLevel: String = ""

//    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("students")

    @ManyToMany
    @JoinTable(name = "contract_curriculum_distribution", joinColumns = [JoinColumn(name = "student_id")], inverseJoinColumns = [JoinColumn(name = "curriculum_id")])
    var curriculums: MutableSet<Curriculum> = mutableSetOf()

//    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("students")

    @ManyToMany
    @JoinTable(name = "contract_optionals_distribution", joinColumns = [JoinColumn(name = "student_id")], inverseJoinColumns = [JoinColumn(name="optional_discipline_id")])
    var optionalDisciplines: MutableSet<OptionalDiscipline> = mutableSetOf();

    @OneToMany(mappedBy = "optionalsSelectionStudent")
    var optionalsSelections: MutableSet<OptionalsSelection> = mutableSetOf()

    @OneToMany(mappedBy = "gradeStudent")
    var grades: MutableSet<Grade> = mutableSetOf()

}