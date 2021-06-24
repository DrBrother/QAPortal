import React from 'react';

import {Navbar, Container, Col} from 'react-bootstrap';

class Footer extends React.Component {
    render() {
        let fullYear = new Date().getFullYear();

        return (
            <Navbar fixed="bottom" bd="dark" variant="dark">
                <Container>
                    <Col ld={12} className="text-center text-muted">
                        <div>
                            {fullYear},
                            Designed by Shadrin
                        </div>
                    </Col>
                </Container>
            </Navbar>
        );
    }
}

export default Footer;