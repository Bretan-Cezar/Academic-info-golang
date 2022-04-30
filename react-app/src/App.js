import React from "react";
import "./App.css";
import Login from "./login/Login";
import Student from "./student/Student";
import ChiefOfDepartment from "./chief_of_dept/ChiefOfDepartment";
import Teacher from "./teacher/Teacher";
import Admin from "./admin/Admin";

export const UserContext = React.createContext("");

function App() {
	const [userData, setUserData] = React.useState("");

	if (!userData) {
		document.title = "AcademicInfo Login";
		return (
			<>
				<div className="App">
					<Login setUser={setUserData} />
				</div>
			</>
		);
	}

	if (userData.status === "Unauthorized user") {
		document.title = "AcademicInfo Login";
		return (
			<>
				<div className="App">
					<Login setUser={setUserData} />
					<p className="Auth-error-msg">Username or password incorrect!</p>
				</div>
			</>
		);
	}

	// let userType = userData.user_type
	let userType = "student";

	switch (userType) {
		case "student":
			document.title = "AcademicInfo - Student";
			return (
				<UserContext.Provider value={userData}>
					<Student setUserData={setUserData} />
				</UserContext.Provider>
			);

		case "teacher":
			document.title = "AcademicInfo - Teacher";
			return (
				<UserContext.Provider value={userData}>
					<Teacher setUserData={setUserData} />
				</UserContext.Provider>
			);

		case "chief":
			document.title = "AcademicInfo - Chief of Dept.";
			return (
				<UserContext.Provider value={userData}>
					<ChiefOfDepartment setUserData={setUserData} />
				</UserContext.Provider>
			);

		case "admin":
			document.title = "AcademicInfo - Admin";
			return (
				<UserContext.Provider value={userData}>
					<Admin setUserData={setUserData} />
				</UserContext.Provider>
			);
		default:
			return <></>;
	}
}

export default App;
