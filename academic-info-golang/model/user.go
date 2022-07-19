package model

import (
	"golang.org/x/crypto/bcrypt"
	"time"
)

type User struct {
	UserId      int       `gorm:"primaryKey;column:user_id;autoIncrement;type:int"`
	fullName    string    `gorm:"column:full_name;type:string"`
	Password    string    `gorm:"column:password;type:string"`
	cnp         string    `gorm:"column:cnp;type:string"`
	dateOfBirth time.Time `gorm:"column:date_of_birth;type:time"`
	Email       string    `gorm:"column:email;type:string"`
	phoneNumber string    `gorm:"column:phone_number;type:string"`
	Username    string    `gorm:"column:username;type:string"`
	Admin       Admin     `gorm:"foreignKey:AdminId;references:UserId"`
	Teacher     Teacher   `gorm:"foreignKey:TeacherId;references:UserId"`
	Student     Student   `gorm:"foreignKey:StudentId;references:UserId"`
}

func (user *User) HashPassword(password string) error {

	bytes, err := bcrypt.GenerateFromPassword([]byte(password), 14)
	if err != nil {
		return err
	}

	user.Password = string(bytes)
	return nil
}

func (user *User) CheckPassword(providedPassword string) error {

	err := bcrypt.CompareHashAndPassword([]byte(user.Password), []byte(providedPassword))
	if err != nil {
		return err
	}

	return nil
}
