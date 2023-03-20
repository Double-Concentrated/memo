import axios from "axios"
// import Element from 'element-ui'
// import router from './router'
// import store from './store'

axios.defaults.baseURL="/api"
// axios.defaults.baseURL="http://localhost:8082/api"

// // 前置拦截
// axios.interceptors.request.use(config => {
//     console.log("=====前置拦截=====")
//     // 可以统一设置请求头
//     return config
// })
//
// // 后置拦截
// axios.interceptors.response.use(response => {
//         let res = response.data;
//         console.log("=====后置拦截=====");
//
//         if(res.code === 200){
//             return response
//         }else {
//             // 弹窗异常信息
//             Element.Message({
//                 message: response.data.msg,
//                 type: 'error',
//                 duration: 2 * 1000
//             })
//
//             return Promise.reject(response.data.msg)
//         }
//     },
//     error => {
//         console.log('err' + error)// for debug
//         if(error.response.data){
//             error.message = error.response.data.msg
//         }
//         // 根据请求状态决定是否登录或者提示其他
//         if (error.response.status === 401) {
//             store.commit('REMOVE_INFO');
//             router.push({
//                 path: '/login'
//             });
//             error.message = '请重新登录';
//         }
//         if (error.response.status === 403) {
//             error.message = '权限不足，无法访问';
//         }
//         Element.Message({
//             message: error.message,
//             type: 'error',
//             duration: 3 * 1000
//         })
//         return Promise.reject(error)
//     }
// )