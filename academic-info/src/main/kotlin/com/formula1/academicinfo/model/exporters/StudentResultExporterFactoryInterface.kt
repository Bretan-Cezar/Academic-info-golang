package com.formula1.academicinfo.model.exporters

interface StudentResultExporterFactoryInterface {
    fun createFromType(type: String) : StudentResultExporter
}