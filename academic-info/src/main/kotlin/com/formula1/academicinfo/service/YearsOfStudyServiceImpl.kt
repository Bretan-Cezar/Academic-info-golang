package com.formula1.academicinfo.service

import com.formula1.academicinfo.dtos.GetYearsDto
import com.formula1.academicinfo.model.YearOfStudy
import com.formula1.academicinfo.repository.YearOfStudyRepository
import org.springframework.stereotype.Service

@Service
class YearsOfStudyServiceImpl(private val yearsRepository: YearOfStudyRepository) : YearsOfStudyService {
    override fun getYearsOfStudyByUsername(username: String): MutableSet<GetYearsDto> {
        val yearsDto : MutableSet<GetYearsDto> = mutableSetOf()

        val yearsOfStudy = yearsRepository.getYearsOfStudyByUsername(username)

        for (year in yearsOfStudy){
            val yearDto = GetYearsDto()
            yearDto.year_number = year.yearNo
            yearDto.specialization = year.specialization
            yearDto.curriculumId = year.curriculum!!.curriculumId
            yearsDto.add(yearDto)
        }

        return yearsDto
    }
}