package model

type Grade struct {
	StudentId    int `gorm:"primaryKey;column:student_id;type:int"`
	DisciplineId int `gorm:"primaryKey;column:discipline_id;type:int"`
	Value        int `gorm:"column:value;type:int"`
	Student      *Student
	Discipline   *Discipline
}

func (Grade) TableName() string {
	return "grade"
}
