package com.formula1.academicinfo.model.exporters

import com.formula1.academicinfo.dtos.StudentResultDto
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import javax.servlet.http.HttpServletResponse

class StudentResultExcelExporter private constructor() : StudentResultExporter {
    companion object {
        fun getInstance(): StudentResultExcelExporter {
            return StudentResultExcelExporter()
        }
    }
    override fun export(objects: List<StudentResultDto>, httpServletResponse: HttpServletResponse) {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Students")

        createHeader(sheet, objects)
        insertStudents(sheet, objects)

        httpServletResponse.contentType = "application/vnd.ms-excel"
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=grades.xls")

        workbook.write(httpServletResponse.outputStream)
    }

    private fun createHeader(sheet: XSSFSheet, students: List<StudentResultDto>) {
        sheet.createRow(0)

        sheet.getRow(0).createCell(0).setCellValue("Student Name")
        sheet.getRow(0).createCell(1).setCellValue("Student Group")
        sheet.getRow(0).createCell(2).setCellValue("Student Result")

        for (i in students.indices) {
            sheet.createRow(i + 1)
        }
    }

    private fun insertStudents(sheet: XSSFSheet, students: List<StudentResultDto>) {
        for (i in students.indices) {
            sheet.getRow(i + 1).createCell(0).setCellValue(students[i].studentName)
            sheet.getRow(i + 1).createCell(1).setCellValue(students[i].studentGroup)
            sheet.getRow(i + 1).createCell(2).setCellValue(students[i].grade)
        }
    }
}