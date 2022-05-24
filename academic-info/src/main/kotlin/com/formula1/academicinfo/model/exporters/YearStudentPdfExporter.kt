package com.formula1.academicinfo.model.exporters

import com.formula1.academicinfo.dtos.StudentResultDto
import com.formula1.academicinfo.dtos.YearStudentsDto
import com.lowagie.text.Document
import com.lowagie.text.PageSize
import com.lowagie.text.Paragraph
import com.lowagie.text.Phrase
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfWriter
import javax.servlet.http.HttpServletResponse

class YearStudentPdfExporter private constructor() : YearStudentExporter{
    companion object {
        fun getInstance(): YearStudentExporter {
            return YearStudentPdfExporter()
        }
    }
    override fun export(objects: List<YearStudentsDto>, httpServletResponse: HttpServletResponse) {
        httpServletResponse.contentType = "application/pdf"

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=grades.pdf")

        val document = Document(PageSize.A4)
        PdfWriter.getInstance(document, httpServletResponse.outputStream)

        document.open()
        val paragraph = Paragraph("List of Students sorted by years and results")
        paragraph.alignment = Paragraph.ALIGN_CENTER
        paragraph.spacingAfter = 10f
        document.add(paragraph)

        val table = PdfPTable(3)
        table.widthPercentage = 100f

        writeTableHeader(table)
        writeTableData(table, objects)

        document.add(table)

        document.close()
    }

    private fun writeTableHeader(table: PdfPTable) {
        val cell = PdfPCell()
        cell.setPadding(5f)
        cell.phrase = Phrase("Student Name")
        table.addCell(cell)
        cell.phrase = Phrase("Student Year")
        table.addCell(cell)
        cell.phrase = Phrase("Student Grade")
        table.addCell(cell)
    }

    private fun writeTableData(table: PdfPTable, students: List<YearStudentsDto>) {
        for (student in students) {
            table.addCell(student.student_name)
            table.addCell(student.year_number.toString())
            table.addCell(student.grade.toString())
        }
    }
}