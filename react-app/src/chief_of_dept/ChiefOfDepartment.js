import React from "react";
import "./ChiefOfDepartment.css";
import ViewProfile from "../view_profile/ViewProfile";
import ModifyProfile from "../modify_profile/ModifyProfile";
import ManageOptionals from "../manage_optionals/ManageOptionals";
import AddGrades from "../add_grades/AddGrades";
import FilterDisciplines from "../filter_disciplines/FilterDisciplines";

function ChiefFeatureSelector({ feature_name }) {
	switch (feature_name) {
		case "view_profile": {
			return <ViewProfile />;
		}

		case "modify_profile": {
			return <ModifyProfile />;
		}

		case "manage_optionals": {
			return <ManageOptionals />;
		}

		case "manage_grades": {
			return <AddGrades />;
		}

		case "filter_disciplines": {
			return <FilterDisciplines />;
		}
		default: {
		}
	}
}

function ChiefOfDepartment({ setUserData }) {
	const [feature, setFeature] = React.useState("");
	return (
		<>
			<header className="App-header">
				<p>AcademicInfo - Chief of Department Section</p>
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
					<div className="Sidebar-item" onClick={() => setFeature("manage_grades")}>
						<p>Manage Grades</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("manage_optionals")}>
						<p>Manage Optionals</p>
					</div>
					<div className="Sidebar-item">
						<p>View (Filter/Sort) Disciplines</p>
					</div>
					<div className="Sidebar-item">
						<p>View (Filter/Sort) Teachers</p>
					</div>
					<div className="Sidebar-logout-item" onClick={() => setUserData("")}>
						<p>Logout</p>
					</div>
				</div>
				<div className="Content">
					<ChiefFeatureSelector feature_name={feature} />
				</div>
			</div>
		</>
	);
}

export default ChiefOfDepartment;
