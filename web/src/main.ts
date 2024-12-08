import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import axios from 'axios';
import { message } from 'ant-design-vue';

axios.defaults.baseURL = process.env.VUE_APP_SERVER;

/**
 * Axios Interceptors
 */
axios.interceptors.request.use(function (config) {
    console.log('Request parameters:', config);
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log('Response result:', response);
    return response;
}, error => {
    console.log('Response error:', error);
    return Promise.reject(error);
});


const app = createApp(App);
app.use(store).use(router).use(Antd).mount('#app');


console.log('env：', process.env.NODE_ENV);
console.log('server：', process.env.VUE_APP_SERVER);