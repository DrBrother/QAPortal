import React, {Component} from 'react';

import {Card, Table} from 'react-bootstrap';

export default class FieldList extends Component {
    render() {
        return (
            <Card className={"border border-dark bg-white text-dark"}>
                <Card.Header>Fields</Card.Header>
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
                        <tr align="center">
                            <td colSpan="4">No fields</td>
                        </tr>
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

