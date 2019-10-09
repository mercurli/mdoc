import React from 'react'
import Header from './Header'
import Nav from './Nav'
import './index.less'

export default class Layout extends React.Component {

    render() {
        return (
            <div className="mer-wrapper">
                <Header></Header>
                <div className="mer-container">
                    <Nav></Nav>
                    <main className="mer-main">{this.props.children}</main>
                </div>
            </div>
        )
    }
}