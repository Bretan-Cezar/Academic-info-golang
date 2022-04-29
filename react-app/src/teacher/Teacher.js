import React from "react";
import "./Teacher.css";
import { UserContext } from "../App";

function Teacher({setUserData}) {
	return (
		<>
			<header className="App-header">
				<p>AcademicInfo - Teacher Section</p>
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
					<p>Propose Optional</p>
				</div>
				<div className="Sidebar-item">
					<p>Manage Grades</p>
				</div>
				<div className="Sidebar-logout-item" onClick={() => setUserData("")}>
					<p>Logout</p>
				</div>
			</div>
		</>
	);
}

export default Teacher;
