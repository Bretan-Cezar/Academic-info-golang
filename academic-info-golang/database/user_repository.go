package database

import (
	"example/academic-info-golang/model"
	"time"
)

type UserRepository struct {
	Data []model.User
}

func (userRepo UserRepository) FindByUsername(username string) model.User {

	for _, user := range userRepo.Data {

		if user.Username == username {

			return user
		}
	}

	return model.User{}
}

func (userRepo UserRepository) Create(
	fullName string, cnp string, password string, dob time.Time, email string, phoneNumber string, username string, userType string) (
	newUser model.User) {

	newUser = model.User{
		FullName:    fullName,
		CNP:         cnp,
		Password:    password,
		DateOfBirth: dob,
		Email:       email,
		PhoneNumber: phoneNumber,
		Username:    username,
	}

	Instance.Create(&newUser)

	switch userType {

	case "student":

		newStudent := model.Student{
			StudentId: newUser.UserId,
		}

		newUser.Student = newStudent

		Instance.Create(&newStudent)
		break

	case "admin":

		newAdmin := model.Admin{
			AdminId: newUser.UserId,
		}

		newUser.Admin = newAdmin

		Instance.Create(&newAdmin)
		break

	case "teacher":
	case "chiefOfDepartment":

		newTeacher := model.Teacher{
			TeacherId: newUser.UserId,
		}

		newUser.Teacher = newTeacher

		Instance.Create(&newTeacher)
		break
	}

	return
}

func (userRepo UserRepository) Update(userId int, email string, phone string) {

	var pos int

	for index, user := range userRepo.Data {

		if user.UserId == userId {

			pos = index

			userRepo.Data[pos].Email = email
			userRepo.Data[pos].PhoneNumber = phone
			break
		}
	}

	// goroutine execution time: ~400ms
	go Instance.Save(&userRepo.Data[pos])
}
