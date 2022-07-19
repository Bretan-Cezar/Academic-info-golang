package controller

import (
	"encoding/json"
	"errors"
	"example/academic-info-golang/auth"
	"example/academic-info-golang/database"
	"example/academic-info-golang/model"
	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
	"io/ioutil"
	"net/http"
)

func LoginUser(context *gin.Context) {

	var user model.User
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

	record := database.Instance.Table("users").Where("username = ?", loginData.Username).First(&user)

	if errors.Is(record.Error, gorm.ErrRecordNotFound) {
		context.JSON(http.StatusUnauthorized, gin.H{"message": "Unauthorized user"})
		context.Abort()
		return
	}

	// TODO: figure out how to use mapped structs instead of queries

	//var admins []model.Admin
	//err = database.Instance.Model(&user).Preload("Admin").Find(&admins).Error

	adminRecord := database.Instance.Table("admin").Where("admin_id = ?", user.UserId).First(&user.Admin)
	studentRecord := database.Instance.Table("student").Where("student_id = ?", user.UserId).First(&user.Student)
	teacherRecord := database.Instance.Table("teacher").Where("teacher_id = ?", user.UserId).First(&user.Teacher)

	credentialError := user.CheckPassword(loginData.Password)

	if credentialError != nil {
		context.JSON(http.StatusUnauthorized, gin.H{"message": "Unauthorized user"})
		context.Abort()
		return
	}

	token, err := auth.GenerateJWT(loginData.Username)

	context.Header("Authorization", "Bearer "+token)

	responseBody := gin.H{"message": "Successful", "username": loginData.Username}

	if adminRecord.RowsAffected != 0 {

		responseBody["userType"] = "admin"

	} else

	if studentRecord.RowsAffected != 0 {

		responseBody["userType"] = "student"
	} else

	if teacherRecord.RowsAffected != 0 {

		responseBody["userType"] = "teacher"
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
