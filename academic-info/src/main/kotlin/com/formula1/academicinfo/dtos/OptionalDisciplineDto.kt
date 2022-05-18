package com.formula1.academicinfo.dtos
class OptionalDisciplineDto(

) {
    var oDisciplineId: Int = 0

    var disciplineName: String = ""

    var maxAttendants: Int = 0

    var creditCount: Int = 0

    var teacherName: String = ""


    constructor(oDisciplineId: Int, disciplineName: String, maxAttendants: Int, creditCount: Int, teacherName: String) : this() {
        this.oDisciplineId = oDisciplineId
        this.disciplineName = disciplineName
        this.maxAttendants = maxAttendants
        this.creditCount = creditCount
        this.teacherName = teacherName

    }
}