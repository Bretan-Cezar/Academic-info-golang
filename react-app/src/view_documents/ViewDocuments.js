import React from "react";
import "./ViewDocuments.css";
import { UserContext } from "../App";

function viewStudentsGroups(userData) {
	let url = "http://localhost:1337/http://localhost:8090/admin/getStudentsOrderedByGrades/pdf";

	const docRequestOptions = {
		method: "GET",
		headers: {
			"Content-Type": "application/pdf",
			"Access-Control-Allow-Origin": "origin",
			Authorization: "Bearer " + userData.auth_token,
		},
	};

	const docRequest = new Request(url, docRequestOptions);

	fetch(docRequest)
		.then((response) => {
			return response.blob();
		})
		.then((data) => {
			var link = document.createElement("a");
			link.href = window.URL.createObjectURL(data);
			link.download = "students_groups.pdf";
			link.click();
		});
}

function viewStudentsSorted(userData) {
	let url = "http://localhost:1337/http://localhost:8090/admin/getAllStudentsOrderedByGrades/pdf";

	const docRequestOptions = {
		method: "GET",
		headers: {
			"Content-Type": "application/pdf",
			"Access-Control-Allow-Origin": "origin",
			Authorization: "Bearer " + userData.auth_token,
		},
	};

	const docRequest = new Request(url, docRequestOptions);

	fetch(docRequest)
		.then((response) => {
			return response.blob();
		})
		.then((data) => {
			var link = document.createElement("a");
			link.href = window.URL.createObjectURL(data);
			link.download = "students_sorted.pdf";
			link.click();
		});
}

function viewStudentsYears(userData) {
	let url = "http://localhost:1337/http://localhost:8090/admin/getStudentsForYearByGrades/pdf";

	const docRequestOptions = {
		method: "GET",
		headers: {
			"Content-Type": "application/pdf",
			"Access-Control-Allow-Origin": "origin",
			Authorization: "Bearer " + userData.auth_token,
		},
	};

	const docRequest = new Request(url, docRequestOptions);

	fetch(docRequest)
		.then((response) => {
			return response.blob();
		})
		.then((data) => {
			var link = document.createElement("a");
			link.href = window.URL.createObjectURL(data);
			link.download = "students_years.pdf";
			link.click();
		});
}

function ViewDocuments() {
	let userData = React.useContext(UserContext);

	return (
		<>
			<button className="doc-btn" onClick={() => viewStudentsGroups(userData)}>
				View the list of students from each group ordered by their professional results
			</button>
			<button className="doc-btn" onClick={() => viewStudentsSorted(userData)}>
				View students classified in decreasing order of obtained results
			</button>
			<button className="doc-btn" onClick={() => viewStudentsYears(userData)}>
				View the list of students from each year ordered by their professional results
			</button>
		</>
	);
}

export default ViewDocuments;
