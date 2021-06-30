import React, {Component} from 'react';
import {Button, Card, Col, Form, FormControl, InputGroup, Row} from 'react-bootstrap';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEnvelope, faLock, faPhone, faUser} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";

export default class EditPassword extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.findUserById(localStorage.getItem("userId"))
        // console.log(localStorage)
    }

    initialState = {
        password: '',
        newPassword: '',
    };

    editUser = () => {
        const userId = localStorage.getItem("userId")
        if (this.state.confirmPassword === this.state.newPassword) {
        axios.put(`http://localhost:8080/users/${userId}/password`)
            .then(response => {
                localStorage.setItem("firstName", response.data.firstName)
                window.location.reload();
            }).catch((error) => {
            alert("Wrong password")
        })
        } else {
            alert("Password mismatch")
        }
    };

    findUserById = (userId) => {
        axios.get(`http://localhost:8080/users/`+userId)
            .then(response => {
                this.setState({
                    // password: response.data.password
                })
            }).catch((error) => {
            console.error("Error - " + error);
        });
    }

    redirectCheck = () => {
        if (localStorage.getItem("authenticated")) {
            return this.props.history.push("/");
        }
    }

    userChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    };


    render() {
        const {password, newPassword, confirmPassword} = this.state;

        return (
            <Row className="justify-content-md-center">
                <Col xs={5}>
                    <Card className={"border border-dark bg-white text-dark"}>
                        <Card.Header>
                            Edit Profile
                        </Card.Header>
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faLock}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl required autoComplete="off" type="password" name="password"
                                                     value={password} onChange={this.userChange}
                                                     className={"bg-white text-dark"} placeholder="Current Password"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faLock}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl autoComplete="off" type="password" name="newPassword"
                                                     value={newPassword} onChange={this.userChange}
                                                     className={"bg-white text-dark"} placeholder="New Password"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text><FontAwesomeIcon icon={faLock}/></InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl required autoComplete="off" type="password" name="confirmPassword"
                                                     value={confirmPassword} onChange={this.userChange}
                                                     className={"bg-white text-dark"} placeholder="Confirm Password"/>
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{textAlign: "right"}}>
                            <Button size="sm" type="button" variant="primary"
                                    onClick={this.editUser}>
                                SAVE
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            </Row>
        );
    }
}