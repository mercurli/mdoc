import React from 'react'
import { Card, Form, Input, Button, Modal } from 'antd'

export default class Department extends React.Component {

    state = {
        visible: false
    }

    showMoadl = () => {
        this.setState({visible: true})
    }

    handleOk = e => {
        console.log(this.roleForm);
        this.roleForm.props.form.validateFields((err, data) => {
            if (!err) {
                //
                this.setState({visible: false})
            }
        })
    }

    handleCancel = () => {
        this.setState({visible: false})
    }

    render() {
        
        return (
            <div className="mer-content">
                <Card title="新增部门" extra={<Button type="primary" onClick={this.showMoadl}>新增</Button>}>
                    
                </Card>
                <Modal width="600px" title="新增部门" okText="确定" cancelText="取消" visible={this.state.visible} onOk={this.handleOk} onCancel={this.handleCancel}>
                    <DepartmentForm wrappedComponentRef={(inst) => this.departmentForm = inst }/>
                </Modal>
            </div>
        )
    }
}

class DepartmentForm extends React.Component {

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
                <Form.Item label="名称">
                    {getFieldDecorator('name', {
                        rules: [{required: true, message: "请输入角色名称"}],
                        validateTrigger: 'onBlur'
                    })(
                        <Input />
                    )}
                </Form.Item>
                <Form.Item label="上级">
                    {getFieldDecorator('partId', {
                        initialValue: 1,
                    })(
                        <Input />
                    )}
                </Form.Item>
            </Form>
        )
    }
}

DepartmentForm = Form.create({})(DepartmentForm)