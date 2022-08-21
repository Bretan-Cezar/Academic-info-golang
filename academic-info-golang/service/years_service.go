package service

import "example/academic-info-golang/database"

func GetYearsByStudentId(studentId int) (yearDtos []YearOfStudyDto) {

	entities := database.YearOfStudyRepo.GetByStudentId(studentId)

	for _, entity := range entities {

		yearDtos = append(yearDtos, YearOfStudyDto{
			CurriculumId:   entity.Curriculum.CurriculumId,
			Specialization: entity.Specialization,
			YearNumber:     entity.YearNumber,
		})
	}

	return
}
