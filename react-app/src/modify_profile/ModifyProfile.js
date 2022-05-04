import React from "react";
import "./ModifyProfile.css";
import { UserContext } from "../App";

let url = "http://localhost:1337/http://localhost:8090/user/update/";

function ModifyForm({ fields, setFields }) {
	const userData = React.useContext(UserContext);
	let [email, setEmail] = React.useState("");
	let [phone, setPhone] = React.useState("");

	const handleSubmit = (event) => {
		event.preventDefault();

		const requestOptions = {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "origin",
				Authorization: "Bearer " + userData.auth_token,
			},
			body: JSON.stringify({
				username: userData.username,
				email: email,
				phone: phone,
			}),
		};

		const profileRequest = new Request(url, requestOptions);

		fetch(profileRequest)
			.then((response) => {
				return response.json();
			})
			.then((data) => {});

		setFields({ fullName: fields.fullName, cnp: fields.cnp, dateOfBirth: fields.dateOfBirth, email: email, phoneNumber: phone });
	};

	return (
		<>
			<div className="Profile-cont">
				<form onSubmit={handleSubmit}>
					<div className="Profile-f">
						<p>Full Name: </p>
						<span>{fields.fullName}</span>
					</div>

					<div className="Profile-f">
						<p>CNP: </p>
						<span>{fields.cnp}</span>
					</div>

					<div className="Profile-f">
						<p>Date of Birth: </p>
						<span>{fields.dateOfBirth}</span>
					</div>

					<div className="Profile-f">
						<p>E-mail: </p>
						<input
							type="text"
							name="email"
							placeholder="E-mail"
							//value={fields.email || ""}
							className="Profile-field-editable"
							onChange={(event) => setEmail(event.target.value)}
						></input>
					</div>

					<div className="Profile-f">
						<p>Phone Number: </p>
						<input
							type="text"
							name="phone"
							placeholder="Phone Number"
							//value={fields.phoneNumber || ""}
							className="Profile-field-editable"
							onChange={(event) => setPhone(event.target.value)}
						></input>
					</div>
					<button type="submit">
						<p>Submit</p>
					</button>
				</form>
			</div>
		</>
	);
}

function ModifyProfile() {
	let [fields, setFields] = React.useState({});
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
				setFields({ fullName: data.fullName, cnp: data.cnp, dateOfBirth: data.dateOfBirth, email: "", phoneNumber: "" });
			});
	}, [userData]);

	return <ModifyForm fields={fields} set={setFields} />;
}

export default ModifyProfile;
