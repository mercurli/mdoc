import React from 'react'
import {Link} from 'react-router-dom'
import { Menu, Popover , Avatar, Icon } from 'antd'
import './Header.less'

export default class Header extends React.Component {

    render() {
        const content = (
            <div class="mer-user-wrapper">
                <p>Mer</p>
                <Menu style={{width: 250}}>
                    <Menu.Divider/>
                    <Menu.Item><Icon type="user" />个人设置</Menu.Item>
                    <Menu.Item><Icon type="logout" />退出</Menu.Item>
                </Menu>
            </div>
        )
        return(
            <header className="mer-header">
                <div className="mer-header-container">
                    <div className="mer-header-logo"><Link to="/">MDoc</Link></div>
                    <div className="mer-header-nav">
                        <Menu mode="horizontal" className="mer-header-menu">
                            <Menu.Item><Link to="/dashboard">工作台</Link></Menu.Item>
                            <Menu.Item><Link to="/system">系统</Link></Menu.Item>
                        </Menu>
                    </div>
                    <div className="mer-header-bar">
                        <div className="mer-header-notify mer-header-bar-item">
                            <Icon type="bell" />
                        </div>
                        <Popover placement="bottomRight" content={content}>
                            <Avatar size="small" icon="user" />
                        </Popover>
                    </div>
                </div>
            </header>
        )
    }
}