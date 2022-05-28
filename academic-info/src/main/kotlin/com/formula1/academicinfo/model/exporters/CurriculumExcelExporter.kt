package com.formula1.academicinfo.model.exporters

import com.formula1.academicinfo.dtos.DisciplineDto
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import javax.servlet.http.HttpServletResponse

class CurriculumExcelExporter private constructor() : CurriculumExporter {
    companion object {
        fun getInstance() : CurriculumExcelExporter {
            return CurriculumExcelExporter()
        }
    }
    override fun export(objects: List<DisciplineDto>, httpServletResponse: HttpServletResponse) {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Students")

        createHeader(sheet, objects)
        insertDisciplines(sheet, objects)

        httpServletResponse.contentType = "application/vnd.ms-excel"
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=grades.xls")

        workbook.write(httpServletResponse.outputStream)
    }

    private fun createHeader(sheet: XSSFSheet, disciplines: List<DisciplineDto>) {
        sheet.createRow(0)

        sheet.getRow(0).createCell(0).setCellValue("Discipline Type")
        sheet.getRow(0).createCell(1).setCellValue("Discipline Name")
        sheet.getRow(0).createCell(2).setCellValue("Teacher Name")
        sheet.getRow(0).createCell(3).setCellValue("Credit Count")

        for (i in disciplines.indices) {
            sheet.createRow(i + 1)
        }
    }

    private fun insertDisciplines(sheet: XSSFSheet, disciplines: List<DisciplineDto>) {
        for (i in disciplines.indices) {
            sheet.getRow(i + 1).createCell(0).setCellValue(disciplines[i].discipline_type)
            sheet.getRow(i + 1).createCell(1).setCellValue(disciplines[i].discipline_name)
            sheet.getRow(i + 1).createCell(2).setCellValue(disciplines[i].teacher_name)
            sheet.getRow(i + 1).createCell(3).setCellValue(disciplines[i].credit_count.toString())
        }
    }
}