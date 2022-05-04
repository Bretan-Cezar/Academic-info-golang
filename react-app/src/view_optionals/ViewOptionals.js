import React from "react";
import "./ViewOptionals.css";
import { UserContext } from "../App";

function ViewOptionals() {
	const userData = React.useContext(UserContext);

	let [specList, setSpecList] = React.useState([]);
	let [selectedSpec, setSelectedSpec] = React.useState(-1);
	let [optionals, setOptionals] = React.useState([]);

	React.useEffect(() => {
		let urlSpec = "http://localhost:1337/http://localhost:8090/student/getSpecializations/" + userData.username;

		const specRequestOptions = {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "origin",
				Authorization: "Bearer " + userData.auth_token,
			},
		};

		const specRequest = new Request(urlSpec, specRequestOptions);
		fetch(specRequest)
			.then((response) => response.json())
			.then((data) => {
				setSpecList(data);
			});
	}, [userData]);

	return (
		<>
			<select
				className="Specialisation-selector"
				onChange={(e) => {
					setSelectedSpec(e.target.value);
				}}
				value={selectedSpec}
			>
				<option value="-1">--Select--</option>
				{specList.map((spec) => {
					return (
						<option key={spec.yosId} value={spec.yosId}>
							{spec.facultyYos.facultyName + " - specialization " + spec.specialization + ", year " + spec.yearNo}
						</option>
					);
				})}
			</select>
		</>
	);
}

export default ViewOptionals;
