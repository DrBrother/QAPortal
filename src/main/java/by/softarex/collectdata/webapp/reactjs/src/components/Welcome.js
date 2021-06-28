import React from 'react';

import {Jumbotron} from 'react-bootstrap';

export default class Welcome extends React.Component {
    render() {
        return (
            <Jumbotron className="bg-white text-dark">
                <h1> Welcome to the QA portal</h1>
                <blockquote className="blockquote mb-0">
                    <p>
                        Не нравится ответ — не задавай вопрос.
                    </p>
                    <footer className="blockquote-footer">
                        House M.D.
                    </footer>

                </blockquote>
            </Jumbotron>
        )
    }
}