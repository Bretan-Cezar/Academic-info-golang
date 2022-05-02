package com.formula1.academicinfo.model

import com.formula1.academicinfo.model.Curriculum
import javax.persistence.*


@Entity
@Table(name = "year_of_study")
class YearOfStudy(){

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "year_of_study_id")
    var yosId: Int = 0

    @Column(name = "year_number")
    var yearNo: Int =  0

    @Column(name = "study_level")
    var studyLevel: String = ""

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    var facultyYos: Faculty? = null

    @OneToMany(mappedBy = "yosOptionalsGroup")
    var optionalsGroups: MutableSet<OptionalsGroup> = mutableSetOf()

    @OneToMany(mappedBy = "yosCurriculum")
    var curriculums: MutableSet<Curriculum> = mutableSetOf()

}