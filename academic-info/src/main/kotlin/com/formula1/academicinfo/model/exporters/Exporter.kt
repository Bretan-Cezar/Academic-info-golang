package com.formula1.academicinfo.model.exporters

import javax.servlet.http.HttpServletResponse

interface Exporter<ObjectType> {
    fun export(objects: List<ObjectType>, httpServletResponse: HttpServletResponse)
}