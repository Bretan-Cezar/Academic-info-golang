package controller

import (
	"encoding/json"
	"example/academic-info-golang/auth"
	"example/academic-info-golang/database"
	"example/academic-info-golang/model"
	"github.com/gin-gonic/gin"
	"io/ioutil"
	"net/http"
)

func LoginUser(context *gin.Context) {

	rawLoginData, err := ioutil.ReadAll(context.Request.Body)

	if err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	var loginData auth.TokenRequest

	err = json.Unmarshal(rawLoginData, &loginData)

	if err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	var userRecord model.User

	for _, user := range database.UserRepository {

		if user.Username == loginData.Username {
			userRecord = user
			break
		}
	}

	if userRecord.Username == "" {
		context.JSON(http.StatusUnauthorized, gin.H{"message": "Unauthorized user"})
		context.Abort()
		return
	}

	credentialError := userRecord.CheckPassword(loginData.Password)

	if credentialError != nil {
		context.JSON(http.StatusUnauthorized, gin.H{"message": "Unauthorized user"})
		context.Abort()
		return
	}

	token, err := auth.GenerateJWT(loginData.Username)

	context.Header("Authorization", "Bearer "+token)

	responseBody := gin.H{"message": "Successful", "username": loginData.Username}

	if userRecord.Admin.AdminId == userRecord.UserId {

		responseBody["userType"] = "admin"

	} else if userRecord.Student.StudentId == userRecord.UserId {

		responseBody["userType"] = "student"

	} else if userRecord.Teacher.TeacherId == userRecord.UserId {

		responseBody["userType"] = "teacher"

		for _, faculty := range database.FacultyRepository {

			if faculty.Teacher != nil {

				if faculty.Teacher.TeacherId == userRecord.Teacher.TeacherId {

					responseBody["userType"] = "chiefOfDepartment"
					break
				}
			}
		}
	}

	context.JSON(http.StatusOK, responseBody)
}

func RegisterUser(context *gin.Context) {

	var user model.User

	if err := context.ShouldBindJSON(&user); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	if err := user.HashPassword(user.Password); err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	record := database.Instance.Create(&user)

	if record.Error != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"error": record.Error.Error()})
		context.Abort()
		return
	}
	context.JSON(http.StatusCreated, gin.H{"userId": user.UserId, "username": user.Username})
}
