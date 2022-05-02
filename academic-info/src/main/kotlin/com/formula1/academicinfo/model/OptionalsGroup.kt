package com.formula1.academicinfo.model
import javax.persistence.*

@Entity
@Table(name = "optionals_group")
class OptionalsGroup(){

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "optionals_group_id", nullable = false)
    var oGroupId: Int = 0

    @Column(name = "semester_number", nullable = false)
    var semesterNo: Int = 0

    @ManyToOne
    @JoinColumn(name = "year_of_study_id", nullable= false)
    var yosOptionalsGroup: YearOfStudy? = null

    @OneToMany(mappedBy = "optionalDisciplineGroup")
    var optionalDisciplines: MutableSet<OptionalDiscipline> = mutableSetOf()

}