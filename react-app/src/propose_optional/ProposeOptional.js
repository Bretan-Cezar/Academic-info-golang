import React from "react";
import "./ProposeOptional.css";
import { UserContext } from "../App";

function ProposeForm() {
	let url = "http://localhost:1337/http://localhost:8090/teacher/proposeOptional";
	const userData = React.useContext(UserContext);
	let [name, setName] = React.useState("");
	let [credits, setCredits] = React.useState("");
	let [message, setMessage] = React.useState("");

	const handleSubmit = (event) => {
		event.preventDefault();

		const requestOptions = {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "origin",
				Authorization: "Bearer " + userData.auth_token,
			},
			body: JSON.stringify({
				username: userData.username,
				disciplineName: name,
				creditCount: credits,
			}),
		};

		const profileRequest = new Request(url, requestOptions);

		fetch(profileRequest)
			.then((response) => {
				return response.text();
			})
			.then((data) => {
				setMessage(data);
			});
	};

	return (
		<>
			<div className="Optional-container">
				<form onSubmit={handleSubmit}>
					<div className="Optional-field">
						<p>Optional Name: </p>
						<input
							type="text"
							name="name"
							placeholder="Optional Name"
							//value={name}
							className="Optional-field-editable"
							onChange={(event) => setName(event.target.value)}
						></input>
					</div>

					<div className="Optional-field">
						<p>Credit Count: </p>
						<input
							type="text"
							name="credits"
							placeholder="Credit Count"
							//value={credits}
							className="Optional-field-editable"
							onChange={(event) => setCredits(event.target.value)}
						></input>
					</div>

					<button type="submit">
						<p>Submit</p>
					</button>
				</form>
				<div className="Optional-field">{message}</div>
			</div>
		</>
	);
}

function ProposeOptional() {
	return <ProposeForm />;
}

export default ProposeOptional;
