import React, {Component} from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import {faEdit, faList, faTrash} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import axios from 'axios';
import {Link} from 'react-router-dom';


export default class FieldList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            fields: []
        };
    }

    componentDidMount() {
        this.findAllFields();
    }

    findAllFields() {
        axios.get("http://localhost:8080/fields")
            .then(response => response.data)
            .then((data) => {
                this.setState({fields: data});
            });
    }

    deleteField = (fieldId) => {
        axios.delete("http://localhost:8080/fields/" + fieldId)
            .then(response => {
                if (response.data != null) {
                    alert("Field deleted");
                    this.setState({
                        fields: this.state.fields.filter(field => field.id !== fieldId)
                    });
                }
            });
    };

    render() {
        return (
            <Card className={"border border-dark bg-white text-dark"}>
                <Card.Header>
                    <FontAwesomeIcon icon={faList}/>
                    {' '}Fields <div align="right">
                    <Link to={"field"} align="right">Create new field </Link>
                </div>
                </Card.Header>
                <Card.Body>
                    <Table border hover striped variant="bright">
                        <thead>
                        <tr>
                            <th>Label</th>
                            <th>Type</th>
                            <th>Required</th>
                            <th>Is Active</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.fields.length === 0 ?
                                <tr align="center">
                                    <td colSpan="6">{this.state.fields.length} fields</td>
                                </tr> :
                                this.state.fields.map((field) => (
                                    <tr key={field.id}>
                                        <td>{field.label}</td>
                                        <td>{field.type}</td>
                                        <td>{field.required ? 'true' : 'false'}</td>
                                        <td>{field.active ? 'true' : 'false'}</td>
                                        <td>
                                            <ButtonGroup>
                                                <Link to={"fields/" + field.id}
                                                      className="btn btn-sm btn-outline-primary"><FontAwesomeIcon
                                                    icon={faEdit}/></Link> {' '}

                                                <Button size="sm" variant="outline-danger"
                                                        onClick={this.deleteField.bind(this, field.id)}><FontAwesomeIcon
                                                    icon={faTrash}/> </Button>
                                            </ButtonGroup>
                                        </td>
                                    </tr>
                                ))
                        }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

