package model

type Admin struct {
	AdminId   int `gorm:"primaryKey;column:admin_id;type:int;"`
	FacultyId int `gorm:"column:faculty_id;type:int;"`
	Faculty   *Faculty
}

func (Admin) TableName() string {
	return "admin"
}
