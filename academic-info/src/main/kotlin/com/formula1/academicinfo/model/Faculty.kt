package com.formula1.academicinfo.model

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    @JoinColumn(name = "chief_teacher_id")
    var teacherFaculty: Teacher? = null

    @OneToMany(mappedBy = "facultyTeacher")
    // ??
    @JsonIgnore
    var teachers: MutableSet<Teacher> = mutableSetOf()

    @OneToMany(mappedBy = "facultyYos")
    // ??
    @JsonIgnore
    var yos: MutableSet<YearOfStudy> = mutableSetOf()

    @OneToMany(mappedBy = "facultyAdmin")
    @JsonIgnore
    var admins: MutableSet<Admin> = mutableSetOf()
}