package utils

import "regexp"

func IsValidEmail(email string) bool {

	expr := "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"
	re, _ := regexp.Compile(expr)

	return re.MatchString(email)
}

func IsValidPhoneNumber(phoneNumber string) bool {

	expr := "^[+]*[(]?[0-9]{1,4}[)]?[-s./0-9]*$"
	re, _ := regexp.Compile(expr)

	return re.MatchString(phoneNumber)
}
