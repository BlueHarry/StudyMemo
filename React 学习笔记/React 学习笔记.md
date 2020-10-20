# React 学习笔记

3大WEB前端开发框架，React、Angular 和 Vue，React被认为是学习曲线最平缓，最稳定，最为普遍使用的框架。Angular.js 2016年改版成Angular.js 2.0，框架做了彻底的改变。Angular.js 2.0现在省去后缀.js，叫做 Angular了。这个陡坡式升级，开发者抱怨很大。Vue.js虽然发展势头很好，但是在美国的使用普遍性远远不如React。



Https://picsum/photos ，这个图片网站好用。





## 环境安装：

1. 到Nodejs.io网站下载 Nodejs包，为了能够使用 npm 包管理工具。
2. 在命令行用 npm 安装 create-react-app 命令行工具。

```sh
npm i -g create-react-app
```

3. 安装 VS Code，到官方网站下载
4. 安装一些插件：prettier- code formatter，Simple React Snippets
5. 安装 git ，这个比较麻烦



## getbootstrap.com

一个提供前端开发模版的网站



## component 类

REACT 框架的核心是 Component 类。所有的构成 App UI 的 ReactDOM 组件都是继承这个类。

```react
import React, { Component } from 'react';

class Jcomponent1 extends Component {
    state = {  }
    render() { 
        return (  );
    }
}
 
export default Jcomponent1;
```

- render( ) 方法不断侦测 state 的变化，在毫秒时间级别异步刷新 ReactDOM 的模样。
- 在创建一个component 对象实例时，父组件可以设置子组件的 Props 值。这是父子传递信息的重要途径。



## event handling

事件的触发和处理函数方法，这是React编程的主要工作。

事件处理函数方法在父组件中定义，父组件创建子组件对象时，设定这个组件对象的函数参考指针（Method reference），作为这个组件对象的 Props属性之一。

```react
class Jcomponent1 extends Component {

    state = {  }

    onClickHandle() {
        // do somthing here after onClick Event
    }

    render() { 
        
      // 以下语句Jcomponent1父组件创建一个Button子组件
      // 设置一个事件处理参考指针Props属性，就是this.onClickHandle，指向在父组件中定义的一个方法函数  onClickHandle
        <button onClick={this.onClickHandle}></button> 
        
    }
}
```

