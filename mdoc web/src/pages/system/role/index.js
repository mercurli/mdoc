import React from 'react'
import { Card, Table, Form, Input, Button, Modal, Select, Tag, Popconfirm, Dropdown, Icon, Menu, Tree } from 'antd'
import Http from '../../../util/http';

const statusDict = [
    {
        text: '启用',
        value: 1
    },
    {
        text: '停用',
        value: 0
    }
]

export default class Role extends React.Component {

    state = {
        data: [],
        visible: false,
        title: '',
        row: {},
        permissionVisible: false
    }

    componentWillMount() {
        this.getRoles()
    }

    getRoles() {
        Http.get('/role/getList').then((data) => {
            this.setState({data: data})
        })
    }

    showMoadlAdd = () => {
        this.setState({
            visible: true,
            title: '新增角色'
        })
    }

    showMoadlEdit = (row) => {
        this.setState({
            visible: true,
            title: '编辑角色',
            row: row
        })
    }

    handleOk = () => {
        this.roleForm.props.form.validateFields((err, data) => {
            if (!err) {
                Http.get('/role/add', data);
                this.setState({visible: false});
                this.getRoles()
            }
        })
    }

    handleDelete = (id) => {
        Http.get('/role/remove',{
            id: id
        }).then((data) => {
            this.getRoles()
        })
    }

    render() {
        const menu = (
            <Menu>
                <Menu.Item>所有</Menu.Item>
                {statusDict.map(item =>
                    <Menu.Item key={item.value}>{item.text}</Menu.Item>
                )}
            </Menu>
        )
        const colums = [
            {
                title: '角色名',
                dataIndex: 'name',
                width: '40%'
            },
            {
                title: () => (
                    <Dropdown overlay={menu}>
                        <span>状态<Icon type="down"/></span>
                    </Dropdown>
                ),
                dataIndex: 'status',
                render: (value) => {
                    let s = '';
                    statusDict.forEach(item => {
                        if (item.value === value) {
                            s = item.text;
                            return
                        }
                    })
                    return <Tag>{s}</Tag>
                }
            },
            {
                title: '',
                render: (v, item) => (
                    <div className="mer-table-row-tool">
                        <span className="mer-btn-text" onClick={() => {this.setState({permissionVisible: true})}}>角色授权</span>
                        <span className="mer-btn-text" onClick={() => {this.showMoadlEdit(item)}}>编辑</span>
                        <Popconfirm 
                            title="确认删除该角色？" 
                            onConfirm={() => {this.handleDelete(item.id)}} 
                            placement="right"
                        >
                            <span className="mer-btn-text">删除</span>
                        </Popconfirm>
                    </div>
                )
            }
        ]

        return (
            <div className="mer-content">
                <Card title="角色管理" extra={<Button type="primary" onClick={this.showMoadlAdd}>新增</Button>}>
                    <Table 
                        columns={colums} 
                        dataSource={this.state.data} 
                        rowKey="id" 
                        pagination={false} 
                        size="middle" 
                    />
                </Card>
                <Modal 
                    width="600px" 
                    title={this.state.title} 
                    visible={this.state.visible} 
                    onOk={this.handleOk} 
                    onCancel={() => {this.setState({visible: false})}}
                >
                    <RoleForm 
                        wrappedComponentRef={inst => this.roleForm = inst} 
                        row={this.state.row} 
                        resetFlag={this.state.resetFlag}
                    />
                </Modal>
                <Modal 
                    title="权限配置" 
                    visible={this.state.permissionVisible} 
                    onCancel={() => {this.setState({permissionVisible: false})}}
                >
                    <MenuTree />
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

        return (
            <Form {...formItemLayout}>
                <Form.Item label="角色名">
                    {getFieldDecorator('name', {
                        rules: [{required: true, message: "请输入角色名"}],
                        validateTrigger: 'onBlur'
                    })(
                        <Input />
                    )}
                </Form.Item>
                <Form.Item label="角色状态">
                    {getFieldDecorator('status', {
                        initialValue: 1,
                    })(
                        <Select>
                            {statusDict.map(item => 
                                <Select.Option key={item.value} value={item.value}>{item.text}</Select.Option>
                            )}
                        </Select>
                    )}
                </Form.Item>
            </Form>
        )
    }
}
RoleForm = Form.create({})(RoleForm)

class MenuTree extends React.Component {

    renderTreeNodes = data =>
        data.map(item => {
          if (item.children) {
            return (
              <Tree.TreeNode title={item.title} key={item.key} dataRef={item}>
                {this.renderTreeNodes(item.children)}
              </Tree.TreeNode>
            );
        }
        return <Tree.TreeNode key={item.key} {...item} />;
    })

    render() {
        const treeData = [
            {
                title: '工作台',
                key: '1',
                children: [
                {
                    title: '项目',
                    key: '11'
                },
                {
                    title: '知识库',
                    key: '12'
                },
                {
                    title: '其他',
                    key: '13',
                },
                ],
            },
            {
                title: '系统',
                key: '2',
                children: [
                    { title: '角色管理', key: '21' },
                    { title: '部门管理', key: '22' },
                    { title: '用户管理', key: '23' },
                    { title: '菜单管理', key: '24' }
                ],
            }
        ]
        
        return (
            <Tree checkable>
                {this.renderTreeNodes(treeData)}
            </Tree>
        )
    }
}