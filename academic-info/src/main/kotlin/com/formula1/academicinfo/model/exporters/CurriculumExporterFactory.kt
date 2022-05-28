package com.formula1.academicinfo.model.exporters

import org.springframework.stereotype.Component

@Component
object CurriculumExporterFactory : CurriculumExporterFactoryInterface {
    override fun createFromType(type: String): CurriculumExporter =
        when(type) {
            "pdf" -> CurriculumPdfExporter.getInstance()
            "excel" -> CurriculumExcelExporter.getInstance()
            else -> throw Exception("Exporter type not supported!")
        }
}