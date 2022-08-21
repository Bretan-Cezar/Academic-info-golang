package model

type OptionalsSelection struct {
	StudentId            int `gorm:"primaryKey;column:student_id;type:int"`
	OptionalDisciplineId int `gorm:"primaryKey;column:optional_discipline_id;type:int"`
	Priority             int `gorm:"column:priority;type:int"`
	Student              *Student
	OptionalDiscipline   *OptionalDiscipline
}
