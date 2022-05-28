package com.formula1.academicinfo.model.exporters

import com.formula1.academicinfo.dtos.DisciplineDto
import com.lowagie.text.Document
import com.lowagie.text.PageSize
import com.lowagie.text.Paragraph
import com.lowagie.text.Phrase
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfWriter
import javax.servlet.http.HttpServletResponse

class CurriculumPdfExporter private constructor(): CurriculumExporter {
    companion object {
        fun getInstance(): CurriculumPdfExporter {
            return CurriculumPdfExporter()
        }
    }

    override fun export(objects: List<DisciplineDto>, httpServletResponse: HttpServletResponse) {
        httpServletResponse.contentType = "application/pdf"

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=curriculum.pdf")

        val document = Document(PageSize.A4)
        PdfWriter.getInstance(document, httpServletResponse.outputStream)
        document.open()
        val paragraph = Paragraph("Curriculum")
        paragraph.alignment = Paragraph.ALIGN_CENTER
        paragraph.spacingAfter = 10f
        document.add(paragraph)

        val table = PdfPTable(4)
        table.widthPercentage = 100f

        writeTableHeader(table)
        writeTableData(table, objects)

        document.add(table)

        document.close()
    }

    private fun writeTableHeader(table: PdfPTable) {
        val cell = PdfPCell()
        cell.setPadding(5f)
        cell.phrase = Phrase("Discipline Type")
        table.addCell(cell)
        cell.phrase = Phrase("Discipline Name")
        table.addCell(cell)
        cell.phrase = Phrase("Teacher Name")
        table.addCell(cell)
        cell.phrase = Phrase("Credit Count")
        table.addCell(cell)
    }

    private fun writeTableData(table: PdfPTable, disciplines: List<DisciplineDto>) {
        for (discipline in disciplines) {
            table.addCell(discipline.discipline_type)
            table.addCell(discipline.discipline_name)
            table.addCell(discipline.teacher_name)
            table.addCell(discipline.credit_count.toString())
        }
    }
}