# Mac OS shell 命令



## ~/.zprofile 环境参数配置文件

**知识点：** ～/ 表示用户home目录，这个常识要记住。用 cd 或者 cd～命令进入。 

```shell
zsh.$ pwd
/
zsh.$ cd ~
zsh.$ pwd
/Users/tony
zsh.$ ls -la .zprofile
-rw-r--r--  1 tony  staff  275  1 29  2020 .zprofile
zsh.$ cat .zprofile

# Setting PATH for Python 3.8
# The original version is saved in .zprofile.pysave
PATH="/Library/Frameworks/Python.framework/Versions/3.8/bin:${PATH}"
export PATH
export PATH="/Users/tony/DataHarry/flutter/bin:${PATH}"
export PATH="/Users/tony/Library/Android/sdk:${PATH}"

zsh.$
```

上图：用 "cd ~"进入home目录“/Users/tony”，上面有一个点开头的隐含文件“.zprofile”，用cat命令查看。可以看出来PATH这个重要的环境变量是在这里设置的。当然，只对用户tony在使用zsh是有效。

其它环境配置文件：

- /etc/profile，对系统内所有shell，所有用户生效。没有特殊需要不要修改这个文件，最好保持其原生态。 
- /etc/zshrc，对所有使用zsh shell登录的用户生效，最好不要修改。 
- /etc/bashrc， 对所有使用bash shell登录的用户生效。Mac OS缺省用zsh登录，所以一般用不到这个配置文件。
- ~/.zprofile，就是建议设置的配置文件。

**为了保持环境的原生状态，应该习惯只设置特定shell（这里是zsh）特定用户（这里是tony）的环境参数文件~/.zprofile，就是保持最小的环境改变。**



## source 执行命令



```shell
zsh.$ pwd
/Users/tony/3jSchool/trydjango
zsh.$ bin/activate
zsh: permission denied: bin/activate
zsh.$ ls -la bin/activate
-rw-r--r--  1 tony  staff  2368 10 18 09:59 bin/activate
zsh.$ source bin/activate
(trydjango) zsh.$
```

上图：bin/activate 文件，tony没有执行权限。但是可以用 source bin/activate 命令执行。



## chsh命令改变登录shell

```shell
$ chsh -s /bin/bash

然后重新启动终端，即可生效。
```

