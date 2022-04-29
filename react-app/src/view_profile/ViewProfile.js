import React from "react";
import "./ViewProfile.css";
import { UserContext } from "../App";

let url = 'localhost:1337/view_curriculum?q=react';

function ViewProfile() {

    let [profile, setProfile] = React.useState("");

    const userData = React.useContext(UserContext);

    React.useEffect(() => {

        url += "&user_id=" + userData.user_id + "&full_name=" + userData.full_name;

        // data = {full_name: ..., cnp: ..., dob: "YYYY-MM-DD", email: ..., phone_no: ...}

        /*
        fetch(url)
            .then(response => response.json())
            .then(data => setProfile(data));

         */

        // Example data
        setProfile({full_name: "Bretan Cezar-Alexandru", cnp: "5020110125775", dob: "2002-01-10", email: "cezar.bretan@gmail.com", phone_no: "+40734584843"});


    }, [userData.user_id, userData.full_name]);

    return (
        <>

            <div className="Profile-container">

                <div className="Profile-field">
                    <p>Full Name: </p>
                    <span>{profile.full_name}</span>
                </div>

                <div className="Profile-field">
                    <p>CNP: </p>
                    <span>{profile.cnp}</span>
                </div>

                <div className="Profile-field">
                    <p>Date of Birth: </p>
                    <span>{profile.dob}</span>
                </div>

                <div className="Profile-field">
                    <p>E-mail: </p>
                    <span>{profile.email}</span>
                </div>

                <div className="Profile-field">
                    <p>Phone Number: </p>
                    <span>{profile.phone_no}</span>
                </div>

            </div>

        </>
    );

}

export default ViewProfile;