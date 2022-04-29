import React from "react";
import "./Login.css";

function LoginForm({ set }) {
	const [username, setUsername] = React.useState("");
	const [password, setPassword] = React.useState("");

	let url = "http://localhost:1337/login?q=react";

	/*
	async function submitHandler(event) {
		event.preventDefault();

		const submit_data = { username: username, password: password };

		url += "&username=" + submit_data.username + "&password=" + submit_data.password;

		// const received_data = { status: "ok/fail", user_type: "admin/chief_of_dept/student/teacher", user_id: ..., full_name: ... };

		const response = await fetch(url);
		const received_data = response.json();

		console.log(received_data);
		set(received_data);
	}

	 */

	function submitHandler(event) {
		event.preventDefault();

		const data = { status: "ok", username: username, password: password };
		set(data);
		console.log(data);
	}

	return (
		<>
			<form onSubmit={submitHandler}>
				<input placeholder="Username" onChange={(event) => setUsername(event.target.value)} />
				<input type="password" placeholder="Password" onChange={(event) => setPassword(event.target.value)} />
				<button type="submit">Submit</button>
			</form>
		</>
	);
}

function Login({ setUser }) {
	return (
		<>
			<header className="App-header">
				<p>AcademicInfo - Login</p>
			</header>
			<LoginForm set={setUser} />
		</>
	);
}

export default Login;
