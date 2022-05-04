import React from "react";
import "./Teacher.css";
import ViewProfile from "../view_profile/ViewProfile";
import ModifyProfile from "../modify_profile/ModifyProfile";
import ProposeOptional from "../propose_optional/ProposeOptional";

function TeacherFeatureSelector({ feature_name }) {
	switch (feature_name) {
		case "view_profile": {
			return <ViewProfile />;
		}

		case "modify_profile": {
			return <ModifyProfile />;
		}

		case "propose_optional": {
			return <ProposeOptional />;
		}
		default: {
		}
	}
}

function Teacher({ setUserData }) {
	const [feature, setFeature] = React.useState("");
	return (
		<>
			<header className="App-header">
				<p>AcademicInfo - Teacher Section</p>
			</header>
			<div className="App-main">
				<div className="Sidebar">
					<div className="Sidebar-item">
						<p>Home</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("view_profile")}>
						<p>View Profile</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("modify_profile")}>
						<p>Modify Profile</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("propose_optional")}>
						<p>Propose Optional</p>
					</div>
					<div className="Sidebar-item">
						<p>Manage Grades</p>
					</div>
					<div className="Sidebar-logout-item" onClick={() => setUserData("")}>
						<p>Logout</p>
					</div>
				</div>
				<div className="Content">
					<TeacherFeatureSelector feature_name={feature} />
				</div>
			</div>
		</>
	);
}

export default Teacher;
