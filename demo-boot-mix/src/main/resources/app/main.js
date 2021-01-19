//import  users from './user' ;

// const greeter = require('./hello.js');
//
//
// document.querySelector("#root").appendChild(greeter());


import React from 'react';
import {render} from 'react-dom';
import Greeter from './hello';

import {users} from './user';
import {user} from './index';


import './main.css';//使用require导入css文件
console.log(...users);
user.getInfo(users);
user.showInfo(users);
render(<Greeter nn={users[1]}/>, document.getElementById('root'));
