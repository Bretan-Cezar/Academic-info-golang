package database

import (
	"example/academic-info-golang/model"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"log"
)

var Instance *gorm.DB
var dbError error

var UserRepository []model.User
var FacultyRepository []model.Faculty

func Connect(connectionString string) {

	Instance, dbError = gorm.Open(postgres.New(postgres.Config{
		DSN:                  connectionString,
		PreferSimpleProtocol: true, // disables implicit prepared statement usage
	}), &gorm.Config{})

	if dbError != nil {

		log.Fatal(dbError)
		//panic("Cannot connect to DB")
	}

	log.Println("Connected to Database!")
}

func Migrate() {

	var err error

	err = Instance.AutoMigrate(&model.User{})

	if err != nil {
		return
	}

	err = Instance.AutoMigrate(&model.Admin{})
	if err != nil {
		return
	}

	err = Instance.AutoMigrate(&model.Student{})
	if err != nil {
		return
	}

	err = Instance.AutoMigrate(&model.Teacher{})
	if err != nil {
		return
	}

	err = Instance.AutoMigrate(&model.Faculty{})
	if err != nil {
		return
	}

	log.Println("Database Migration Completed!")
}

func Preload() {

	var err error

	err = Instance.
		Preload("Admin").
		Preload("Student").
		Preload("Teacher").
		Preload("Teacher.Faculty").
		Find(&UserRepository).Error

	if err != nil {
		return
	}

	err = Instance.Preload("Teacher").Find(&FacultyRepository).Error
	//err = Instance.Find(&FacultyRepository).Error
	if err != nil {
		return
	}

	log.Println("Database Preload Completed!")
}
