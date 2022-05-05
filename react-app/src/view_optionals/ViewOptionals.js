import React from "react";
import "./ViewOptionals.css";
import { UserContext } from "../App";

function OptionalsList({ optionals_array }) {
	return (
		<>
			{optionals_array.map((optional) => (
				<tr>
					<td className="Optionals-table-body-item">{optional.disciplineName}</td>
					<td className="Optionals-table-body-item">{optional.teacherName}</td>
					<td className="Optionals-table-body-item">{optional.creditCount}</td>
					<td className="Optionals-table-body-item">{optional.maxAttendants}</td>
				</tr>
			))}
		</>
	);
}

function ViewOptionals() {
	const userData = React.useContext(UserContext);

	let [facultyList, setFacultyList] = React.useState([]);
	let [selectedFaculty, setSelectedFaculty] = React.useState(-1);
	let [optionalsList, setOptionalsList] = React.useState([]);

	React.useEffect(() => {
		let urlF = "http://localhost:1337/http://localhost:8090/student/getFaculties/" + userData.username;

		const fRequestOptions = {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "origin",
				Authorization: "Bearer " + userData.auth_token,
			},
		};

		const fRequest = new Request(urlF, fRequestOptions);
		fetch(fRequest)
			.then((response) => response.json())
			.then((data) => {
				setFacultyList(data);
			});

		if (selectedFaculty != -1) {
			let urlO = "http://localhost:1337/http://localhost:8090/student/getOptionals/" + userData.username + "/" + selectedFaculty;

			const oRequestOptions = {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
					"Access-Control-Allow-Origin": "origin",
					Authorization: "Bearer " + userData.auth_token,
				},
			};

			const oRequest = new Request(urlO, oRequestOptions);
			fetch(oRequest)
				.then((response) => response.json())
				.then((data) => {
					// console.log(data);
					setOptionalsList(data);
				});
		} else {
			setOptionalsList([]);
		}
	}, [userData, selectedFaculty]);

	return (
		<>
			<select
				className="Faculty-selector"
				onChange={(e) => {
					setSelectedFaculty(e.target.value);
				}}
				value={selectedFaculty}
			>
				<option value="-1">--Select--</option>
				{facultyList.map((f) => {
					return (
						<option key={f.facultyId} value={f.facultyId}>
							{f.facultyId + " - " + f.facultyName}
						</option>
					);
				})}
			</select>
			<table cellSpacing="0" cellPadding="12" className="Optionals-table">
				<thead>
					<tr>
						<td className="Optionals-table-head-item">Name</td>
						<td className="Optionals-table-head-item">Teacher</td>
						<td className="Optionals-table-head-item">Credits</td>
						<td className="Optionals-table-head-item">Max. Attendants</td>
					</tr>
				</thead>
				<tbody>
					<OptionalsList optionals_array={optionalsList} />
				</tbody>
			</table>
		</>
	);
}

export default ViewOptionals;
