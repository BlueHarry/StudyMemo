
__知识点__

- 全局配置文件 profile 和  bashrc
- 用户配置文件 \~/.bash_profile 和 \~/.bashrc 

# @ 废话连篇 

/etc/profile、/etc/bashrc、\~/.bash_profile、\~/.bashrc 有什么区别？  

/etc/profile: 设置系统环境参数，比如: PATH，JAVA_HOME等. 这里面设置的环境变量对 __系统内所有用户__ 生效。  

/etc/bashrc:  设置系统 bash shell 相关的参数，对系统内 __所有用户、在运行bash命令__ 情况下起作用。

~/.bash_profile: 和/etc/profile 类似，但是这个是针对用户来设定的，也就是说，你在/home/user1/.bash_profile 中设定了环境变量，只对 user1 这个用户生效  

~/.bashrc: 和/etc/bashrc类似, 只是针对用户自己而言，不对其他用户生效

另外/etc/profile中设定的变量(全局)的可以作用于任何用户,而~/.bashrc等中设定的变量(局部)只能继承/etc/profile中的变量,他们是"父子"关系（？）

~/.bash_profile 是交互式、login 方式进入 bash 运行的，意思是只有用户登录时才会生效

~/.bashrc 是交互式 non-login 方式进入 bash 运行的，用户不一定登录，只要以该用户身份运行命令行就会读取该文件。

# @ /etc/profile 和 /etc/bashrc 示例

```
$ cat /etc/profile
# System-wide .profile for sh(1)

if [ -x /usr/libexec/path_helper ]; then
	eval `/usr/libexec/path_helper -s`
fi

if [ "${BASH-no}" != "no" ]; then
	[ -r /etc/bashrc ] && . /etc/bashrc
fi

# JAVA_HOME
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home"
export PATH=$JAVA_HOME/bin:$PATH

```

```
cat /etc/bashrc
# System-wide .bashrc file for interactive bash(1) shells.
if [ -z "$PS1" ]; then
   return
fi

PS1='\h:\W \u\$ '
# Make bash check its window size after a process completes
shopt -s checkwinsize
# Tell the terminal about the working directory at each prompt.
if [ "$TERM_PROGRAM" == "Apple_Terminal" ] && [ -z "$INSIDE_EMACS" ]; then
    update_terminal_cwd() {
        # Identify the directory using a "file:" scheme URL,
        # including the host name to disambiguate local vs.
        # remote connections. Percent-escape spaces.
	local SEARCH=' '
	local REPLACE='%20'
	local PWD_URL="file://$HOSTNAME${PWD//$SEARCH/$REPLACE}"
	printf '\e]7;%s\a' "$PWD_URL"
    }
    PROMPT_COMMAND="update_terminal_cwd; $PROMPT_COMMAND"
fi

```
安装 Python3 后，安装包对 .bash_profile 的修改：

```
$ cat .bash_profile 

# Setting PATH for Python 3.6
# The original version is saved in .bash_profile.pysave
PATH="/Library/Frameworks/Python.framework/Versions/3.6/bin:${PATH}"
export PATH


```
