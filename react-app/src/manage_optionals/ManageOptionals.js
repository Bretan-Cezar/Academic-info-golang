import React from "react";
import "./ManageOptionals.css";
import { UserContext } from "../App";

let approveUrl = "http://localhost:1337/http://localhost:8090/chiefOfDepartment/approveOptional/";

function handleApprove(event, selected, att, setMsg, userData) {
	event.preventDefault();

	const requestOptions = {
		method: "PUT",
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "origin",
			Authorization: "Bearer " + userData.auth_token,
		},
		body: JSON.stringify({
			oDisciplineId: selected,
			maxAttendants: att,
		}),
	};

	const profileRequest = new Request(approveUrl, requestOptions);

	fetch(profileRequest)
		.then((response) => {
			return response.text();
		})
		.then((data) => {
			setMsg(data);
		});
}

function ApproveForm({ id, att, setAtt, msg, setMsg, selected, userData }) {
	if (id != -1) {
		return (
			<>
				<div className="Optional-form">
					<form onSubmit={(e) => handleApprove(e, selected, att, setMsg, userData)}>
						<div className="Optional-f">
							<p>Max. Attendants: </p>
							<input
								type="text"
								name="attendants"
								placeholder="Max. Attendants"
								//value={name}
								className="Optional-f-editable"
								onChange={(event) => setAtt(event.target.value)}
							></input>
						</div>

						<button type="submit">
							<p>Approve</p>
						</button>
					</form>
					<div className="Optional-f">{msg}</div>
				</div>
			</>
		);
	} else {
		return <></>;
	}
}

function ManageOptionals() {
	const userData = React.useContext(UserContext);

	let getUrl = "http://localhost:1337/http://localhost:8090/chiefOfDepartment/getOptionals/" + userData.username;
	let [optionalsList, setOptionalsList] = React.useState([]);
	let [selectedOptional, setSelectedOptional] = React.useState(-1);
	let [attendants, setAttendants] = React.useState(-1);
	let [message, setMessage] = React.useState("");

	React.useEffect(() => {
		const getRequestOptions = {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "origin",
				Authorization: "Bearer " + userData.auth_token,
			},
		};

		const getRequest = new Request(getUrl, getRequestOptions);

		fetch(getRequest)
			.then((response) => {
				return response.json();
			})
			.then((data) => {
				console.log(data);
				setOptionalsList(data);
			});
	}, [userData, selectedOptional]);

	return (
		<>
			<select
				className="Optionals-selector"
				onChange={(e) => {
					setSelectedOptional(e.target.value);
				}}
				value={selectedOptional}
			>
				<option value="-1">--Select--</option>
				{optionalsList.map((o) => {
					return (
						<option key={o.odisciplineId} value={o.odisciplineId}>
							{o.odisciplineId + " - " + o.disciplineName + " - by: " + o.teacherName + " - " + o.creditCount + " credits"}
						</option>
					);
				})}
			</select>
			<ApproveForm
				id={selectedOptional}
				att={attendants}
				setAtt={setAttendants}
				msg={message}
				setMsg={setMessage}
				selected={selectedOptional}
				userData={userData}
			/>
		</>
	);
}

export default ManageOptionals;
