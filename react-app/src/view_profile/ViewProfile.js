import React from "react";
import "./ViewProfile.css";
import { UserContext } from "../App";

function ViewProfile() {
	let [profile, setProfile] = React.useState("");

	const userData = React.useContext(UserContext);

	//let profile = {};

	React.useEffect(() => {
		let url = "http://localhost:1337/http://localhost:8090/user/getUser/";
		url += userData.username;

		const requestOptions = {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "origin",
				Authorization: "Bearer " + userData.auth_token,
			},
		};

		const profileRequest = new Request(url, requestOptions);

		fetch(profileRequest)
			.then((response) => {
				return response.json();
			})
			.then((data) => {
				// console.log({ fullName: data.fullName, cnp: data.cnp, dateOfBirth: data.dateOfBirth, email: data.email, phoneNumber: data.phoneNumber });
				setProfile({ fullName: data.fullName, cnp: data.cnp, dateOfBirth: data.dateOfBirth, email: data.email, phoneNumber: data.phoneNumber });
			});

		// Example data
		// setProfile({full_name: "Bretan Cezar-Alexandru", cnp: "5020110125775", dob: "2002-01-10", email: "cezar.bretan@gmail.com", phone_no: "+40734584843"});
	}, [userData]);

	return (
		<>
			<div className="Profile-container">
				<div className="Profile-field">
					<p>Full Name: </p>
					<span>{profile.fullName}</span>
				</div>

				<div className="Profile-field">
					<p>CNP: </p>
					<span>{profile.cnp}</span>
				</div>

				<div className="Profile-field">
					<p>Date of Birth: </p>
					<span>{profile.dateOfBirth}</span>
				</div>

				<div className="Profile-field">
					<p>E-mail: </p>
					<span>{profile.email}</span>
				</div>

				<div className="Profile-field">
					<p>Phone Number: </p>
					<span>{profile.phoneNumber}</span>
				</div>
			</div>
		</>
	);
}

export default ViewProfile;
