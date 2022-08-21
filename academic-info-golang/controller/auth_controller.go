package controller

import (
	"example/academic-info-golang/auth"
	"example/academic-info-golang/service"
	"github.com/gin-gonic/gin"
	"net/http"
)

type TokenRequest struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

func LoginUser(context *gin.Context) {

	var loginData TokenRequest

	if err := context.ShouldBindJSON(&loginData); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	user, err := service.CheckUserCredentials(loginData.Username, loginData.Password)

	if err != nil {

		context.JSON(http.StatusUnauthorized, gin.H{"message": "Unauthorized user"})
		context.Abort()
		return
	}

	userType := service.DetermineUserType(user)

	token, _ := auth.GenerateJWT(user.UserId, user.Username, userType)

	context.Header("Authorization", token)

	context.JSON(http.StatusOK,
		gin.H{
			"message":  "Successful",
			"username": user.Username,
			"userType": userType,
		})
}

/*
Example of a request body:

{
    "fullName": "Gopher Smith",
    "cnp": "6131221236886",
    "password": "abcd",
    "dateOfBirth": "2009-11-10",
    "email": "gopher.smith@gmail.com",
    "phoneNumber": "0734584843",
    "username": "gopher",
    "type": "student"
}

*/

func RegisterUser(context *gin.Context) {

	var dto service.RegisterDto

	if err := context.ShouldBindJSON(&dto); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	_, err := service.CreateNewUserWithType(dto)

	if err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	context.JSON(http.StatusCreated, "")
}
