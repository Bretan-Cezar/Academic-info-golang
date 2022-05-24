package com.formula1.academicinfo.model.exporters

interface AllStudentsResultExporterFactoryInterface {
    fun createFromType(type: String) : AllStudentsResultExporter
}