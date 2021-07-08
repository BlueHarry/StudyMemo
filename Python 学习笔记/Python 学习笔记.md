# Python 学习笔记



## Python虚拟环境安装

Python的虚拟环境工具 virtualenv ，如同容器一样，创建一个特定的Python运行环境，维持一份特定依赖库文件。也就是说不需要大家都共享一个系统默认的Python运行环境，而是自己在特定的目录下（比如，./Django_ev）建立一套自己独立使用的依赖文件包（文件夹）。

想想也是很有道理的，每个Python程序包独立一份自己的依赖文件拷贝，自己独立升级和维护，又占不了多少文件存储空间，有什么必要共享呢？凭空搞出N多兼容和匹配的问题，自己住独立的别墅多好，爱装啥电梯、用多少伏特的电压啥的，自己独立吧。



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

