package com.formula1.academicinfo.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("hello")
    fun sayHello() : ResponseEntity<String> {
        return ResponseEntity.ok("Hello")
    }
}