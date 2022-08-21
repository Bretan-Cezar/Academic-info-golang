package auth

import (
	"errors"
	"github.com/dgrijalva/jwt-go"
	"github.com/magiconair/properties"
	"time"
)

var jwtKey = []byte(
	properties.MustLoadFile("application.properties", properties.UTF8).
		MustGetString("secret"))

type JWTClaim struct {
	UserId   int    `json:"userId"`
	Username string `json:"username"`
	UserType string `json:"userType"`
	jwt.StandardClaims
}

func GenerateJWT(id int, username string, userType string) (tokenString string, err error) {

	expirationTime := time.Now().Add(24 * time.Hour)

	claims := &JWTClaim{
		UserId:   id,
		Username: username,
		UserType: userType,
		StandardClaims: jwt.StandardClaims{
			IssuedAt:  time.Now().Unix(),
			ExpiresAt: expirationTime.Unix(),
		},
	}

	token := jwt.NewWithClaims(jwt.SigningMethodHS512, claims)
	tokenString, err = token.SignedString(jwtKey)

	return
}

func ValidateToken(signedToken string) (err error) {

	if signedToken[:7] != "Bearer " {

		err = errors.New("invalid token")
		return
	}

	signedToken = signedToken[7:]

	token, err := jwt.ParseWithClaims(
		signedToken,
		&JWTClaim{},
		func(token *jwt.Token) (interface{}, error) {
			return jwtKey, nil
		},
	)
	if err != nil {
		return
	}

	claims, ok := token.Claims.(*JWTClaim)

	if !ok {
		err = errors.New("couldn't parse claims")
		return
	}
	if claims.ExpiresAt < time.Now().Local().Unix() {
		err = errors.New("token expired")
		return
	}

	return
}

func GetTokenClaims(signedToken string) (claims *JWTClaim, err error) {

	signedToken = signedToken[7:]

	token, err := jwt.ParseWithClaims(
		signedToken,
		&JWTClaim{},
		func(token *jwt.Token) (interface{}, error) {
			return jwtKey, nil
		},
	)
	if err != nil {
		return
	}

	claims, _ = token.Claims.(*JWTClaim)

	return
}
