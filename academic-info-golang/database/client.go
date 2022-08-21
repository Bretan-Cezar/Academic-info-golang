package database

import (
	"example/academic-info-golang/model"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"log"
)

var Instance *gorm.DB
var dbError error

var UserRepo UserRepository
var FacultyRepo FacultyRepository
var YearOfStudyRepo YearOfStudyRepository

//var GradeRepo GradeRepository
//var DisciplineRepo DisciplineRepository

func Connect(connectionString string) {

	Instance, dbError = gorm.Open(postgres.New(postgres.Config{
		DSN:                  connectionString,
		PreferSimpleProtocol: true, // disables implicit prepared statement usage
	}), &gorm.Config{})

	if dbError != nil {

		log.Fatal(dbError)
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

	err = Instance.AutoMigrate(&model.YearOfStudy{})
	if err != nil {
		return
	}

	err = Instance.AutoMigrate(&model.Grade{})
	if err != nil {
		return
	}

	err = Instance.AutoMigrate(&model.Discipline{})
	if err != nil {
		return
	}

	err = Instance.AutoMigrate(&model.OptionalDiscipline{})
	if err != nil {
		return
	}

	err = Instance.AutoMigrate(&model.Curriculum{})
	if err != nil {
		return
	}

	err = Instance.AutoMigrate(&model.OptionalsSelection{})
	if err != nil {
		return
	}

	log.Println("Database Migration Completed!")
}

func PreloadUsers() (err error) {

	return Instance.
		Preload("Admin").
		Preload("Student").
		Preload("Teacher").
		Find(&UserRepo.Data).Error
}

func PreloadFaculties() (err error) {

	return Instance.
		Preload("Teacher").
		Find(&FacultyRepo.Data).Error
}

func PreloadYearsOfStudy() (err error) {

	return Instance.
		Preload("Faculty").
		Preload("Student").
		Preload("Curriculum").
		Find(&YearOfStudyRepo.Data).Error
}

//func PreloadGrades() (err error) {
//
//	return Instance.
//		Preload("Student").
//		Preload("Discipline").
//		Find(&GradeRepo.Data).Error
//}
//
//func PreloadDisciplines() (err error) {
//
//	return Instance.
//		Preload("Teacher").
//		Preload("OptionalDiscipline").
//		Find(&DisciplineRepo.Data).Error
//}

/*
	Preloading only the static data for the application.
*/
func PreloadAll() {

	var err error

	err = PreloadUsers()

	if err != nil {
		log.Fatal(err)
		return
	}

	err = PreloadFaculties()

	if err != nil {
		log.Fatal(err)
		return
	}

	err = PreloadYearsOfStudy()

	if err != nil {
		log.Fatal(err)
		return
	}

	log.Println("Database Preload Completed!")
}
