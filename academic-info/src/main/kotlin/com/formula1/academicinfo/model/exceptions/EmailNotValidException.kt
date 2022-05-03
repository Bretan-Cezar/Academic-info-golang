package com.formula1.academicinfo.model.exceptions

class EmailNotValidException : Exception {
    constructor() : super()
    constructor(message : String) : super(message)
}