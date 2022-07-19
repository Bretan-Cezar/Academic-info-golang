package auth

import (
	"errors"
	"github.com/dgrijalva/jwt-go"
	"time"
)

var jwtKey = []byte("academic_info")

type JWTClaim struct {
	Username string `json:"username"`
	jwt.StandardClaims
}

type TokenRequest struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

func GenerateJWT(username string) (tokenString string, err error) {

	expirationTime := time.Now().Add(1 * time.Hour)

	claims := &JWTClaim{
		Username: username,
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
