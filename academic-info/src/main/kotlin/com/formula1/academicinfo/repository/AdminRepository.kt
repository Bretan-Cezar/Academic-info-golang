package com.formula1.academicinfo.repository

import com.formula1.academicinfo.dtos.GetStudentByGradeDto
import com.formula1.academicinfo.dtos.YearStudentsResultDto
import com.formula1.academicinfo.model.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AdminRepository : JpaRepository<Admin, Int> {
    @Query(
        "select new com.formula1.academicinfo.dtos.GetStudentByGradeDto(g.gradeStudent.studentId, avg(g.value)) " +
                "from Admin a join Faculty f on a.facultyAdmin.facultyId = f.facultyId " +
                "join Teacher t on t.facultyTeacher.facultyId = f.facultyId " +
                "join Discipline d on d.teacherDiscipline.teacherId = t.teacherId " +
                "join Grade g on g.gradeDiscipline.disciplineId = d.disciplineId " +
                "join Student s on s.studentId = g.gradeStudent.studentId " +
                "where a.adminId = :adminId " +
                "group by g.gradeStudent.studentId " +
                "order by avg(g.value)"
    )
    fun getStudentsByResults(@Param("adminId") adminId: Int) : Set<GetStudentByGradeDto>

    @Query(
        "select a " +
        "from Admin a join User u on a.adminId = u.userId " +
        "where u.username = :username"
    )
    fun findAdminByUsername(@Param("username") username: String) : Admin?

    @Query(
        "select new com.formula1.academicinfo.dtos.GetStudentByGradeDto(g.gradeStudent.studentId, avg(g.value)) " +
                "from Admin a join Faculty f on a.facultyAdmin.facultyId = f.facultyId " +
                "join Teacher t on t.facultyTeacher.facultyId = f.facultyId " +
                "join Discipline d on d.teacherDiscipline.teacherId = t.teacherId " +
                "join Grade g on g.gradeDiscipline.disciplineId = d.disciplineId " +
                "join Student s on s.studentId = g.gradeStudent.studentId " +
                "where a.adminId = :adminId " +
                "group by g.gradeStudent.studentId " +
                "order by avg(g.value) desc"
    )
    fun getStudentsByResultsDecreasing(@Param("adminId") adminId: Int) : Set<GetStudentByGradeDto>
    @Query(
        "select new com.formula1.academicinfo.dtos.YearStudentsResultDto(g.gradeStudent.studentId, ys.yearNo, avg(g.value)) " +
                "from Admin a join Faculty f on a.facultyAdmin.facultyId = f.facultyId " +
                "join Teacher t on t.facultyTeacher.facultyId = f.facultyId " +
                "join Discipline d on d.teacherDiscipline.teacherId = t.teacherId " +
                "join Grade g on g.gradeDiscipline.disciplineId = d.disciplineId " +
                "join Student s on s.studentId = g.gradeStudent.studentId " +
                "join YearOfStudy ys on ys.studentYos.studentId = s.studentId " +
                "where a.adminId = :adminId " +
                "group by g.gradeStudent.studentId, ys.yearNo " +
                "order by ys.yearNo, avg(g.value) desc"
    )
    fun getStudentsForEachYearByResults(@Param("adminId") adminId: Int) : Set<YearStudentsResultDto>
}