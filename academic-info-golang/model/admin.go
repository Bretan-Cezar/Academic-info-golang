package model

type Admin struct {
	AdminId int `gorm:"primaryKey;column:admin_id;type:int;"`
}

func (Admin) TableName() string {
	return "admin"
}
