import React from 'react';

import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom';


class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <img
                        src="https://hsto.org/getpro/moikrug/uploads/user/100/023/360/1/avatar/727742be38878ff36b22555071a0a427.jpg"
                        width="25" height="25" alt="brand"/> QA portal

                </Link>
                <Navbar.Brand href="/">

                </Navbar.Brand>
                <Nav className="me-auto">
                    <Link to={"fields"} className="navbar-brand"> Fields </Link>
                </Nav>
            </Navbar>
        )
    }
}

export default NavigationBar;