package model

type Faculty struct {
	FacultyId      int    `gorm:"primaryKey;column:faculty_id;type:int;"`
	FacultyName    string `gorm:"column:faculty_name;type:string"`
	Email          string `gorm:"column:email;type:string"`
	PhoneNumber    string `gorm:"column:phone_number;type:string"`
	Address        string `gorm:"column:address;type:string"`
	ChiefTeacherId int
	Teacher        *Teacher `gorm:"foreignKey:ChiefTeacherId;references:TeacherId;"`
}

func (Faculty) TableName() string {
	return "faculty"
}
