package com.formula1.academicinfo.model
import com.example.demo.domain.*
import javax.persistence.*

@Entity
@Table(name = "curriculum")
class Curriculum(){

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "curriculum_id", nullable = false)
    var curriculumId: Int = 0

    @ManyToOne
    @JoinColumn(name = "specialization_id", nullable = false)
    var specCurriculum: Specialization? = null

    @ManyToOne
    @JoinColumn(name = "year_of_study_id", nullable = false)
    var yosCurriculum: YearOfStudy? = null

    @OneToMany(mappedBy = "optionalDisciplineCurriculum")
    var optionalDisciplines: MutableSet<OptionalDiscipline> = mutableSetOf()

    @OneToMany(mappedBy = "mandatoryDisciplineCurriculum")
    var mandatoryDisciplines: MutableSet<MandatoryDiscipline> = mutableSetOf()

    @ManyToMany(mappedBy = "curriculums", fetch = FetchType.LAZY)
    var students: MutableSet<Student> = mutableSetOf()

}