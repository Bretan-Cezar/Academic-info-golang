package com.formula1.academicinfo.model

import javax.persistence.*

@Entity
@Table(name = "faculty")
class Faculty(){

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "faculty_id", nullable = false)
    var facultyId: Int = 0

    @Column(name = "faculty_name", nullable = false)
    var facultyName: String = ""

    @Column(name = "address", nullable = false)
    var address: String = ""

    @Column(name = "email", nullable = false)
    var email: String = ""

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String = ""

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "chief_teacher_id")
    var teacherFaculty: Teacher? = null

    @OneToMany(mappedBy = "facultyTeacher")
    var teachers: MutableSet<Teacher> = mutableSetOf()

    @OneToMany(mappedBy = "facultyYos")
    var yos: MutableSet<YearOfStudy> = mutableSetOf()

}