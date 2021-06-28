import React from 'react';
import { Form, Button, Col} from 'react-bootstrap';

import {Jumbotron} from 'react-bootstrap';

export default class Registration extends React.Component {
    render() {
        return (
            <Jumbotron className="bg-white text-dark">
                <h1> Welcome to the QA portal</h1>
                <blockquote className="blockquote mb-0">
                    <Form.Row>
                        <Form.Group as={Col} controlId="formGrid">
                            <Form.Label>Login</Form.Label>
                            <Form.Control required autoComplete="off"
                                          type="text"
                                          className={"bg-white text-dark"}
                                          placeholder="Enter Login"/>
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