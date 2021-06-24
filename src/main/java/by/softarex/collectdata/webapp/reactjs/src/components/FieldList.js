import React, {Component} from 'react';

import {Card, Table} from 'react-bootstrap';
import {faList} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import axios from 'axios';


export default class FieldList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            fields: []
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/fields")
            .then(response => response.data)
            .then((data) => {
                this.setState({fields: data});
            });
    }

    render() {
        return (
            <Card className={"border border-dark bg-white text-dark"}>
                <Card.Header>
                    <FontAwesomeIcon icon={faList}/> Fields</Card.Header>
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

