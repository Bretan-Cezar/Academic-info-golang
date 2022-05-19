import React from "react";
import "./Admin.css";
import ViewProfile from "../view_profile/ViewProfile";
import ModifyProfile from "../modify_profile/ModifyProfile";
import ViewDocuments from "../view_documents/ViewDocuments";

function AdminFeatureSelector({ feature_name }) {
	switch (feature_name) {
		case "view_profile": {
			return <ViewProfile />;
		}

		case "modify_profile": {
			return <ModifyProfile />;
		}

		case "view_docs": {
			return <ViewDocuments />;
		}

		default: {
		}
	}
}

function Admin({ setUserData }) {
	const [feature, setFeature] = React.useState("");
	return (
		<>
			<header className="App-header">
				<p>AcademicInfo - Admin Section</p>
			</header>
			<div className="App-main">
				<div className="Sidebar">
					<div className="Sidebar-item" onClick={() => setFeature("view_profile")}>
						<p>View Profile</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("modify_profile")}>
						<p>Modify Profile</p>
					</div>
					<div className="Sidebar-item" onClick={() => setFeature("view_docs")}>
						<p>View/Print Documents</p>
					</div>
					<div className="Sidebar-logout-item" onClick={() => setUserData("")}>
						<p>Logout</p>
					</div>
				</div>
				<div className="Content">
					<AdminFeatureSelector feature_name={feature} />
				</div>
			</div>
		</>
	);
}

export default Admin;
