package com.formula1.academicinfo.model
import javax.persistence.*

@Entity
@Table(name = "curriculum")
class Curriculum(){

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "curriculum_id", nullable = false)
    var curriculumId: Int = 0

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "year_of_study_id")
    var curriculumYos: YearOfStudy? = null

    @ManyToMany(mappedBy = "curriculums", fetch = FetchType.LAZY)
    var disciplines: MutableSet<Discipline> = mutableSetOf()

}