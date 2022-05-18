package com.formula1.academicinfo.model
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "teacher")
class Teacher(){

    @Id
    @Column(name = "teacher_id", nullable = false)
    var teacherId: Int = 0

//    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    @MapsId
    @JoinColumn(name ="teacher_id")
    @JsonIgnore
    var teacherUser: User = User()

    @Column(name = "degree", nullable = false)
    var degree: String = ""


    ///TODO
    ///daca vrem sa fie unidirectional, lasam doar cascade = [CascadeType.ALL], ar ajuta la timpul de executie??
//    @OneToOne(mappedBy = "teacherFaculty", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @OneToOne(mappedBy = "teacherFaculty", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore
    var faculty: Faculty = Faculty()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="faculty_id", nullable=false)
    @JsonIgnore
    var facultyTeacher: Faculty? = null

    @OneToMany(mappedBy = "teacherDiscipline", fetch = FetchType.LAZY)
    @JsonIgnore
    var disciplines: MutableSet<Discipline> = mutableSetOf()

}



//    constructor(teacherId: Int, facultyId: Int, degree: String) : this() {
//        this.teacherId = teacherId
//        this.degree = degree
//        this.facultyId = facultyId
//    }


