import React, {Component} from 'react';

import {Card, Form, Button, Col} from 'react-bootstrap';
import axios from "axios";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSave, faPlusSquare, faList, faEdit} from '@fortawesome/free-solid-svg-icons';


export default class Field extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.fieldChange = this.fieldChange.bind(this);
        this.submitField = this.submitField.bind(this);
    }

    initialState = {
        id: '',
        label: '',
        type: '',
        required: false,
        active: false,
        optionList: ''
    }

    componentDidMount() {
        const fieldId = +this.props.match.params.id;
        if (fieldId) {
            this.findFieldById(fieldId)
        }
    }

    submitField = event => {

        event.preventDefault();

        const field = {
            label: this.state.label,
            type: this.state.type,
            required: this.state.required,
            active: this.state.active,
            optionList: this.state.optionList
        }

        axios.post("http://localhost:8080/fields", field)
            .then(response => {
                alert("Field was saved ");
                if (response.data != null) {
                    this.setState(this.initialState);
                }
            });
    }


    findFieldById = (fieldId) => {
        axios.get('http://localhost:8080/fields/' + fieldId)
            .then(response => {

                if (response.data != null) {
                    this.setState({
                        id: response.data.id,
                        label: response.data.label,
                        type: response.data.type,
                        required: response.data.required,
                        optionList: response.data.optionList
                    })
                }
            }).catch((error) => {
            console.error("Error - " + error)
        })
    }

    resetField = () => {
        this.setState(() => this.initialState)
    }

    fieldChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    fieldList = () => {
        return this.props.history.push("/fields")
    }

    updateField = event => {

        event.preventDefault();

        const field = {
            id: this.state.id,
            label: this.state.label,
            type: this.state.type,
            required: this.state.required,
            active: this.state.active,
            optionList: this.state.optionList
        }

        axios.put("http://localhost:8080/fields/" + this.state.id, field)
            .then(response => {
                alert("Field was saved ");
                if (response.data != null) {
                    this.setState(this.initialState);
                }
            });
        this.props.history.push("/fields")
        window.location.reload()
    }

    render() {

        const {label, required, active, type, optionList} = this.state;


        return (
            <Card className={"border border-dark bg-white text-dark"}>
                <Card.Header>
                    <FontAwesomeIcon
                        icon={this.state.id ? faEdit : faPlusSquare}/>{this.state.id ? "Update Field" : "Add New Field"}
                </Card.Header>
                <Form onReset={this.resetField}
                      onSubmit={this.state.id ? this.updateField : this.submitField}
                      id="fieldFormId">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGrid">
                                <Form.Label>Label</Form.Label>
                                <Form.Control required autoComplete="off"
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
                                            onClick={() => {
                                                this.setState({"required": true})
                                            }}
                                            name="required"
                                    // onChange={this.fieldChange}
                                />
                            </Form.Group>
                        </Form.Row>

                        <Form.Row>
                            <Form.Group controlId="active">
                                <Form.Check type="checkbox" label="Is Active"
                                            value={active}
                                            onClick={() => {
                                                this.setState({"active": true})
                                            }}
                                            name="active"
                                    // onChange={this.fieldChange}
                                />
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer style={{"textAlign": "right"}}>
                        <Button size="sm" variant="success" type="submit">
                            <FontAwesomeIcon icon={faSave}/> {this.state.id ? "Update" : "Save"}
                        </Button> {' '}
                        <Button size="sm" variant="info" type="button" onClick={this.fieldList.bind()}>
                            <FontAwesomeIcon icon={faList}/> Field list
                        </Button>
                    </Card.Footer>
                </Form>
            </Card>
        );
    }
}
