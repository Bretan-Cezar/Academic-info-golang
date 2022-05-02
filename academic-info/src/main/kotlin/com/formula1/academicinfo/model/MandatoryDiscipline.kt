package com.formula1.academicinfo.model
import com.formula1.academicinfo.model.Curriculum
import javax.persistence.*

@Entity
@Table(name = "mandatory_discipline")
class MandatoryDiscipline(){

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "mandatory_discipline_id", nullable = false)
    var mDisciplineId: Int = 0

    @Column(name = "semester_number", nullable = false)
    var semesterNo: Int = 0

    @ManyToOne
    @JoinColumn(name = "curriculum_id", nullable = false)
    var mandatoryDisciplineCurriculum: Curriculum? = null

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "mandatory_discipline_id")
    var mandatoryDiscipline: Discipline? = null
}