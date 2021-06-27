# Ubuntu学习笔记



# 运行环境变量 (学习更新中。。。)




## Mac环境变量加载顺序

Mac Catalina 以前的版本一般使用Bash作为默认shell，2019年推出 Catalina 后将 ZSH 设为默认shell。

Mac系统的环境变量，加载顺序为：

```
/etc/profile` -> `/etc/paths` -> `/etc/paths.d/`文件夹所有内容 -> `~/.bash_profile` -> `~/.bash_login` -> `~/.profile` -> `~/.bashrc
```

> 安装软件后, 最常更改配置的是`/etc/paths.d/`, `~/.bashrc`, `~/.bash_profile`. 可查看这些相关文件.

其中, `/etc/profile`, `/etc/paths`, `/etc/paths.d/`是系统级别的，系统启动就会加载，后面几个是当前用户级的环境变量。

后面3个按照从前往后的顺序读取，如果`~/.bash_profile`文件存在，则后面的几个文件就会被忽略不读了，如果`~/.bash_profile`文件不存在，才会以此类推读取后面的文件。

`~/.bashrc` 没有上述规则，它是bash shell打开的时候载入的。

如果没特殊说明,设置PATH的语法都为：



```ruby
# 中间用冒号隔开 (Linux/Mac)
export PATH=$PATH:<PATH 1>:<PATH 2>:<PATH 3>:------:<PATH N>
```

（一）全局设置
 下面的几个文件设置是全局的，修改时需要root权限

1. `/etc/paths` （全局建议修改这个文件 ）
   编辑 paths，将环境变量添加到 paths文件中 ，一行一个路径

> Hint：输入环境变量时，不用一个一个地输入，只要拖动文件夹到 Terminal 里就可以了。

1. `/etc/profile` （建议不修改这个文件 ）
   全局（公有）配置，不管是哪个用户，登录时都会读取该文件。
2. `/etc/bashrc` （一般在这个文件中添加系统级环境变量）
   全局（公有）配置，bash shell执行时，不管是何种方式，都会读取此文件。
3. `/etc/paths.d/`应用启动配置PATH
   1. 创建一个文件：`sudo touch /etc/paths.d/MySQL`
   2. 用 vim 打开这个文件（如果是以 open -t 的方式打开，则不允许编辑）：`sudo vim /etc/paths.d/mysql`
   3. 编辑该文件，键入路径并保存（关闭该 Terminal 窗口并重新打开一个，就能使用 mysql 命令了）`/usr/local/mysql/bin`

据说，这样可以自己生成新的文件，不用把变量全都放到 paths 一个文件里，方便管理。

（二）单个用户设置

1. `~/.bash_profile` （任意一个文件中添加用户级环境变量）. 若bash shell是以login方式执行时，才会读取此文件。



```bash
export PATH=/opt/local/bin:/opt/local/sbin:$PATH
```

1. `~/.bashrc` 同上

如果想立刻生效，则可执行下面的语句：`source 相应的文件`

一般环境变量更改后，重启Shell后生效.



## $PATH 环境变量

新手最需要学习的是设置环境变量。

```shell
% cat /etc/paths
/usr/local/bin
/usr/bin
/bin
/usr/sbin
/sbin
```



## ~/.zprofile 环境变量配置文件

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

# 其它未归类命令



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

# 然后重新启动终端，即可生效。
```



## ln 命令


分hard link 和 symbolic link 两种

1、Symbolic Link 更常用，所谓的“符号链接”

```shell
sudo ln -s 源文件 目标文件 

# -s：代表 symbolic，一般需要较高的权限,  所以一般用 sudo
```



举例：

```shell
sudo ln -s /lLibrary/frameworks/JavaVM/java （此处空格）/usr/bin/java

# 就是让   /usr/bin/java  指向  /lLibrary/frameworks/JavaVM/java，成为一种类似快捷链接
```




删除链接

```shell
rm /usr/bin/java

# /usr/bin/java这个文件就不存在了，实体文件 /lLibrary/frameworks/JavaVM/java 依然存在，只是指向它的快捷链接 /usr/bin/java不存在了。
```

2、Hard Link，所谓的“硬链接”

指向同一个 inode 的另一个文件名，甚至可能有2个以上的文件名，都指向同一个inode下的实体文件，都宣称它能代表那个实体文。既然用得不多，就不详细说了。

```shell
sudo ln  源文件 目标文件

# 注意：没有－s 选项
```





## find Whereis 和 locate

用了很多年find，  
似乎不习惯用 Whereis 或者 locate，    
那是比较Low的习惯


**Whereis python**

查找Shell命令或可执行的程序，  
比如，/usr/bin/python，   
你可以通过 $ whereis python找到。 

通常只有一条查询结果输出


**locate python**

查找所有包含有 “python” 的目录和文件，  
通常会输出N多条结果。  
自己试试吧

要注意第一次使用locate，  
会提示要不要创建查询数据库。  
那创建好了，  
那点磁盘空间也算不了什么，  
咱们家不是富裕吗？



## Mac OS /Linux 命令行提示符设置

Linux的命令行提示符的模样是由 PS1变量决定的，所以要想自己定制一个酷炫的命令行提示符，需要修改启动配置文件。

是在哪个配置文件中呢？这要看你当前使用哪个Shell。如果你是Mac OS环境，缺省启动 zsh，而Linux环境会缺省启动 bash。

在zsh环境下，启动配置文件是 /etc/zshrc

在bash环境下，启动配置文件是 /etc/bashrc 

在这两个配置文件中，通常会有类似这么一行，用于设置 PS1环境变量。

```bash
# Default prompt
PS1='\h:\W \u\$ '
```

su到root账号，用vim编辑修改这一行就可以了。

应该尽量修改 ～/.zprofile





## chsh -s 命令切换Shell

此命令用于切换你正在使用的 Shell。一般Mac OS 缺省的Shell是 zsh，也就是 Z Shell，这是一种功能比较强大的新型Shell，而Linux缺省使用的Shell一般都是 bash。 

对于大多数的用户和程序员来说，两种Shell没有什么差别，所以没有必要去chsh改变它，以免带来意想不到的奇怪问题。如果确实需要切换Shell，可以用下面这个命令：

```zsh
chsh -s /bin/bash
```

需要当前Shell会话连接，重新登录进入才能生效。比如，如果你是在使用Mac OS的终端仿真器，需要退出终端，重新登录才能切换到新的Shell会话环境下。

