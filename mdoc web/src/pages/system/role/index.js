import React from 'react'
import { Card, Table, Form, Input, Button, Modal, Select } from 'antd'
import Http from '../../../util/http'
import Util from '../../../util/util'

export default class Role extends React.Component {

    state = {
        data: [],
        visible: false
    }

    componentDidMount() {
        Http.get('/role/getRoles').then((data) => {
            this.setState({data: data})
        })
    }

    showMoadl = () => {
        this.setState({visible: true});
        console.log(this.roleForm);
        //this.roleForm.props.form.resetFields()
    }

    handleOk = e => {
        this.roleForm.props.form.validateFields((err, data) => {
            if (!err) {
                Http.get('/role/add', data);
                this.setState({visible: false})
            }
        })
    }

    handleCancel = () => {
        this.setState({visible: false})
    }

    render() {
        const colums = [
            {
                title: '角色名称',
                dataIndex: 'name',
                width: '30%'
            },
            {
                title: '状态',
                dataIndex: 'status'
            },
            {
                title: '创建时间',
                dataIndex: 'createTime',
                render: (value) => Util.formatDate(value)
            },
            {
                title: '',
                render: () => (
                    <div className="mer-table-row-tool">
                        <span className="mer-btn-text">角色授权</span>
                        <span className="mer-btn-text">编辑</span>
                        <span className="mer-btn-text">删除</span>
                    </div>
                )
            }
        ]

        return (
            <div className="mer-content">
                <Card title="角色管理" extra={<Button type="primary" onClick={this.showMoadl}>新增</Button>}>
                    <Table columns={colums} dataSource={this.state.data} rowKey="id" pagination={false} size="middle" />
                </Card>
                <Modal width="600px" title="新增角色" okText="确定" cancelText="取消" visible={this.state.visible} onOk={this.handleOk} onCancel={this.handleCancel}>
                    <RoleForm wrappedComponentRef={(inst) => this.roleForm = inst }/>
                </Modal>
            </div>
        )
    }
}

class RoleForm extends React.Component {

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
                <Form.Item label="角色名称">
                    {getFieldDecorator('name', {
                        rules: [{required: true, message: "请输入角色名称"}],
                        validateTrigger: 'onBlur'
                    })(
                        <Input />
                    )}
                </Form.Item>
                <Form.Item label="状态">
                    {getFieldDecorator('status', {
                        initialValue: 1,
                    })(
                        <Select>
                            <Option value={1}>启用</Option>
                            <Option value={0}>禁用</Option>
                        </Select>
                    )}
                </Form.Item>
            </Form>
        )
    }
}
RoleForm = Form.create({})(RoleForm)