
__知识点：__

- 安装 PIP3（在Mac上）
- 使用 PIP3
- 安装和使用 virtualenv


***

# @ 安装 PIP3

PIP是用于管理和安装Python程序包的工具  
Python程序包的发布者（一般都是开源包），在PIP上登记注册自己要发行的程序包，用户只要知道这个包的名字，就可以用PIP自动从网上下载和安装，而不需要知道类似于存放位置（通常是在github.io上）这些信息。  
因此，是超级方便的神器。


### 1) 从pypa网站get 最新的pip安装脚本

是用python写的  
软件包作为data包在里面了

> $ curl -o pip-install.py  https:  //  bootstrap. pypa. io / get-pip.py


### 2) 运行安装脚本

> $ python3 pip-install.py


### 3) 找到安装好的pip3  
比如：   
> $ sudo find / -name pip3  
$ /usr/local/bin/pip3

然后就可以使用了

***

# @ 使用 PIP3

### 1) 用 PIP 获取和安装 python 程序包

> $ pip3 install beautifulsoup4

PIP 这个神奇的工具就会自动到网上找到官方的 beautifulsoup4 的程序包，给你安装上。  
其实也没有什么神奇的，因为 beautifulsoup4 这些 python 程序包已经在PIP上登记注册了，PIP自然就知道放在哪儿。用户就没有必要知道了。

### 2) freeze 当前的安装信息
> $ pip3 freeze > baseline.txt

baseline.txt 上就会记录所有用 pip3 安装的程序包，以及它们所在的目录位置  
如果在某一个 virtualenv 虚拟环境激活状态下运行这个 freeze 命令，输出的将是这个虚拟环境下用 PIP3 安装的所有程序包信息。

freeze输出的安装信息长得这样的：

```
appdirs==1.4.0
beautifulsoup4==4.5.3
packaging==16.8
pyparsing==2.1.10
six==1.10.0

```

***


# @ 安装和使用 virtualenv



virtualenv是一个创建独立隔离Python开发、运行环境的工具。  
创建一个包含所有必要的可执行文件的文件夹，供特定Python开发项目专用

### 1) 通过pip安装virtualenv：

> $ pip install virtualenv

基本使用


### 2) 创建一个虚拟环境：

> $ virtualenv myProject

 将会在当前的目录中创建一个目录，包含了Python可执行文件，以及 pip 库的一份拷贝，这样就能安装其他包了。

创建时可以指定Python版本

> $ virtualenv -p /usr/bin/python3.6 myProject

这将会使用 /usr/bin/python3.6 作为Python解释器。

### 3) 激活 virtualenv 虚拟环境

> $ source myProject/bin/activate

当前虚拟环境的名字（这里是 myProject）会显示在提示符左侧  
在激活状态下，任何你使用pip安装的包将会放在 ~/myProject 目录下，与全局安装的Python隔绝开。

### 4) 停用虚拟环境：

> $ deactivate

这将会回到系统默认的Python解释器，包括已安装的库也会回到默认的。

### 5) 删除虚拟环境

要删除一个虚拟环境，只需删除它的目录：

> rm -rf ~/myProject

***
The end
