import React from "react";
import "./Admin.css";
import { UserContext } from "../App";

function Admin({setUserData}) {
	return (
		<>
			<header className="App-header">
				<p>AcademicInfo - Admin Section</p>
			</header>
			<div className="Sidebar">
				<div className="Sidebar-item">
					<p>View/Print Documents</p>
				</div>
				<div className="Sidebar-logout-item" onClick={() => setUserData("")}>
					<p>Logout</p>
				</div>
			</div>
		</>
	);
}

export default Admin;
