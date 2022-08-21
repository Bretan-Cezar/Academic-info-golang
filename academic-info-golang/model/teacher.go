package model

type Teacher struct {
	TeacherId   int    `gorm:"primaryKey;column:teacher_id;type:int"`
	FacultyId   int    `gorm:"column:faculty_id;type:int"`
	Degree      string `gorm:"column:degree;type:string"`
	Faculty     *Faculty
	Disciplines []*Discipline
}

func (Teacher) TableName() string {
	return "teacher"
}
