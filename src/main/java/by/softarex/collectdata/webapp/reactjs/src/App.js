import React from 'react';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";
import Footer from "./components/Footer";
import FieldList from "./components/FieldList";
import Field from "./components/Field";
import Response from "./components/Response";
import ThankYou from "./components/ThankYou";
import Registration from "./components/Registration";
import Login from "./components/Login";
import User from "./components/User"

function App() {
    const marginTop = {
        marginTop: "20px"
    }

    return (
        <Router>
            <NavigationBar/>
            <Container>
                <Row>
                    <Col lg={12} style={marginTop}>
                        <Switch>
                            <Route path="/" exact component={Welcome}/>
                            <Route path="/fields" exact component={FieldList}/>
                            <Route path="/field" exact component={Field}/>
                            <Route path="/fields/:id" exact component={Field}/>
                            <Route path="/questionnaires" exact component={Response}/>
                            <Route path="/сongratulations" exact component={ThankYou}/>
                            <Route path="/responses" exact component={Response}/>
                            <Route path="/registration" exact component={Registration}/>
                            <Route path="/login" exact component={Login}/>
                            <Route path="/user" exact component={User}/>
                        </Switch>
                    </Col>
                </Row>
            </Container>
            <Footer/>
        </Router>
    );
}

export default App;
