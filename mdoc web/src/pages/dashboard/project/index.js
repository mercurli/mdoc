import React from 'react'
import { Card } from 'antd'

export default class Dashboard extends React.Component {

    render() {
        return (
            <div>
                <p>项目列表</p>
                <div>
                    <Card style={{ width: 300 }}>
                        <p>项目1</p>
                    </Card>
                </div>
            </div>
        )
    }
}