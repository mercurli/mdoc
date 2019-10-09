import axios from 'axios'
import { Modal } from 'antd'

export default class Http {

    static get(url, data) {
        return new Promise((resolve, reject) => {
            axios({
                url: url,
                method: 'get',
                params: data
            }).then((response) => {
                if (response.status === 200) {
                    let result = response.data;
                    if (result.success) {
                        resolve(result.data)
                    } else {
                        Modal.info({
                            title: '提示',
                            content: result.message
                        });
                    }
                } else {
                    reject(response.data);
                }
            })
        })
    }

    static post(url, data) {
        return new Promise((resolve, reject) => {
            axios({
                url: url,
                method: 'post',
                params: data
            }).then((response) => {
                if (response.status === 200) {
                    let result = response.data;
                    if (result.success) {
                        resolve(result.data)
                    } else {
                        Modal.info({
                            title: '提示',
                            content: result.message
                        });
                    }
                } else {
                    reject(response.data);
                }
            })
        })
    }
}