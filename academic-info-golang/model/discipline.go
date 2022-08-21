package model

type Discipline struct {
	DisciplineId       int    `gorm:"primaryKey;column:discipline_id;type:int"`
	DisciplineName     string `gorm:"column:discipline_name;type:string"`
	TeacherId          int    `gorm:"column:teacher_id;type:int"`
	CreditCount        int    `gorm:"column:credit_count;type:int"`
	IsOptional         bool   `gorm:"column:is_optional;type:bool"`
	Teacher            *Teacher
	Grades             []*Grade
	OptionalDiscipline *OptionalDiscipline `gorm:"foreignKey:OptionalDisciplineId"`
}

func (Discipline) TableName() string {
	return "discipline"
}
