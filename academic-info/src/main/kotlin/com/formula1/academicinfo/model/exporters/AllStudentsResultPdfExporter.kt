package com.formula1.academicinfo.model.exporters

import com.formula1.academicinfo.dtos.AllStudentsResultDto
import javax.servlet.http.HttpServletResponse
import com.lowagie.text.*
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfWriter

class AllStudentsResultPdfExporter private constructor() : AllStudentsResultExporter{
    companion object {
        fun getInstance(): AllStudentsResultExporter {
            return AllStudentsResultPdfExporter();
        }
    }
    override fun export(objects: List<AllStudentsResultDto>, httpServletResponse: HttpServletResponse) {
        httpServletResponse.contentType = "application/pdf"

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=grades.pdf")

        val document = Document(PageSize.A4)
        PdfWriter.getInstance(document, httpServletResponse.outputStream)

        document.open()
        val paragraph = Paragraph("List of Students sorted descending by results")
        paragraph.alignment = Paragraph.ALIGN_CENTER
        paragraph.spacingAfter = 10f
        document.add(paragraph)

        val table = PdfPTable(2)
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
        cell.phrase = Phrase("Student Grade")
        table.addCell(cell)
    }

    private fun writeTableData(table: PdfPTable, students: List<AllStudentsResultDto>) {
        for (student in students) {
            table.addCell(student.studentName)
            table.addCell(student.grade.toString())
        }
    }
}