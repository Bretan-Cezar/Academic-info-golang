package com.formula1.academicinfo.model.exporters

import org.springframework.stereotype.Component

@Component
object YearStudentExporterFactory : YearStudentExporterFactoryInterface{
    override fun createFromType(type: String): YearStudentExporter =
        when(type) {
            "pdf" -> YearStudentPdfExporter.getInstance()
            "excel" -> YearStudentExcelExporter.getInstance()
            else -> throw Exception("Exporter type not supported!")
        }
}