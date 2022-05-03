import React from "react";
import "./Student.css";
import "../view_curriculum/ViewCurriculum";
import ViewCurriculum from "../view_curriculum/ViewCurriculum";
import ViewProfile from "../view_profile/ViewProfile";
import ModifyProfile from "../modify_profile/ModifyProfile";

function StudentFeatureSelector({ feature_name }) {
	switch (feature_name) {
		case "view_curriculum": {
			return <ViewCurriculum />;
		}

		case "view_profile": {
			return <ViewProfile />;
		}

		case "modify_profile": {
			return <ModifyProfile />;
		}
		default: {
		}
	}
}

function Student({ setUserData }) {
	const [feature, setFeature] = React.useState("");

	return (
		<>
			<header className="App-header">
				<p>AcademicInfo - Student Section</p>
			</header>
			<div className="App-main">
				<div className="Sidebar">
					<div className="Sidebar-item">
						<p>Home</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("view_profile")}>
						<p>View Profile</p>
					</div>
					<div className="Sidebar-item " onClick={() => setFeature("modify_profile")}>
						<p>Modify Profile</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("view_curriculum")}>
						<p>View Curriculum</p>
					</div>
					<div className="Sidebar-item">
						<p>View Optionals</p>
					</div>
					<div className="Sidebar-item">
						<p>View Contract</p>
					</div>
					<div className="Sidebar-item">
						<p>View Grades</p>
					</div>
					<div className="Sidebar-logout-item" onClick={() => setUserData("")}>
						<p>Logout</p>
					</div>
				</div>
				<div className="Content">
					<StudentFeatureSelector feature_name={feature} />
				</div>
			</div>
		</>
	);
}

export default Student;
