import React from "react";
import { UserContext } from "../App";

function DisciplinesList({ disciplines_array }) {
	return (
		<>
			<table>
				<thead>
					<td className="Disciplines-table-head-item">Discipline Type</td>
					<td className="Disciplines-table-head-item">Discipline Name</td>
					<td className="Disciplines-table-head-item">Credits</td>
				</thead>
				<tbody>
					{disciplines_array.map((discipline) => (
						<tr>
							<td className="Disciplines-table-body-item">{discipline.disciplineType}</td>
							<td className="Disciplines-table-body-item">{discipline.disciplineName}</td>
							<td className="Disciplines-table-body-item">{discipline.creditCount}</td>
						</tr>
					))}
				</tbody>
			</table>
		</>
	);
}

function FilterDisciplines() {
	const userData = React.useContext(UserContext);

	let [selectedTeacher, setSelectedTeacher] = React.useState(-1);
	let [teachersList, setTeachersList] = React.useState([]);
	let [disciplinesList, setDisciplinesList] = React.useState([]);

	React.useEffect(() => {
		let tUrl = "http://localhost:1337/http://localhost:8090/chiefOfDepartment/getTeachers/";

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
				setTeachersList(data);
			});
	}, []);

	React.useEffect(() => {
		if (selectedTeacher != -1) {
			let dUrl = "http://localhost:1337/http://localhost:8090/chiefOfDepartment/getDisciplines/" + selectedTeacher;

			const dRequestOptions = {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
					"Access-Control-Allow-Origin": "origin",
					Authorization: "Bearer " + userData.auth_token,
				},
			};

			const dRequest = new Request(dUrl, dRequestOptions);

			fetch(dRequest)
				.then((response) => {
					return response.json();
				})
				.then((data) => {
					setDisciplinesList(data);
				});
		}
	}, [selectedTeacher]);

	return (
		<>
			<select
				className="Teachers-selector"
				onChange={(e) => {
					setSelectedTeacher(e.target.value);
				}}
				value={selectedTeacher}
			>
				<option value="-1">--Select--</option>
				{teachersList.map((t) => {
					return (
						<option key={t.teacherId} value={t.teacherId}>
							{t.teacherId + " - " + t.teacherName}
						</option>
					);
				})}
			</select>
			<DisciplinesList disciplines_array={disciplinesList} />
		</>
	);
}

export default FilterDisciplines;
