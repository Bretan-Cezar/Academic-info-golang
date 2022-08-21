package database

import (
	"example/academic-info-golang/model"
)

type YearOfStudyRepository struct {
	Data []model.YearOfStudy
}

func (yearOfStudyRepo YearOfStudyRepository) GetByStudentId(studentId int) (years []model.YearOfStudy) {

	for _, year := range yearOfStudyRepo.Data {

		if year.Student.StudentId == studentId {

			years = append(years, year)
		}
	}

	return
}
