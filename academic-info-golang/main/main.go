package main

import (
	"example/academic-info-golang/auth"
	"example/academic-info-golang/controller"
	"example/academic-info-golang/database"
	"github.com/gin-gonic/gin"
	"github.com/magiconair/properties"
)

func initAuthController(router *gin.Engine) {

	authentication := router.Group("/auth")
	{
		authentication.POST("/login", controller.LoginUser)
		authentication.POST("/register", controller.RegisterUser)
	}
}

func initUserController(router *gin.Engine) {

	user := router.Group("/user").Use(auth.Auth())
	{
		user.GET("/getUser", controller.GetUser)
		user.GET("/getYears", controller.GetYears)
		user.PUT("/update", controller.ModifyUser)
	}
}

func main() {

	p := properties.MustLoadFile("application.properties", properties.UTF8)

	database.Connect(
		"host=" + p.MustGetString("host") + " " +
			"user=" + p.MustGetString("user") + " " +
			"password=" + p.MustGetString("password") + " " +
			"dbname=" + p.MustGetString("db") + " " +
			"port=" + p.MustGetString("port") + " " +
			"sslmode=" + p.MustGetString("ssl_mode") + " " +
			"TimeZone=" + p.MustGetString("time_zone"))

	database.Migrate()
	database.PreloadAll()

	router := gin.Default()

	err := router.SetTrustedProxies([]string{"127.0.0.1"})
	if err != nil {
		return
	}

	initAuthController(router)
	initUserController(router)

	err = router.Run(":8090")
	if err != nil {
		return
	}

}
