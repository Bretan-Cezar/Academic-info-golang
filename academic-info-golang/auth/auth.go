package auth

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func Auth() gin.HandlerFunc {

	return func(context *gin.Context) {

		tokenString := context.GetHeader("Authorization")

		if tokenString == "" {
			context.JSON(http.StatusUnauthorized, gin.H{"error": "request does not contain an access token"})
			context.Abort()
			return
		}

		err := ValidateToken(tokenString)

		if err != nil {
			context.JSON(http.StatusForbidden, gin.H{"error": err.Error()})
			context.Abort()
			return
		}

		context.Next()
	}
}
