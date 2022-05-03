import React from "react";
import "./ViewCurriculum.css";
import { UserContext } from "../App";

function Discipline({ curr_discipline, index }) {
	return (
		<React.Fragment>
			<tr>
				<td className="Curriculum-table-body-item">{curr_discipline.discipline_type}</td>
				<td className="Curriculum-table-body-item">{curr_discipline.discipline_name}</td>
				<td className="Curriculum-table-body-item">{curr_discipline.teacher_name}</td>
				<td className="Curriculum-table-body-item">{curr_discipline.credit_count}</td>
			</tr>
		</React.Fragment>
	);
}

function Curriculum({ value, index }) {
	return (
		<React.Fragment>
			<option value={value}>{value.specialisation + " - year " + value.yearOfStudy}</option>
		</React.Fragment>
	);
}

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

// function CurriculumList({ curriculum_list, setCurriculum }) {
// 	let [selectedOption, setOption] = React.useState(-1);

// 	return (
// 		<>
// 			<select
// 				className="Curriculum-selector"
// 				onChange={(e) => {
// 					curriculumSelectHandler(e, setOption, setCurriculum);
// 				}}
// 				value={selectedOption.value}
// 			>
// 				<option value="-1">--Select--</option>
// 				{curriculum_list.map((curriculum) => (
// 					<option key={curriculum.id} value={curriculum.id}>
// 						{curriculum.specialisation + " - year " + curriculum.yearOfStudy}
// 					</option>
// 				))}
// 			</select>
// 		</>
// 	);
// }

function ViewCurriculum() {
	const userData = React.useContext(UserContext);

	let curriculumsList;
	/*
  	fetch(url)
  	.then(response => response.json())
  	.then(data => curriculumsList(data));
  */

	curriculumsList = [
		{ id: 120, specialisation: "Computer Science in English", yearOfStudy: 1 },
		{ id: 121, specialisation: "Computer Science in English", yearOfStudy: 2 },
	];

	let [selectedCurriculum, setSelectedCurriculum] = React.useState(-1);
	let [disciplines, setDisciplines] = React.useState([]);

	React.useEffect(() => {
		let url = "http://localhost:1337/http://localhost:8090/user/getCurriculum/";
		url += userData.user_id;

		// data = [{discipline_type: mandatory/optional, discipline_name: ..., teacher_name: ..., credit_count: ...}, ...]

		/*
      	url += "/" + { selectedCurriculum };
      	fetch(url)
      	.then(response => response.json())
      	.then(data => setDisciplines(data))
        */

		console.log(selectedCurriculum);

		if (selectedCurriculum == 120) {
			console.log("120");
			setDisciplines([
				{ discipline_type: "mandatory", discipline_name: "Systems For Design & Implementation", teacher_name: "Dr. Gaceanu", credit_count: 6 },
				{ discipline_type: "optional", discipline_name: "A/V Data Processing", teacher_name: "Forest", credit_count: 4 },
			]);
		} else if (selectedCurriculum == 121) {
			console.log("121");
			setDisciplines([
				{ discipline_type: "mandatory", discipline_name: "Data Structures & Algorithms", teacher_name: "Zsu", credit_count: 5 },
				{ discipline_type: "optional", discipline_name: "C Programming", teacher_name: "Grebla", credit_count: 3 },
			]);
		} else {
			setDisciplines([]);
		}
	}, [userData.user_id, userData.full_name, selectedCurriculum]);

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
					<option key={curriculum.id} value={curriculum.id}>
						{curriculum.specialisation + " - year " + curriculum.yearOfStudy}
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
