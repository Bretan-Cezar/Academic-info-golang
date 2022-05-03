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

    @Column(name = "is_optional", nullable = false)
    var isOptional: Boolean = false

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    var teacherDiscipline: Teacher? = null

    @OneToOne(mappedBy = "optionalDiscipline", cascade = [CascadeType.ALL], fetch = FetchType.LAZY )
    var optionalDiscipline: OptionalDiscipline? = null

    @OneToMany(mappedBy = "gradeDiscipline")
    var grades: MutableSet<Grade> = mutableSetOf()

    @ManyToMany
    @JoinTable(name = "curriculum_discipline_distribution", joinColumns = [JoinColumn(name = "discipline_id")], inverseJoinColumns = [JoinColumn(name = "curriculum_id")])
    var curriculums: MutableSet<Curriculum> = mutableSetOf()
}
