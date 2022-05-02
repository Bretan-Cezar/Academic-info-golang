package com.formula1.academicinfo.model
import javax.persistence.*

@Entity
@Table(name = "discipline")
class Discipline(
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discipline_id", nullable = false)
    var disciplineId: Int = 0

    @Column(name = "discipline_name", nullable = false)
    var disciplineName: String = ""

    @Column(name = "credit_count", nullable = false)
    var creditCount: Int = 0

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    var teacherDiscipline: Teacher? = null

    @OneToOne(mappedBy = "mandatoryDiscipline", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var mandatoryDiscipline: MandatoryDiscipline? = null

    @OneToOne(mappedBy = "optionalDiscipline", cascade = [CascadeType.ALL], fetch = FetchType.LAZY )
    var optionalDiscipline: OptionalDiscipline? = null

    @OneToMany(mappedBy = "gradeDiscipline")
    var grades: MutableSet<Grade> = mutableSetOf()
}
