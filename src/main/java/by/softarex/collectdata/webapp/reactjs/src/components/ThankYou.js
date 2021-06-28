import React from 'react';

import {Jumbotron} from 'react-bootstrap';

export default class ThankYou extends React.Component {
    render() {
        return (
            <Jumbotron className="bg-white text-dark">
                <h1> Thank you! Your response was saved!</h1>
            </Jumbotron>
        )
    }
}