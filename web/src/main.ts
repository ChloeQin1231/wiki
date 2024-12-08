import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import axios from 'axios';

axios.defaults.baseURL = process.env.VUE_APP_SERVER;
const app = createApp(App);
app.use(store).use(router).use(Antd).mount('#app');


console.log('env：', process.env.NODE_ENV);
console.log('server：', process.env.VUE_APP_SERVER);