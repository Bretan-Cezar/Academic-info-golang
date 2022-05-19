import React from "react";
import "./ChooseOptionals.css";
import { UserContext } from "../App";

function cmp(a, b) {
	if (a.priority < b.priority) {
		return -1;
	}
	if (a.priority > b.priority) {
		return 1;
	}
	return 0;
}

function eq(a, b) {
	return a.odisciplineId === b.odisciplineId && a.disciplineName === b.disciplineName;
}

async function addOptional(optional, selected, setSelected, available, setAvailable) {
	let tmp_selected = selected.slice();
	let tmp_available = available.slice();

	tmp_selected.push({
		disciplineName: optional.disciplineName,
		maxAttendants: optional.maxAttendants,
		priority: selected.length + 1,

		odisciplineId: optional.odisciplineId,
		teacherName: optional.teacherName,
		creditCount: optional.creditCount,
	});

	const objToRemove = tmp_available.find((c) => eq(c, optional));
	const indexRemove = tmp_available.indexOf(objToRemove);

	tmp_available.splice(indexRemove, 1);

	setAvailable(tmp_available);
	setSelected(tmp_selected);
}

async function removeOptional(optional, selected, setSelected, available, setAvailable) {
	let tmp_selected = selected.slice();
	let tmp_available = available.slice();

	tmp_available.push({
		disciplineName: optional.disciplineName,
		maxAttendants: optional.maxAttendants,
		odisciplineId: optional.odisciplineId,
		teacherName: optional.teacherName,
		creditCount: optional.creditCount,
	});

	const objToRemove = tmp_selected.find((c) => eq(c, optional));
	const indexRemove = tmp_selected.indexOf(objToRemove);

	tmp_selected.splice(indexRemove, 1);

	tmp_selected.map((option) => (option.priority = tmp_selected.indexOf(option) + 1));

	setAvailable(tmp_available);
	setSelected(tmp_selected);
}

function submitHandler(userData, options_list, setMessage) {
	options_list.map((option) => {
		let sUrl = "http://localhost:1337/http://localhost:8090/student/addOptional/";

		const sReqOptions = {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "origin",
				Authorization: "Bearer " + userData.auth_token,
			},
			body: JSON.stringify({
				disciplineName: option.disciplineName,
				priority: option.priority,
			}),
		};

		const sRequest = new Request(sUrl, sReqOptions);

		fetch(sRequest)
			.then((response) => {
				return response.text();
			})
			.then((data) => {
				setMessage(data);
			});
	});
}

function AvailableOptionalsList({ availableOptionals, setAvailableOptionals, selectedOptionals, setSelectedOptionals }) {
	return (
		<>
			{availableOptionals.map((optional) => (
				<tr>
					<td className="Optionals-table-body-item">{optional.disciplineName}</td>
					<td className="Optionals-table-body-item">{optional.maxAttendants}</td>
					<td className="Optionals-table-body-item">
						<button
							value={JSON.stringify(optional)}
							className="AddRemove-optional-btn"
							onClick={(event) =>
								addOptional(JSON.parse(event.target.value), selectedOptionals, setSelectedOptionals, availableOptionals, setAvailableOptionals)
							}
						>
							Add
						</button>
					</td>
				</tr>
			))}
		</>
	);
}

function SelectedOptionalsList({ availableOptionals, setAvailableOptionals, selectedOptionals, setSelectedOptionals }) {
	return (
		<>
			{selectedOptionals.sort(cmp).map((optional) => (
				<tr>
					<td className="Optionals-table-body-item">{optional.disciplineName}</td>
					<td className="Optionals-table-body-item">{optional.maxAttendants}</td>
					<td className="Optionals-table-body-item">
						<button
							value={JSON.stringify(optional)}
							className="AddRemove-optional-btn"
							onClick={(event) => {
								removeOptional(JSON.parse(event.target.value), selectedOptionals, setSelectedOptionals, availableOptionals, setAvailableOptionals);
							}}
						>
							Remove
						</button>
					</td>
				</tr>
			))}
		</>
	);
}

function SubmitButton({ user, selectedOptionals }) {
	let [message, setMessage] = React.useState("");

	if (selectedOptionals.length !== 0) {
		if (message === "") {
			return (
				<>
					<button className="Submit-btn" onClick={() => submitHandler(user, selectedOptionals, setMessage)}>
						Submit Optionals
					</button>
				</>
			);
		} else {
			return (
				<>
					<div className="Submit-btn">{message}</div>
				</>
			);
		}
	}
}

function ChooseOptionals() {
	const userData = React.useContext(UserContext);

	let [facultyList, setFacultyList] = React.useState([]);
	let [selectedFaculty, setSelectedFaculty] = React.useState(-1);
	let [availableOptionalsList, setAvailableOptionalsList] = React.useState([]);
	let [selectedOptionalsList, setSelectedOptionalsList] = React.useState([]);

	React.useEffect(() => {
		let urlF = "http://localhost:1337/http://localhost:8090/student/getFaculties/";

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
			.then((response) => {
				return response.json();
			})
			.then((data) => {
				setFacultyList(data);
			});
	}, [userData]);

	React.useEffect(() => {
		if (selectedFaculty != -1) {
			let urlO = "http://localhost:1337/http://localhost:8090/student/getOptionals/" + selectedFaculty;

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
				.then((response) => {
					return response.json();
				})
				.then((data) => {
					setAvailableOptionalsList(data);
				});
		}
	}, [userData, selectedFaculty]);

	return (
		<>
			<select
				className="Y-selector"
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

			<div className="Table-container">
				<table cellSpacing="0" cellPadding="12" className="O-table">
					<thead>
						<tr>
							<td className="Optionals-table-head-item">Name</td>
							<td className="Optionals-table-head-item">Max. Attendants</td>
							<td className="Optionals-table-head-item">+</td>
						</tr>
					</thead>
					<tbody>
						<AvailableOptionalsList
							availableOptionals={availableOptionalsList}
							setAvailableOptionals={setAvailableOptionalsList}
							selectedOptionals={selectedOptionalsList}
							setSelectedOptionals={setSelectedOptionalsList}
						/>
					</tbody>
				</table>
				<table cellSpacing="0" cellPadding="12" className="O-table">
					<thead>
						<tr>
							<td className="Optionals-table-head-item">Name</td>
							<td className="Optionals-table-head-item">Max. Attendants</td>
							<td className="Optionals-table-head-item">-</td>
						</tr>
					</thead>
					<tbody>
						<SelectedOptionalsList
							availableOptionals={availableOptionalsList}
							setAvailableOptionals={setAvailableOptionalsList}
							selectedOptionals={selectedOptionalsList}
							setSelectedOptionals={setSelectedOptionalsList}
						/>
					</tbody>
				</table>
			</div>
			<SubmitButton user={userData} selectedOptionals={selectedOptionalsList} />
		</>
	);
}

export default ChooseOptionals;
