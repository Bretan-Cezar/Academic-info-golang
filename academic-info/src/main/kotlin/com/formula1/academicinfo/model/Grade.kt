package com.formula1.academicinfo.model

import com.formula1.academicinfo.model.composite.GradeId
import javax.persistence.*

@Entity
//@IdClass(GradeId::class)
@Table(name = "grade")
class Grade(){

    @EmbeddedId
    var gradeId: GradeId? = null

//    @Id
//    @Column(name = "student_id", nullable = false)
//    var studentId: Int = 0
//
//    @Id
//    @Column(name = "discipline_id", nullable = false)
//    var disciplineId: Int = 0


    @Column(name = "value", nullable = false)
    var value: Int = 0


    @ManyToOne
    @JoinColumn(name = "student_id")
    @MapsId("studentId")
    var gradeStudent: Student = Student()

    @ManyToOne
    @MapsId("disciplineId")
    @JoinColumn(name = "discipline_id")
    var gradeDiscipline: Discipline = Discipline()
}