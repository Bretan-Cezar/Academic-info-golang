import React from "react";
import "./Student.css";
import "../view_curriculum/ViewCurriculum"
import ViewCurriculum from "../view_curriculum/ViewCurriculum";
import ViewProfile from "../view_profile/ViewProfile";

function StudentFeatureSelector( {feature_name} ) {

	switch (feature_name) {

		case "view_curriculum": {

			return <ViewCurriculum />
		}

		case "view_profile": {

			return <ViewProfile />
		}

		default: {

		}

	}
}


function Student({setUserData}) {

	const [feature, setFeature] = React.useState("");

	return (
		<>
			<header className="App-header">
				<p>AcademicInfo - Student Section</p>
			</header>
            <div className="App-content">
				<div className="Sidebar">
					<div className="Sidebar-item">
						<p>Home</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("view_profile")}>
						<p>View Profile</p>
					</div>
					<div className="Sidebar-item">
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

					<StudentFeatureSelector feature_name={feature} />

			</div>
		</>
	);
}

export default Student;
