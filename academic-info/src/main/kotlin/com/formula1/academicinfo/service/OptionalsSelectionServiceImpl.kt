package com.formula1.academicinfo.service

import com.formula1.academicinfo.model.Curriculum
import com.formula1.academicinfo.model.OptionalDiscipline
import com.formula1.academicinfo.repository.CurriculumRepository
import com.formula1.academicinfo.repository.DisciplineRepository
import com.formula1.academicinfo.repository.OptionalDisciplineRepository
import com.formula1.academicinfo.repository.OptionalsSelectionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StopWatch
import kotlin.streams.toList


@Service
class OptionalsSelectionServiceImpl(
    private val optionalsSelectionRepository: OptionalsSelectionRepository,
    private val disciplineRepository: DisciplineRepository,
    private val optionalDisciplineRepository: OptionalDisciplineRepository,
    private val curriculumRepository: CurriculumRepository
) : OptionalsSelectionService {
    @Transactional
    override fun assignOptionals(): Long {
        val timeMeasure = StopWatch()
        timeMeasure.start("Assign")


        val selections = optionalsSelectionRepository.getOptionalsSelectionsOrderedByPriority()

        for (selection in selections) {
            val studentId = selection.optionalsSelectionStudent?.studentId
            val curriculums = studentId?.let { curriculumRepository.getCurriculumByStudentId(it) }

            curriculums?. let {
                for (curriculum in curriculums) {
                    if (optionalAlreadyAssigned(curriculum))
                        continue

                    val disciplineId = selection.optionalsSelectionDiscipline?.oDisciplineId

                    disciplineId?.let {
                        if (disciplineIsPartOfCurriculum(disciplineId, curriculum)) {
                            addDisciplineIfPossible(
                                optionalDisciplineRepository.getOptionalDisciplineByODisciplineId(disciplineId),
                                curriculum,
                                disciplineId)
                        }
                    }
                }
            }
        }

        timeMeasure.stop()

        return timeMeasure.lastTaskTimeMillis
    }

    private fun optionalAlreadyAssigned(curriculum: Curriculum): Boolean {
        val count = curriculumRepository.getOptionalsCount(curriculum.curriculumId)
        return count > 0
    }

    private fun addDisciplineIfPossible(
        optional: OptionalDiscipline,
        curriculum: Curriculum,
        disciplineId: Int
    ) {
        if (optional.currentAttendants < optional.maxAttendants) {
            curriculum.addDiscipline(disciplineRepository.findDisciplineByDisciplineId(disciplineId))
            optionalDisciplineRepository.increaseCurrentAttendants(disciplineId)
        }
    }

    private fun disciplineIsPartOfCurriculum(
        disciplineId: Int,
        curriculum: Curriculum
    ) : Boolean {
        val curriculums = curriculumRepository.findCurriculumByDisciplineId(disciplineId)

        return curriculumFoundInArray(curriculum, curriculums)
    }

    private fun curriculumFoundInArray(
        curriculum: Curriculum,
        curriculumList : List<Curriculum>
    ) : Boolean {
        val list = curriculumList.stream().filter {
            it.curriculumId == curriculum.curriculumId
        }.toList()

        return list.isNotEmpty()
    }
}