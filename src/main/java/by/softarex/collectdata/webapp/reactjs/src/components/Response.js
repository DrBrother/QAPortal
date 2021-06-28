import React, {Component} from 'react';

import {Card, Table} from 'react-bootstrap';
import {faList} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Link} from 'react-router-dom';


export default class Response extends Component {


    render() {
        console.log(this.state)
        return (
            <Card className={"border border-dark bg-white text-dark"}>
                <Card.Header>
                    <FontAwesomeIcon icon={faList}/>
                    {' '}Responses <div align="right">
                    <Link to={"/"} align="right">Make Answer</Link>
                </div>
                </Card.Header>
                <Card.Body>
                    <Table border hover striped variant="bright">
                        <tbody>
                        {<tr align="center">
                            <td colSpan="6">0 responses</td>
                        </tr>}
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

