package controller

import (
	"example/academic-info-golang/auth"
	"example/academic-info-golang/database"
	"example/academic-info-golang/model"
	"example/academic-info-golang/service"
	"example/academic-info-golang/utils"
	"github.com/gin-gonic/gin"
	"net/http"
)

func GetUser(context *gin.Context) {

	claims, _ := auth.GetTokenClaims(context.GetHeader("Authorization"))

	var userRecord model.User

	for _, user := range database.UserRepo.Data {

		if user.UserId == claims.UserId {
			userRecord = user
			break
		}
	}

	// Using lazy loading:
	// database.Instance.Where("user_id = ?", claims.UserId).First(&userRecord)

	context.JSON(http.StatusOK,
		gin.H{
			"fullName":    userRecord.FullName,
			"cnp":         userRecord.CNP,
			"dateOfBirth": userRecord.DateOfBirth.Format("2006-01-02"),
			"email":       userRecord.Email,
			"phoneNumber": userRecord.PhoneNumber,
		})
}

func ModifyUser(context *gin.Context) {

	claims, _ := auth.GetTokenClaims(context.GetHeader("Authorization"))

	var updateData service.UpdateDto

	if err := context.ShouldBindJSON(&updateData); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	if !utils.IsValidEmail(updateData.Email) {
		context.JSON(http.StatusBadRequest, gin.H{"error": "Invalid email"})
		context.Abort()
		return
	}

	if !utils.IsValidPhoneNumber(updateData.Phone) {
		context.JSON(http.StatusBadRequest, gin.H{"error": "Invalid phone number"})
		context.Abort()
		return
	}

	service.UpdateUser(claims.UserId, updateData)

	context.JSON(http.StatusOK, "")
}

func GetYears(context *gin.Context) {

	claims, _ := auth.GetTokenClaims(context.GetHeader("Authorization"))

	context.JSON(http.StatusOK, service.GetYearsByStudentId(claims.UserId))
}
