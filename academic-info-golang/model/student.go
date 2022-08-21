package model

type Student struct {
	StudentId           int    `gorm:"primaryKey;column:student_id;type:int"`
	Group               string `gorm:"column:group;type:string"`
	Grades              []*Grade
	OptionalsSelections []*OptionalsSelection
}

func (Student) TableName() string {
	return "student"
}
