# Angular 学习笔记



## Angular开发环境安装

1.  下载安装 Nodejs。到官网：https://nodejs.org 下载安装Node.js稳定版本。我们主要是想用 NPM（Nodejs Package Manager）这个软件包管理工具，来安装 AnguarCLI 命令行工具。
2. 在终端命令行，用NPM来安装 AngularCLI 工具。

 ```shell
zsh.$ sudo npm install -g @angular/cli
Password:
 ```

	3. 安装Typescript

```shell
zsh.$ sudo npm install -g typescript
Password:

zsh.$ tsc --version
Version 4.0.3
```





## 创建一个Angular新项目

1. 用 ng 创建一个Angular新项目

```shell
zsh.$ ng new Hello-World
```

2. 启动一个本地 Web 服务器

```shell
zsh.$ ng serve

** Angular Live Development Server is listening on localhost:4200, open your browser on http://localhost:4200/ **
```

