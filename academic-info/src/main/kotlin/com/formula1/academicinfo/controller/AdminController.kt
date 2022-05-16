package com.formula1.academicinfo.controller

import com.formula1.academicinfo.model.exporters.StudentResultExporterFactoryInterface
import com.formula1.academicinfo.service.AdminService
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
}