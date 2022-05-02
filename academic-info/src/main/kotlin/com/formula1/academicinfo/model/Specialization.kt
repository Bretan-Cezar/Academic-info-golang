package com.formula1.academicinfo.model
import com.formula1.academicinfo.model.Curriculum
import javax.persistence.*

@Entity
@Table(name = "specialization")
class Specialization(){

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "specialization_id", nullable = false)
    var specId: Int = 0

    @Column(name = "specialization_name", nullable = false)
    var specName: String = ""

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    var facultySpec: Faculty? = null

    @OneToMany(mappedBy = "specCurriculum")
    var curriculums: MutableSet<Curriculum> = mutableSetOf()
}