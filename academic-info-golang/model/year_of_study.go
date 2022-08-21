package model

type YearOfStudy struct {
	YearOfStudyId  int    `gorm:"primaryKey;column:year_of_study_id;type:int"`
	YearNumber     int    `gorm:"column:year_number;type:int"`
	FacultyId      int    `gorm:"column:faculty_id;type:int"`
	StudyLevel     string `gorm:"column:study_level;type:string"`
	Specialization string `gorm:"column:specialization;type:string"`
	StudentId      int    `gorm:"column:student_id;type:int"`
	FundingLevel   string `gorm:"column:funding_level;type:string"`
	Faculty        *Faculty
	Curriculum     *Curriculum
	Student        *Student
}

func (YearOfStudy) TableName() string {

	return "year_of_study"
}
