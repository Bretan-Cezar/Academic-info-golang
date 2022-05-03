package com.formula1.academicinfo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.formula1.academicinfo.model.Curriculum
import javax.persistence.*


@Entity
@Table(name = "year_of_study")
class YearOfStudy(){

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "year_of_study_id", nullable = false)
    var yosId: Int = 0

    @Column(name = "year_number", nullable = false)
    var yearNo: Int =  0

    @Column(name = "study_level", nullable = false)
    var studyLevel: String = ""

    @Column(name = "specialization", nullable = false)
    var specialization: String = ""

    @Column(name = "funding_level", nullable = false)
    var fundingLevel: String  = ""

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    var facultyYos: Faculty? = null

    @OneToOne(mappedBy = "curriculumYos", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    var curriculum: Curriculum? = null

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    var studentYos: Student? = null

}