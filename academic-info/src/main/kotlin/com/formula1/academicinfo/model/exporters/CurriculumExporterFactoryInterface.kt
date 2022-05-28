package com.formula1.academicinfo.model.exporters

interface CurriculumExporterFactoryInterface {
    fun createFromType(type: String) : CurriculumExporter
}