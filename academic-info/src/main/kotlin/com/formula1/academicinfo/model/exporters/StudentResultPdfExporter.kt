package com.formula1.academicinfo.model.exporters

import com.formula1.academicinfo.dtos.StudentResultDto
import com.lowagie.text.*
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfWriter
import javax.servlet.http.HttpServletResponse

class StudentResultPdfExporter private constructor() : StudentResultExporter {
    companion object {
        fun getInstance(): StudentResultPdfExporter {
            return StudentResultPdfExporter()
        }
    }
    override fun export(objects: List<StudentResultDto>, httpServletResponse: HttpServletResponse) {
        val document = Document(PageSize.A4)
        PdfWriter.getInstance(document, httpServletResponse.outputStream)

        document.open()
        val paragraph = Paragraph("List of Students sorted by results")
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
        cell.phrase = Phrase("Student Group")
        table.addCell(cell)
        cell.phrase = Phrase("Student Grade")
        table.addCell(cell)
    }

    private fun writeTableData(table: PdfPTable, students: List<StudentResultDto>) {
        for (student in students) {
            table.addCell(student.studentName)
            table.addCell(student.studentGroup)
            table.addCell(student.grade.toString())
        }
    }
}