import React from 'react';
import {Form, Button, Col} from 'react-bootstrap';


import {Jumbotron} from 'react-bootstrap';


export default class Registration extends React.Component {
    render() {
        return (
            <Jumbotron className="bg-white text-dark">
                <h1> Registration on the QA portal</h1>
                <blockquote className="blockquote mb-0">
                    <Form.Row>
                        <Form.Group as={Col} controlId="formGrid">
                            <Form.Label>Email</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="text"
                                          className={"bg-white text-dark"}
                                          placeholder="Enter Email"/>
                        </Form.Group>
                    </Form.Row>

                    <Form.Row>
                        <Form.Group as={Col} controlId="formGrid">
                            <Form.Label>Password</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="text"
                                          className={"bg-white text-dark"}
                                          placeholder="Enter Password"/>
                        </Form.Group>
                    </Form.Row>

                    <Form.Row>
                        <Form.Group as={Col} controlId="formGrid">
                            <Form.Label>Сonfirm Password</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="text"
                                          className={"bg-white text-dark"}
                                          placeholder="Enter Password"/>
                        </Form.Group>
                    </Form.Row>

                    <Form.Row>
                        <Form.Group as={Col} controlId="formGrid">
                            <Form.Label>First name</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="text"
                                          className={"bg-white text-dark"}
                                          placeholder="Enter first name"/>
                        </Form.Group>
                    </Form.Row>


                    <Form.Row>
                        <Form.Group as={Col} controlId="formGrid">
                            <Form.Label>Phone number</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="text"
                                          className={"bg-white text-dark"}
                                          placeholder="Enter phone number"/>
                        </Form.Group>
                    </Form.Row>

                    <Button size="sm" variant="info" type="button">
                        Login
                    </Button> {' '}

                    <Button size="sm" variant="info" type="button" >
                        Registration
                    </Button>
                </blockquote>
            </Jumbotron>
        )
    }
}