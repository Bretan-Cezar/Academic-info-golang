import React from "react";
import "./ViewGrades.css";
import { UserContext } from "../App";

function GradesList({ grades_array }) {
	return (
		<>
			{grades_array.map((grade) => (
				<tr>
					<td className="Grades-table-body-item">{grade.disciplineName}</td>
					<td className="Grades-table-body-item">{grade.creditCount}</td>
					<td className="Grades-table-body-item">{grade.value}</td>
				</tr>
			))}
		</>
	);
}

function ViewGrades() {
	const userData = React.useContext(UserContext);

	let [yearsList, setYearsList] = React.useState([]);
	let [selectedYear, setSelectedYear] = React.useState(-1);
	let [gradesList, setGradesList] = React.useState([]);

	let yearUrl = "http://localhost:1337/http://localhost:8090/student/getFacultiesAndSpecializations/";

	React.useEffect(() => {
		const yearRequestOptions = {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "origin",
				Authorization: "Bearer " + userData.auth_token,
			},
		};

		const yearRequest = new Request(yearUrl, yearRequestOptions);

		fetch(yearRequest)
			.then((response) => response.json())
			.then((data) => {
				console.log(data);
				setYearsList(data);
			});

		if (selectedYear != -1) {
			let gradesUrl = "http://localhost:1337/http://localhost:8090/student/getGrades/" + selectedYear;

			const gradesRequestOptions = {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
					"Access-Control-Allow-Origin": "origin",
					Authorization: "Bearer " + userData.auth_token,
				},
			};

			const gradesRequest = new Request(gradesUrl, gradesRequestOptions);

			fetch(gradesRequest)
				.then((response) => response.json())
				.then((data) => {
					console.log(data);
					setGradesList(data);
				});
		}
	}, [userData, selectedYear]);

	return (
		<>
			<select
				className="Year-selector"
				onChange={(e) => {
					setSelectedYear(e.target.value);
				}}
				value={selectedYear}
			>
				<option value="-1">--Select--</option>
				{yearsList.map((y) => {
					return (
						<option key={y.yosId} value={y.yosId}>
							{y.yosId + " - " + y.facultyName + ", specialization " + y.specialization + " - year " + y.yearNo}
						</option>
					);
				})}
			</select>
			<table cellSpacing="0" cellPadding="12" className="Grades-table">
				<thead>
					<tr>
						<td className="Grades-table-head-item">Name</td>
						<td className="Grades-table-head-item">Credits</td>
						<td className="Grades-table-head-item">Value</td>
					</tr>
				</thead>
				<tbody>
					<GradesList grades_array={gradesList} />
				</tbody>
			</table>
		</>
	);
}

export default ViewGrades;
