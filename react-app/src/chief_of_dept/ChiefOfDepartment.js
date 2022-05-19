import React from "react";
import "./ChiefOfDepartment.css";
import ViewProfile from "../view_profile/ViewProfile";
import ModifyProfile from "../modify_profile/ModifyProfile";
import ManageOptionals from "../manage_optionals/ManageOptionals";
import AddGrades from "../add_grades/AddGrades";
import FilterDisciplines from "../filter_disciplines/FilterDisciplines";
import TeacherX from "../teacher_x/TeacherX";

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

		case "view_teacher_x": {
			return <TeacherX />;
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
					<div className="Sidebar-item" onClick={() => setFeature("filter_disciplines")}>
						<p>Filter Disciplines By Teacher</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("view_teacher_x")}>
						<p>
							View Top-/Bottom-
							<br />
							Performing Teacher
						</p>
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
