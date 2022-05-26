import React from "react";
import "./TeacherX.css";
import { UserContext } from "../App";

function TeacherTable({ teacher }) {
	if (teacher !== "") {
		return (
			<>
				<table cellSpacing="0" cellPadding="12" className="Teacher-table">
					<thead>
						<tr>
							<td className="Teacher-table-head-item">Name</td>
							<td className="Teacher-table-head-item">Performance</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td className="Teacher-table-body-item">{teacher.teacherName}</td>
							<td className="Teacher-table-body-item">{teacher.performance}</td>
						</tr>
					</tbody>
				</table>
			</>
		);
	}
}

function TeacherX() {
	const userData = React.useContext(UserContext);
	let [selectedOption, setSelectedOption] = React.useState("-1");
	let [teacher, setTeacher] = React.useState("");

	React.useEffect(() => {
		if (selectedOption !== "-1") {
			let tUrl = "";
			switch (selectedOption) {
				case "best": {
					tUrl = "http://localhost:1337/http://localhost:8090/chiefOfDepartment/getBestTeacher/";
					break;
				}

				case "worst": {
					tUrl = "http://localhost:1337/http://localhost:8090/chiefOfDepartment/getWorstTeacher/";
					break;
				}

				default: {
				}
			}

			const tRequestOptions = {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
					"Access-Control-Allow-Origin": "origin",
					Authorization: "Bearer " + userData.auth_token,
				},
			};

			const tRequest = new Request(tUrl, tRequestOptions);

			fetch(tRequest)
				.then((response) => {
					return response.json();
				})
				.then((data) => {
					setTeacher(data);
				});
		}
	}, [selectedOption]);

	return (
		<>
			<select
				className="Best-Worst-selector"
				onChange={(e) => {
					setSelectedOption(e.target.value);
				}}
				value={selectedOption}
			>
				<option value="-1">--Select--</option>
				<option value="best">Top-Performing</option>
				<option value="worst">Bottom-Performing</option>
			</select>
			<TeacherTable teacher={teacher} />
		</>
	);
}

export default TeacherX;
