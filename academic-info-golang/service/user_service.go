package service

import (
	"errors"
	"example/academic-info-golang/database"
	"example/academic-info-golang/model"
	"time"
)

type YearOfStudyDto struct {
	CurriculumId   int    `json:"curriculumId"`
	Specialization string `json:"specialization"`
	YearNumber     int    `json:"year_number"`
}

func CheckUserCredentials(username string, password string) (user model.User, err error) {

	user = database.UserRepo.FindByUsername(username)

	if user.Username == "" {

		err = errors.New("unauthorized user")
		return
	}

	credentialError := user.CheckPassword(password)

	if credentialError != nil {

		err = errors.New("unauthorized user")
		return
	}

	return
}

func DetermineUserType(user model.User) (userType string) {

	if user.Admin.AdminId == user.UserId {

		userType = "admin"

	} else if user.Student.StudentId == user.UserId {

		userType = "student"

	} else if user.Teacher.TeacherId == user.UserId {

		userType = "teacher"

		for _, faculty := range database.FacultyRepo.Data {

			if faculty.Teacher != nil {

				if faculty.Teacher.TeacherId == user.Teacher.TeacherId {

					userType = "chiefOfDepartment"
					break
				}
			}
		}
	}

	return
}

type RegisterDto struct {
	FullName    string `json:"fullName"`
	Cnp         string `json:"cnp"`
	Password    string `json:"password"`
	Dob         string `json:"dateOfBirth"`
	Email       string `json:"email"`
	PhoneNumber string `json:"phoneNumber"`
	Username    string `json:"username"`
	Type        string `json:"type"`
}

func CreateNewUserWithType(dto RegisterDto) (user model.User, err error) {

	if dto.Type != "student" && dto.Type != "teacher" && dto.Type != "admin" && dto.Type != "chiefOfDepartment" {

		err = errors.New("invalid user type")
		return
	}

	newTime, _ := time.Parse("2006-01-02", dto.Dob)

	user = database.UserRepo.Create(dto.FullName, dto.Cnp, dto.Password, newTime, dto.Email, dto.PhoneNumber, dto.Username, dto.Type)

	return
}

type UpdateDto struct {
	Username string `json:"username"`
	Email    string `json:"email"`
	Phone    string `json:"phone"`
}

func UpdateUser(userId int, updateDto UpdateDto) {

	database.UserRepo.Update(userId, updateDto.Email, updateDto.Phone)
}
