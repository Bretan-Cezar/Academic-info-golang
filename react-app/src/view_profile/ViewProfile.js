import React from "react";
import "./ViewProfile.css";
import { UserContext } from "../App";

function ViewProfile() {
	let [profile, setProfile] = React.useState("");

	const userData = React.useContext(UserContext);

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
				setProfile({ fullName: data.fullName, cnp: data.cnp, dateOfBirth: data.dateOfBirth, email: data.email, phoneNumber: data.phoneNumber });
			});
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
