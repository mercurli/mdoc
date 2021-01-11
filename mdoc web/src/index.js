import React from 'react';
import ReactDOM from 'react-dom';
import { ConfigProvider } from 'antd';
import zhCN from 'antd/es/locale/zh_CN';
import Router from './router';
import * as serviceWorker from './serviceWorker';
import './themes/index.less';

ReactDOM.render(
    <ConfigProvider locale={zhCN}>
        <Router/>
    </ConfigProvider>
    , document.getElementById('root')
);

serviceWorker.unregister();
