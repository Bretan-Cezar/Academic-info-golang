package com.formula1.academicinfo.model.exporters

import org.springframework.stereotype.Component

@Component
object StudentResultExporterFactory : StudentResultExporterFactoryInterface {
    override fun createFromType(type: String): StudentResultExporter =
        when(type) {
            "pdf" -> StudentResultPdfExporter.getInstance()
            else -> throw Exception("Exporter type not supported!")
        }
}