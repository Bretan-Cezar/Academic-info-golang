package main

import (
	"example/academic-info-golang/controller"
	"example/academic-info-golang/database"
	"github.com/gin-gonic/gin"
)

func initAuthController(router *gin.Engine) {

	auth := router.Group("/auth")
	{
		auth.POST("/login", controller.LoginUser)
	}

}

func main() {

	database.Connect(
		"host=ec2-35-168-194-15.compute-1.amazonaws.com " +
			"user=uggzmunmxiudlk " +
			"password=442041d90e73cc1457c7817557b193560d379205ea68cb6fa1f5964978cd5c42 " +
			"dbname=d3nktfl185a6ka " +
			"port=5432 " +
			"sslmode=require " +
			"TimeZone=GMT")

	database.Migrate()

	router := gin.Default()

	err := router.SetTrustedProxies([]string{"127.0.0.1"})
	if err != nil {
		return
	}

	initAuthController(router)

	err = router.Run(":8090")
	if err != nil {
		return
	}

}
