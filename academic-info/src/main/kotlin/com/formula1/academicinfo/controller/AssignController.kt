package com.formula1.academicinfo.controller

import com.formula1.academicinfo.service.OptionalsSelectionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AssignController(
    val optionalsSelectionService: OptionalsSelectionService
){
    @RequestMapping("assign")
    fun assignAllOptionals() : ResponseEntity<Any> {
        return ResponseEntity.ok(optionalsSelectionService.assignOptionals())
    }
}