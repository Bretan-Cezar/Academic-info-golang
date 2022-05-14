package com.formula1.academicinfo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "admin")
class Admin {
    @Id
    @Column(name = "admin_id", nullable = false)
    var adminId: Int = 0

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    @MapsId
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    var adminUser: User = User()

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    var facultyAdmin: Faculty? = null
}