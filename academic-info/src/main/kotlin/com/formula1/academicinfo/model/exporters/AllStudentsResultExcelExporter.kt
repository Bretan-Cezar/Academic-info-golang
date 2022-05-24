package com.formula1.academicinfo.model.exporters

import com.formula1.academicinfo.dtos.AllStudentsResultDto
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import javax.servlet.http.HttpServletResponse

class AllStudentsResultExcelExporter private constructor() : AllStudentsResultExporter{
    companion object{
        fun getInstance() : AllStudentsResultExporter{
            return AllStudentsResultExcelExporter();
        }
    }
    override fun export(objects: List<AllStudentsResultDto>, httpServletResponse: HttpServletResponse) {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Students")

        createHeader(sheet, objects)
        insertStudents(sheet, objects)

        httpServletResponse.contentType = "application/vnd.ms-excel"
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=grades.xls")

        workbook.write(httpServletResponse.outputStream)
    }

    private fun createHeader(sheet: XSSFSheet, students: List<AllStudentsResultDto>) {
        sheet.createRow(0)

        sheet.getRow(0).createCell(0).setCellValue("Student Name")
        sheet.getRow(0).createCell(1).setCellValue("Student Result")

        for (i in students.indices) {
            sheet.createRow(i + 1)
        }
    }

    private fun insertStudents(sheet: XSSFSheet, students: List<AllStudentsResultDto>) {
        for (i in students.indices) {
            sheet.getRow(i + 1).createCell(0).setCellValue(students[i].studentName)
            sheet.getRow(i + 1).createCell(1).setCellValue(students[i].grade)
        }
    }
}