package com.formula1.academicinfo.model.composite

import javax.persistence.Column
import javax.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class GradeId(

    @Column(name = "student_id", nullable = false)
    var studentId: Int = 0,

    @Column(name = "discipline_id", nullable = false)
    var disciplineId: Int = 0

): Serializable
