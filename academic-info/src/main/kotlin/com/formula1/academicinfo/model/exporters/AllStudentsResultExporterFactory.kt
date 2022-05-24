package com.formula1.academicinfo.model.exporters

import org.springframework.stereotype.Component

@Component
object AllStudentsResultExporterFactory : AllStudentsResultExporterFactoryInterface {
    override fun createFromType(type: String): AllStudentsResultExporter =
        when(type) {
            "pdf" -> AllStudentsResultPdfExporter.getInstance()
            "excel" -> AllStudentsResultExcelExporter.getInstance()
            else -> throw Exception("Exporter type not supported!")
        }
}