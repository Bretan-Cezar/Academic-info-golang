import React from "react";
import "./ViewCurriculum.css";
import { UserContext } from "../App";

function DisciplineList({ discipline_array }) {
	return (
		<>
			{discipline_array.map((discipline) => (
				<tr>
					<td className="Curriculum-table-body-item">{discipline.discipline_type}</td>
					<td className="Curriculum-table-body-item">{discipline.discipline_name}</td>
					<td className="Curriculum-table-body-item">{discipline.teacher_name}</td>
					<td className="Curriculum-table-body-item">{discipline.credit_count}</td>
				</tr>
			))}
		</>
	);
}

function ViewCurriculum() {
	const userData = React.useContext(UserContext);

	let [curriculumsList, setCurriculumsList] = React.useState([]);

	// curriculumsList = [
	// 	{ cid: 120, specialisation: "Computer Science in English", yearNo: 1 },
	// 	{ cid: 121, specialisation: "Computer Science in English", yearNo: 2 },
	// ];

	let [selectedCurriculum, setSelectedCurriculum] = React.useState(-1);
	let [disciplines, setDisciplines] = React.useState([]);

	React.useEffect(() => {
		let cListUrl = "http://localhost:1337/http://localhost:8090/user/getYears/" + userData.username;

		const cListReqOptions = {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "origin",
				Authorization: "Bearer " + userData.auth_token,
			},
		};

		const cListReq = new Request(cListUrl, cListReqOptions);

		fetch(cListReq)
			.then((response) => {
				return response.json();
			})
			.then((data) => {
				console.log(data);
				setCurriculumsList(data);
			});
	}, [userData]);

	React.useEffect(() => {
		if (selectedCurriculum != -1) {
			let dListUrl = "http://localhost:1337/http://localhost:8090/user/getCurriculum/" + userData.username + "/" + selectedCurriculum;

			// data = [{discipline_type: mandatory/optional, discipline_name: ..., teacher_name: ..., credit_count: ...}, ...]

			const dListReqOptions = {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
					"Access-Control-Allow-Origin": "origin",
					Authorization: "Bearer " + userData.auth_token,
				},
			};

			const dListReq = new Request(dListUrl, dListReqOptions);

			fetch(dListReq)
				.then((response) => {
					return response.json();
				})
				.then((data) => {
					setDisciplines(data);
				});
		}
	}, [userData, selectedCurriculum]);

	return (
		<>
			<select
				className="Curriculum-selector"
				onChange={(e) => {
					setSelectedCurriculum(e.target.value);
				}}
				value={selectedCurriculum}
			>
				<option value="-1">--Select--</option>
				{curriculumsList.map((curriculum) => (
					<option key={curriculum.curriculumId} value={curriculum.curriculumId}>
						{curriculum.specialization + " - year " + curriculum.year_number}
					</option>
				))}
			</select>
			<table cellSpacing="0" cellPadding="12" className="Curriculum-table">
				<thead>
					<tr>
						<td className="Curriculum-table-head-item">Type</td>
						<td className="Curriculum-table-head-item">Name</td>
						<td className="Curriculum-table-head-item">Teacher</td>
						<td className="Curriculum-table-head-item">Credits</td>
					</tr>
				</thead>
				<tbody>
					<DisciplineList discipline_array={disciplines} />
				</tbody>
			</table>
		</>
	);
}

export default ViewCurriculum;
