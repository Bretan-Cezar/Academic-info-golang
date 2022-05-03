package com.formula1.academicinfo.model
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "teacher")
class Teacher(){

    @Id
    @Column(name = "teacher_id", nullable = false)
    var teacherId: Int = 0

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    @MapsId
    @JoinColumn(name ="teacher_id")
    @JsonIgnore
    var teacherUser: User? = null

    @Column(name = "degree", nullable = false)
    var degree: String = ""

    @OneToOne(mappedBy = "teacherFaculty", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var faculty: Faculty? = null

    @ManyToOne
    @JoinColumn(name="faculty_id", nullable=false)
    var facultyTeacher: Faculty? = null

    @OneToMany(mappedBy = "teacherDiscipline")
    // ??
    @JsonIgnore
    var disciplines: MutableSet<Discipline> = mutableSetOf()

}



//    constructor(teacherId: Int, facultyId: Int, degree: String) : this() {
//        this.teacherId = teacherId
//        this.degree = degree
//        this.facultyId = facultyId
//    }


