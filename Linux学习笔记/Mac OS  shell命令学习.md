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



## 了解 /usr 目录

/usr 并不是 /users，到底是什么缩写？众说纷纭，比较广泛接受的说法是 Unix Software Resource的缩写，放置系统和用户的各种程序和代码。你看UNIX诞生才几十年功夫，有些事儿恨不得成立专门考古小组才能弄明白了。

- /usr：类似 C:/Windows/，系统使用，这地儿归皇上管。
  /usr/lib：类似 C:/Windows/System32，放置系统的各种运行时库。
  /usr/src：源码目录，系统使用。

- /usr/local：类似 C:/Progrem Files/，local表示是“归本地用户使用”，皇阿玛就不跟老百姓争地盘了。
  /usr/local/src：源码目录，用户使用。
  /opt：类似 C:/mySoftware，用户使用。opt表示“可选”，不需要的时候方便删除清理。



## 了解 /etc 目录

“etc” 不是首字母缩写，就是我们在英语中常见的 “等等”（其它零碎），有文化的人说是源自拉丁语 etcetera 。

/etc 目录下主要放置各种配置文件，比如常用的：

/etc/passwd

/etc/group

/etc/zprofile

/etc/bashrc

/etc/zshrc

/etc/hosts



##  ln 命令创建 link


分hard link 和 symbolic link 两种

1、Symbolic Link 更常用，所谓的“符号链接”

sudo ln -s 源文件 目标文件 

-s：代表 symbolic

一般需要较高的权限,  
所以，一般用 sudo

举例：

sudo ln -s /lLibrary/frameworks/JavaVM/java （此处空格）/usr/bin/java

就是让   /usr/bin/java  指向  /lLibrary/frameworks/JavaVM/java，   
成为一种类似的快捷链接


删除链接


rm /usr/bin/java

 /usr/bin/java这个文件就不存在了

实体文件 /lLibrary/frameworks/JavaVM/java 依然存在，  
只是指向它的快捷链接 /usr/bin/java不存在了。


2、Hard Link，所谓的“硬链接”

指向同一个 inode 的另一个文件名。  
甚至可能有2个以上的文件名，  
都指向同一个inode下的实体文件，  
都宣称它能代表那个实体文。

既然用得不多，就不详细说了。

sudo ln  源文件 目标文件

注意：没有－s 选项



## who 和 whoami 命令



**who** 

是看看谁连接上了本主机，  
包括通过 console （控制台),    
和 tty（终端）连接的

在Mac终端上 who 一下，  
至少会有两条记录，  
console 记录 和 ttys000 记录

console 是启动Mac时自动启动的控制台，  
记录的启动时间就是你开机时间

ttys000 是你连接的第一个终端

ttys001 是你连接的第二个终端


通过SSH连接Linux，who一下，  
通常显示一条 pts/0 记录


**whoami**

是看看你现在是什么用户身份操作机器

通常是你登录时的身份，比如 harry

su 一下再whoami，就显示你是 root了