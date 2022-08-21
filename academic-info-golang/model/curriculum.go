package model

type Curriculum struct {
	CurriculumId  int           `gorm:"primaryKey;column:curriculum_id;type:int"`
	YearOfStudyId int           `gorm:"column:year_of_study_id;type:int"`
	Disciplines   []*Discipline `gorm:"many2many:curriculum_discipline_distribution;"`
}

func (Curriculum) TableName() string {
	return "curriculum"
}
