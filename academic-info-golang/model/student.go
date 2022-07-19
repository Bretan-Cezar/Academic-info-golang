package model

type Student struct {
	StudentId int    `gorm:"primaryKey;column:student_id;type:int"`
	group     string `gorm:"column:group;type:string"`
}
