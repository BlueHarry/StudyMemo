# Linux学习



## Mac OS /Linux 命令行提示符设置

Linux的命令行提示符的模样是由 PS1变量决定的，所以要想自己定制一个酷炫的命令行提示符，需要修改启动配置文件。

是在哪个配置文件中呢？这要看你当前使用哪个Shell。如果你是Mac OS环境，缺省启动 zsh，而Linux环境会缺省启动 bash。

在zsh环境下，启动配置文件是 /bin/zshrc

在bash环境下，启动配置文件是 /bin/bashrc 

在这两个配置文件中，通常会有类似这么一行，用于设置 PS1环境变量。

```bash
PS1='\h:\W \u\$ '
```

su到root账号，用vim编辑修改这一行就可以了。



## chsh -s 命令切换Shell

此命令用于切换你正在使用的 Shell。一般Mac OS 缺省的Shell是 zsh，也就是 Z Shell，这是一种功能比较强大的新型Shell，而Linux缺省使用的Shell一般都是 bash。 

对于大多数的用户和程序员来说，两种Shell没有什么差别，所以没有必要去chsh改变它，以免带来意想不到的奇怪问题。如果确实需要切换Shell，可以用下面这个命令：

```zsh
chsh -s /bin/bash
```

需要当前Shell会话连接，重新登录进入才能生效。比如，如果你是在使用Mac OS的终端仿真器，需要退出终端，重新登录才能切换到新的Shell会话环境下。



