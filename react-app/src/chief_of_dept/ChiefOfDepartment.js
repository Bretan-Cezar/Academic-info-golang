import React from "react";
import "./ChiefOfDepartment.css";
import { UserContext } from "../App";

function ChiefOfDepartment({setUserData}) {
	return (
		<>
			<header className="App-header">
				<p>AcademicInfo - Chief of Department Section</p>
			</header>
			<div className="Sidebar">
				<div className="Sidebar-item">
					<p>Home</p>
				</div>
				<div className="Sidebar-item">
					<p>View Profile</p>
				</div>
				<div className="Sidebar-item">
					<p>Modify Profile</p>
				</div>
				<div className="Sidebar-item">
					<p>Manage Grades</p>
				</div>
				<div className="Sidebar-item">
					<p>Manage Optionals</p>
				</div>
				<div className="Sidebar-item">
					<p>View (Filter/Sort) Disciplines</p>
				</div>
				<div className="Sidebar-logout-item" onClick={() => setUserData("")}>
					<p>Logout</p>
				</div>
			</div>
		</>
	);
}

export default ChiefOfDepartment;
