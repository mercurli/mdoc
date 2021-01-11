import React from 'react'
import { HashRouter, Switch, Route } from 'react-router-dom'
import Login from '../pages/login'
import Layout from '../components/Layout'
import Dashboard from '../pages/dashboard'
import Project from '../pages/dashboard/project'
import Role from '../pages/system/role'
import Department from '../pages/system/department'
import User from '../pages/system/user'
import Menu from '../pages/system/menu'

export default class Router extends React.Component {

    render() {
        return (
            <HashRouter>
                <Switch>
                    <Route path="/login" component={Login} />
                    <Route path="/" render={() =>
                        <Layout>
                            <Switch>
                                <Route path="/dashboard" component={Dashboard} />
                                <Route path="/dashboard/project" component={Project} />
            
                                <Route path="/system/role" component={Role} />
                                <Route path="/system/department" component={Department} />
                                <Route path="/system/user" component={User} />
                                <Route path="/system/menu" component={Menu} />
                            </Switch>
                        </Layout>
                    } />
                </Switch>
            </HashRouter>
        )
    }
}