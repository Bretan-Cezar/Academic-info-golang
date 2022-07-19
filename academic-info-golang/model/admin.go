package model

type Admin struct {
	AdminId   int `gorm:"primaryKey;column:admin_id;type:int;"`
	FacultyId int `gorm:"column:faculty_id;type:int;"`
}
