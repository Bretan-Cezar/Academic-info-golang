package com.formula1.academicinfo.model.composite

import javax.persistence.Column
import javax.persistence.Embeddable
import java.io.Serializable

@Embeddable
class OptionalsSelectionId(

    @Column(name = "student_id", nullable = false)
    var studentId: Int = 0,

    @Column(name = "optional_discipline_id", nullable = false)
    var oDisciplineId: Int = 0

): Serializable
