import React from 'react'
import { Card, Table, Button, Modal, Form, Input, Radio, Select } from 'antd'

export default class User extends React.Component {

    state = {
        visible: false
    }

    showMoadl = () => {
        this.setState({visible: true})
    }

    handleOk = e => {
        
        this.setState({visible: false})
    }

    handleCancel = () => {
        this.setState({visible: false})
    }

    render() {
        const colums = [
            {
                title: '用户名',
                dataIndex: 'username',
                key: 'username'
            },
            {
                title: '姓名',
                dataIndex: 'name',
                key: 'name'
            },
            {
                title: '角色',
                dataIndex: 'role',
                key: 'role'
            },
            {
                title: '状态',
                dataIndex: 'state',
                key: 'state'
            }
        ]
        
        const data = [
            {
                key: 1,
                username: 'admin',
                name: '管理员',
                role: '管理员',
                state: '正常'
            },
            {
                key: 2,
                username: 'zs',
                name: '张三',
                role: '用户',
                state: '禁用'
            }
        ]

        return (
            <div className="mer-content">
                <Card title="用户管理" extra={<Button type="primary" onClick={this.showMoadl}>新增</Button>}>
                    <Table columns={colums} dataSource={data}/>
                </Card>
                <Modal width="600px" title="新增用户" visible={this.state.visible} onOk={this.handleOk} onCancel={this.handleCancel}>
                    <UserForm />
                </Modal>
            </div>
        )
    }
}

class UserForm extends React.Component {

    render() {
        const { getFieldDecorator } = this.props.form;
        const formItemLayout = {
            labelCol: {
                span: 4
            },
            wrapperCol: {
                span: 20
            }
        }
        const { Option } = Select;
        return (
            <Form {...formItemLayout}>
                <Form.Item label="用户名">
                    {getFieldDecorator('username', {
                        rules: [{required: true, message: "请输入用户名"}],
                        validateTrigger: 'onBlur'
                    })(
                        <Input />
                    )}
                </Form.Item>
                <Form.Item label="用户密码">
                    {getFieldDecorator('password', {
                        rules: [
                            {required: true, message: "请输入密码"},
                            {min: 6, message: "密码长度不能少于 6 位"}
                        ],
                        validateTrigger: 'onBlur',
                        initialValue: '123456'
                    })(
                        <Input.Password />
                    )}
                </Form.Item>
                <Form.Item label="姓名">
                    {getFieldDecorator('name', {
                        rules: [
                            {required: true, message: "请输入姓名"}
                        ],
                        validateTrigger: 'onBlur'
                    })(
                        <Input/>
                    )}
                </Form.Item>
                <Form.Item label="性别">
                    {getFieldDecorator('sex', {
                        initialValue: 1
                    })(
                        <Radio.Group name="sex">
                            <Radio value={1}>男</Radio>
                            <Radio value={2}>女</Radio>
                        </Radio.Group>
                    )}
                </Form.Item>
                <Form.Item label="角色">
                    {getFieldDecorator('role', {
                        rules: [
                            {required: true, message: "请选择角色"}
                        ],
                        validateTrigger: 'onBlur'
                    })(
                        <Select>
                            <Option value="admin">管理员</Option>
                            <Option value="user">用户</Option>
                        </Select>
                    )}
                </Form.Item>
            </Form>
        )
    }
}

UserForm = Form.create({})(UserForm)