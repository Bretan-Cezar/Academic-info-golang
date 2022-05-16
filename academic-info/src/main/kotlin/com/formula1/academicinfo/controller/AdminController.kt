package com.formula1.academicinfo.controller

import com.formula1.academicinfo.model.exporters.StudentResultExporterFactoryInterface
import com.formula1.academicinfo.service.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("admin")
class AdminController(
    val adminService: AdminService,
    val studentResultExporterFactoryInterface: StudentResultExporterFactoryInterface
) {

    @GetMapping("getStudentsOrderedByGrades/{adminId}/{type}")
    fun getStudentsByGrades(@PathVariable("adminId") adminId: Int,
                            @PathVariable("type") type: String,
                            httpServletResponse: HttpServletResponse) {
        val exporter = studentResultExporterFactoryInterface.createFromType(type)

        exporter.export(adminService.getStudentsByResults(adminId), httpServletResponse)
    }

    @GetMapping("getAllStudentsOrderedByGrades/{adminId}/{type}")
    fun getAllStudentsByGrades(@PathVariable("adminId") adminId: Int,
                               @PathVariable("type") type: String,
                               httpServletResponse: HttpServletResponse) : ResponseEntity<Any> {
        return ResponseEntity.ok(adminService.getAllStudentsByResults(adminId));
    }

    @GetMapping("getStudentsForEachYearByResult/{adminId}/{type}")
    fun getStudentsForYearByGrades(@PathVariable("adminId") adminId: Int,
                                   @PathVariable("type") type: String,
                                   httpServletResponse: HttpServletResponse) : ResponseEntity<Any> {
        return ResponseEntity.ok(adminService.getStudentsForEachYearByResult(adminId));
    }
}