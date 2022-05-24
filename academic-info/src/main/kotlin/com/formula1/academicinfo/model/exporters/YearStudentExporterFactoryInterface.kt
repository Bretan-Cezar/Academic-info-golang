package com.formula1.academicinfo.model.exporters

interface YearStudentExporterFactoryInterface {
    fun createFromType(type: String) : YearStudentExporter
}