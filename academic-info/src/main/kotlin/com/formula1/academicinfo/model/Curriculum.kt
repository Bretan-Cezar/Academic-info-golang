package com.formula1.academicinfo.model
import javax.persistence.*

@Entity
@Table(name = "curriculum")
class Curriculum{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "curriculum_id", nullable = false)
    var curriculumId: Int = 0

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "year_of_study_id")
    var curriculumYos: YearOfStudy = YearOfStudy()

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST,
            CascadeType.MERGE])
    @JoinTable(name = "curriculum_discipline_distribution",
        joinColumns = [JoinColumn(name = "curriculum_id")],
        inverseJoinColumns = [JoinColumn(name = "discipline_id")])
    var disciplines: MutableSet<Discipline> = mutableSetOf()

    fun addDiscipline(discipline: Discipline) {
        this.disciplines.add(discipline)
        discipline.curriculums.add(this)
    }

    fun removeDiscipline(disciplineId: Int) {
        val discipline = disciplines.stream().filter {
            it.disciplineId == disciplineId
        }.findFirst().orElse(null)

        discipline ?. let {
            disciplines.remove(it)
            it.curriculums.remove(this)
        }
    }
}