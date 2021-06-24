import React, {Component} from 'react';

import {Card, Form, Button, Col} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSave, faPlusSquare, faBan} from '@fortawesome/free-solid-svg-icons';

export default class Field extends Component {


    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.fieldChange = this.fieldChange.bind(this);
        this.submitField = this.submitField.bind(this);
        console.log(localStorage.getItem("fieldId"))
    }

    initialState = {
        label: '',
        type: '',
        required: '',
        active: '',
        optionList: ''
    }

    submitField(event) {
        alert('Label: ' + this.state.label + ', Type: ' + this.state.type + ', Required: ' + this.state.required + ", Active: " + this.state.active +
            ', Option:' + this.state.optionList);
        event.preventDefault();
    }

    fieldChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {

        const {label, type, required, active, optionList} = this.state;

        return (
            <Card className={"border border-dark bg-white text-dark"}>
                <Card.Header>
                    <FontAwesomeIcon icon={faPlusSquare}/> Add New Field
                </Card.Header>
                <Form onSubmit={this.submitField} id="fieldFormId">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGrid">
                                <Form.Label>Label</Form.Label>
                                <Form.Control required
                                              type="text"
                                              name="label"
                                              value={label}
                                              onChange={this.fieldChange}
                                              className={"bg-white text-dark"}
                                              placeholder="Enter Field Label"/>
                            </Form.Group>
                        </Form.Row>

                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridTitle">
                                <Form.Label>Type</Form.Label>
                                <Form.Control as={'select'}
                                              name="type"
                                              value={type}
                                              onChange={this.fieldChange}
                                              required
                                              className={"bg-white text-dark"}>
                                    <option></option>
                                    <option value="single line text">single line text</option>
                                    <option value="checkbox">checkbox</option>
                                    <option value="combobox">combobox</option>
                                    <option value="date">date</option>
                                    <option value="radio button">radio button</option>
                                    <option value="multiline text"> multiline text</option>
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} className="mb-3" controlId="exampleForm.ControlTextarea1">
                                <Form.Label>Options</Form.Label>
                                <Form.Control
                                    value={optionList}
                                    name="optionList"
                                    onChange={this.fieldChange}
                                    as="textarea" rows={3}/>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group controlId="required">
                                <Form.Check type="checkbox" label="Required"
                                            value={required}
                                            name="required"
                                            onChange={this.fieldChange}
                                />
                            </Form.Group>
                        </Form.Row>

                        <Form.Row>
                            <Form.Group controlId="active">
                                <Form.Check type="checkbox" label="Is Active"
                                            value={active}
                                            name="active"
                                            onChange={this.fieldChange}
                                />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer style={{"textAlign": "right"}}>
                        <Button size="sm" variant="success" type="submit">
                            <FontAwesomeIcon icon={faSave}/> Submit
                        </Button>

                        <Button size="sm" variant="fail" type="submit">
                            <FontAwesomeIcon icon={faBan}/> Cancel
                        </Button>
                    </Card.Footer>
                </Form>
            </Card>
        );
    }
}
