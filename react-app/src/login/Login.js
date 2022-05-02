import React from "react";
import "./Login.css";

function LoginForm({ set }) {
	const [username, setUsername] = React.useState("");
	const [password, setPassword] = React.useState("");

	let url = "http://localhost:1337/http://localhost:8090/auth/login";

	async function submitHandler(event) {
		event.preventDefault();

		const submit_data = { username: username, password: password };

		// const received_data = { status: "ok/fail", user_type: "admin/chief_of_dept/student/teacher", user_id: ..., full_name: ... };

		const requestOptions = {
			method: "POST",
			headers: { "Content-Type": "application/json", "Access-Control-Allow-Origin": "origin" },
			body: JSON.stringify(submit_data),
		};

		const loginRequest = new Request(url, requestOptions);

		let token = "";

		fetch(loginRequest)
			.then((response) => {
				token = response.headers.get("authorization");
				return response.json();
			})
			.then((data) => {
				if (data.message === "Successful") {
					set({ status: data.message, username: data.username, auth_token: token });
				} else {
					set({ status: data.message, username: "", auth_token: "" });
				}
			});
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
