import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './serviceWorker';
import Router from './router';
import './themes/index.less';

ReactDOM.render(
    <div>
        <Router/>
    </div>
    , document.getElementById('root')
);

serviceWorker.unregister();
