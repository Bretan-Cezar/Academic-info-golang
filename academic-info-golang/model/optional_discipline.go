package model

type OptionalDiscipline struct {
	OptionalDisciplineId int         `gorm:"primaryKey;column:optional_discipline_id;type:int"`
	MaxAttendants        int         `gorm:"column:max_attendants;type:int"`
	Approved             bool        `gorm:"column:approved;type:bool"`
	CurrentAttendants    int         `gorm:"column:current_attendants;type:int"`
	Discipline           *Discipline `gorm:"foreignKey:OptionalDisciplineId;associationForeignKey:DisciplineId"`
}

func (OptionalDiscipline) TableName() string {
	return "optional_discipline"
}
