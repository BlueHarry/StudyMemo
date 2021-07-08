# Django学习笔记



## Python虚拟环境安装

参照《Python学习笔记》相关内容，本文档这部分内容已经停止维护了，仅作为简单参考：

1. 下载安装虚拟环境工具。因为版本的匹配需要，为每个python项目配置一个特定的虚拟环境是第一步。

   ```shell
   $ pip3 install virtualenv
   
   # 以上命令通过python的安装包工具 pip3来下载和安装虚拟环境工具 virtualenv
   ```

2. 创建一个Python3 虚拟环境。

```shell
$ virtualenv ./Django_env

# 运行以上命令，创建一个Python虚拟环境 Django_env。
```

3. 激活进入虚拟环境。

```shell
zsh.$ source bin/activate
(Django_env) zsh.$
```

4. 在这个虚拟环境下安装 Django开发框架。

```shell
(Django_env) zsh.$ pip3 install django
```



观察以下命令执行结果，理解不同环境下的python版本。

```shell
(Django_env) zsh.$ deactivate # 退出虚拟环境，回到 Mac OS下的默认 Python 环境
zsh.$ python --version
Python 2.7.16

zsh.$ source bin/activate # 激活进入本目录下的虚拟环境，即Django_env
(Django_env) zsh.$ python --version
Python 3.8.1
(Django_env) zsh.$ which python # 不错嘛，还会用which 命令
/Users/tony/3jSchool/Django_env/bin/python

(Django_env) zsh.$ deactivate
zsh.$ which python
/usr/bin/python
zsh.$ which python3
/Library/Frameworks/Python.framework/Versions/3.8/bin/python3
zsh.$ 

```



## 启动 Django 的WEB服务器



1. 创建一个Django项目

```shell
(Django_env) zsh.$ django-admin startproject 3jschool ./src # django-admin 第一个基础工具
```

2. 启动开发环境在本地机器的WEB服务器

```shell
(Django_env) zsh.$ python manage.py runserver # manage.py 第二个重要工具

 会输出如下信息：
Django version 3.1.2, using settings 'Django_env.settings'
Starting development server at http://127.0.0.1:8000/
```

3. 用浏览器连接本地服务器 http://127.0.0.1:8000

   连接成功会出现一个页面恭喜你：The install worked successfully! Congratulations!

   

4. 创建一个superuser来管理网站

```shell
(Django_env) zsh.$ python manage.py createsuperuser # createsuperuser 这个必须的！！
Username (leave blank to use 'tony'): Harry
Email address: 
Password: Judy
Password (again): 
```



## 创建一个子app

一个web网站实际上是有很多子app构成的，比如，https://3jschool.com 可能有‘ blog’ 子app：https://3jschool.com/blog，‘bookstore‘子app：https://3jschool.com/bookstore。

在Django框架下，每一个子app都是独立的。程序员的任务就是独立地开发这些子app，然后把它们加到域名后面。

1. 创建一个子app，这里的例子是blog

```shell
(Django_env) zsh.$ python manage.py startapp blog # startapp 可不要理解为“启动”，是“创建”一个app
```

2. 