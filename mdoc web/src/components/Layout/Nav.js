import React from 'react'
import {Link} from 'react-router-dom'
import { Menu, Icon } from 'antd'
import './Nav.less'


const menuData = [
    {
        title: '项目',
        icon: 'appstore',
        path: '/dashboard/project'
    },
    {
        title: '角色管理',
        icon: 'file-text',
        path: '/system/role'
    },
    {
        title: '部门管理',
        icon: 'file-text',
        path: '/system/department'
    },
    {
        title: '用户管理',
        icon: 'user',
        path: '/system/user'
    },
    {
        title: '菜单管理',
        icon: 'user',
        path: '/system/menu'
    }
]

export default class Nav extends React.Component {

    render() {
        return(
            <nav className="mer-nav">
                <Menu>
                    {
                        menuData.map((item) => {
                            return <Menu.Item key={item.path}><Link to={item.path}><Icon type={item.icon}/>{item.title}</Link></Menu.Item>
                        })
                    }
                </Menu>
            </nav>
        )
    }
}