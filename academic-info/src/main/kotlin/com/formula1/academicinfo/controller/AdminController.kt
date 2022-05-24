package com.formula1.academicinfo.controller

import com.formula1.academicinfo.model.exporters.AllStudentsResultExporterFactoryInterface
import com.formula1.academicinfo.model.exporters.StudentResultExporterFactoryInterface
import com.formula1.academicinfo.model.exporters.YearStudentExporterFactoryInterface
import com.formula1.academicinfo.security.jwtutils.TokenManager
import com.formula1.academicinfo.service.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("admin")
class AdminController(
    val adminService: AdminService,
    val studentResultExporterFactoryInterface: StudentResultExporterFactoryInterface,
    val allStudentsResultExporterFactoryInterface: AllStudentsResultExporterFactoryInterface,
    val yearStudentsResultExporterFactoryInterface: YearStudentExporterFactoryInterface,
    val tokenManager: TokenManager
) {

    // Get students and groups
    @GetMapping("getStudentsOrderedByGrades/{type}")
    fun getStudentsByGrades(@RequestHeader("Authorization") token : String,
                            @PathVariable("type") type: String,
                            httpServletResponse: HttpServletResponse) {
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        val exporter = studentResultExporterFactoryInterface.createFromType(type)

        exporter.export(adminService.getStudentsByResults(username), httpServletResponse)
    }
    // Get students without groups
    @GetMapping("getAllStudentsOrderedByGrades/{type}")
    fun getAllStudentsByGrades(@RequestHeader("Authorization") token : String,
                               @PathVariable("type") type: String,
                               httpServletResponse: HttpServletResponse){
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        val exporter = allStudentsResultExporterFactoryInterface.createFromType(type);
        exporter.export(adminService.getAllStudentsByResults(username), httpServletResponse);
    }

    @GetMapping("getStudentsForYearByGrades/{type}")
    fun getStudentsForYearByGrades(@RequestHeader("Authorization") token : String,
                                   @PathVariable("type") type: String,
                                   httpServletResponse: HttpServletResponse){
        val username = tokenManager.getUsernameFromToken(token.substring(7))
        val exporter = yearStudentsResultExporterFactoryInterface.createFromType(type);
        exporter.export(adminService.getStudentsForEachYearByResult(username), httpServletResponse);
    }
}
