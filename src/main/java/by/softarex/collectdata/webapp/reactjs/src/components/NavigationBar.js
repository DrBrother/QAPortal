import React from 'react';

import {Navbar, Nav, NavDropdown} from 'react-bootstrap';
import {Link, Route, Switch, withRouter} from 'react-router-dom';
import {faSignInAlt, faUserPlus} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";




 class NavigationBar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: localStorage.getItem("firstName"),
            authenticated: localStorage.getItem("authenticated")
        }
        this.redirectCheck()
    }

    logout = () => {
        axios.get(`http://localhost:8080/logout`)
            .then(response => {
                localStorage.removeItem("firstName")
                localStorage.removeItem("lastName")
                localStorage.removeItem("userId")
                localStorage.removeItem("authenticated");

                this.props.history.push("/");
                window.location.reload();
            }).catch((error) => {
            alert(error)
        });
    };

    redirectCheck = () => {
        if (!localStorage.getItem("authenticated")) {
            return this.props.history.push("/");
        }
    }

    render() {

        const guestLinks = (
            <>
                <div className="mr-auto"/>
                <Nav className="navbar-right">
                    <Link to={"responses"} className="navbar-brand">Responses</Link>
                    <Link to={"/registration"} className="nav-link"><FontAwesomeIcon
                        icon={faUserPlus}/> Registration</Link>
                    <Link to={"/login"} className="nav-link"><FontAwesomeIcon icon={faSignInAlt}/> Login</Link>
                </Nav>
            </>
        );

        const userLinks = (
            <>
                <Nav className="navbar-right" className="ml-auto">
                    <NavDropdown  title={this.state.username} id="navbarScrollingDropdown">
                        <NavDropdown.Item href={'/edit'}>Edit Profile</NavDropdown.Item>
                        <NavDropdown.Item href={'/password'}>Change password</NavDropdown.Item>
                        <NavDropdown.Item onClick={this.logout }>Logout</NavDropdown.Item>
                    </NavDropdown>
                    <Link to={"fields"} className="navbar-brand">Fields </Link>
                    <Link to={"responses"} className="navbar-brand">Responses</Link>
                </Nav>
            </>
        );
        return (
            <Navbar bg="light" variant="bright">
                <Link to={""} className="navbar-brand">
                    <img
                        src="https://hsto.org/getpro/moikrug/uploads/user/100/023/360/1/avatar/727742be38878ff36b22555071a0a427.jpg"
                        width="25" height="25" alt="brand"/> QA portal

                </Link>

                {this.state.authenticated ? userLinks : guestLinks}



            </Navbar>

        )
    }
} export default withRouter(NavigationBar);