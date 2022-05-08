import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Editor from 'vue2-ace-editor';


createApp(App)

    .use(store)
    .use(router)
    .use(Editor)
    .use(ElementPlus)
    .use(VueAxios, axios)
    .mount('#app')

