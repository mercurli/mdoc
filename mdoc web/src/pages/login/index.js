import React from 'react'
import {Input, Form, Button, Icon} from 'antd'
import './index.less'

class Login extends React.Component {

    handleSubmit = () => {
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log(values);
                this.props.history.push('/dashboard')
            }
        })
    }

    componentDidMount() {
        document.addEventListener("keydown", (e) => {
            if (e.keyCode === 13) {
                this.handleSubmit()
            }
        })
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <div className="mer-login-wrapper">
                <div className="mer-form-container">
                    <h1>MDoc</h1>
                    <Form>
                        <Form.Item>
                            {getFieldDecorator('username', {
                                rules: [{required: true, message: "请输入用户名"}],
                                validateTrigger: 'onBlur'
                            })(
                                <Input prefix={<Icon type="user"/>} size="large" placeholder="用户名" />
                            )}
                        </Form.Item>
                        <Form.Item>
                            {getFieldDecorator('password', {
                                rules: [
                                    {required: true, message: "请输入密码"},
                                    {min: 6, message: "请输入正确密码格式，至少 6 位"}
                                ],
                                validateTrigger: 'onBlur'
                            })(
                                <Input.Password prefix={<Icon type="lock"/>} size="large" placeholder="密码" />
                            )}
                        </Form.Item>
                        <Button type="primary" size="large" block onClick={this.handleSubmit}>登录</Button>
                    </Form>
                </div>
            </div>
        )
    }
}

export default Form.create({name: 'loginForm'})(Login)