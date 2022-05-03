package com.formula1.academicinfo.model

fun String.matchesEmail() : Boolean {
    return this.matches(Regex("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\$"))
}

fun String.matchesPhoneNumber() : Boolean {
    return this.matches(Regex("^[+]*[(]?[0-9]{1,4}[)]?[-s./0-9]*$"))
}