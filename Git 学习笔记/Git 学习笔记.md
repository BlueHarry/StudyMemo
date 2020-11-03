# Git 学习笔记



## Git 的基本概念

- Git 是一种VCS，版本控制系统（Version Control System，有人也叫它版本管理系统） 。版本控制系统主要就是要管理一组文件的变化历史，每一个时间基线对应一个版本。
- Git 是 Distributed VCS，分布式VCS，就是支持多人协同开发。每一位开发者有一份完整的拷贝在本地机器上。居于中心的服务器负责协调写冲突和版本同步。

![](001Git基本概念.jpeg) 

此处必须上图了，否则说不清楚。

- 对于程序员来说，你要面对3分拷贝：Working Copy，Local Repository 和 Remote Repository。
-  Working Copy。也叫working tree，就是你正在编码的一组文件，我们就叫它工作拷贝吧。比如用Visual Studio Code读写的程序代码。你写呀写呀，改呀改的那些代码文件。实际上，在VSCode上做修改（change）的时候，changes分成两个状态，changes 和 staged changes。commit之前先要把changes “+”到 staged changes 区域，才能 commit到 Local Repository。
- Local Repository，本地代码仓库。你如果从来没有用过任何版本管理系统（VCS），会觉得这玩意儿有点诡异。我正在编写的代码文件，存储在我的本地磁盘上，是持久化的存储呀，难道不就是存储代码的仓库吗？你一脸困惑地发问。是的，你本地文件系统上的工作拷贝（Working Copy）一般不会丢失，可是会被你不断地修改，包括错误地修改。安装了Git这类版本管理系统之后，你可以定期“提交（commit）”你的成果到本地代码仓库封存起来，贴上标签，说这是某某版本，Version x.y.z。
- Remote Repository，远程代码仓库。它要负责管理整个项目开发团队提交上来的所有代码，是一个项目的公共代码仓库。显然它除了具有本地代码仓库的版本控制功能外，还必须处理好一个开发团队内各个程序员提交代码时的冲突问题。如果两个程序员要同时修改同一个文件怎么办？必须有一种控制机制，这个当然一点都不难理解。一个项目的所有程序员都必须基于同一套版本文件进行修改，在你进入一个项目开始开发之前，你必须先把远程的版本文件 Pull 到本地代码仓库，基于这个共同的基础来开发新功能走向更加美好的未来。
- commit 和 push。你写好代码之后，需要两步才能把代码提交到远程项目代码仓库上去。第一步，你要把刚写好的代码“提交（commit）”到本地代码仓库（local Repository）；第二部，你要 push 你的工作成果到远程项目代码仓库（Remote Repository）。
- 






## 创建代码仓

### 1.  创建代码仓 git init 和 git clone
初始化创建 Local Repository，有两种方法：
1. git init 将一个本地文件目录初始化为一个版本仓库。初始化以后这个目录的所有文件就开始受到git的版本控制。说得更通俗一点，就是git将会监控和记录下这个目录下所有文件的改变历史。

2. git clone 从一个远程版本库客隆，在本地生成一个一摸一样的版本仓库文件目录。
   例如：git  clone https://github.com/3jschool/homepage

   

所谓的版本仓库（Repository），其实就是在相应的文件目录里多了一个 .git 文件，存有git repository的管理信息。因此要想把这个版本仓库文件目录变成普通的文件目录，只要用 “rm -rf .git”命令删除这个 .git文件就可以了。可以再用“git show”命令确认一下这个目录还是不是一个git版本仓库。

### 2.  查看代码仓 git show



### 3.  删除代码仓 rm  -rf 

```shell
zsh.$ rm -rf .git
zsh.$ git show
fatal: not a git repository (or any of the parent directories): .git
```



## 提交代码到仓库

###  1.  git status
查看工作区（working tree）代码与入库缓存区（stage）的差别

### 2.  git add 
把工作区的变化（changes）提交到stage缓存区，可以理解为生成了一份快照。当然标准叫法是一次change，这个词汇中文比较没有具象感，叫“切片”、“快照”比较容易理解和记住。

### 3. git commit -m '版本注释'

把缓存区的变化提交到本地的代码仓

### 4. git push origin master

把本地代码仓的变化推送到远端代码仓（比如，github repo）

## 撤销一次错误提交 

### 1.  git reset 重置

如果执行了一次错误的提交（commit），希望从local repo中退回，那就要用到 reset 命令回退到前面的某一次提交“快照”，通常就是回到这次错误提交的上一次提交快照。

分为所谓的软重置（--soft）和硬重置（--hard），区别在于 `git reset --soft`  不仅让local repo版本回退到以前提交的某一快照，而且会把提交的错误文件放回到 staged change区，供你修改后再次提交。而 ` git reset --hard` 让local repo版本回退到以前提交的某一快照后，彻底丢弃刚刚提交的错误文件。

```shell

zsh.$ vim test.txt
zsh.$ cat test.txt
This is a wrong commit.
zsh.$ git add .
zsh.$ git commit -m "wrong commit test"

# 以上命令执行了一个错误提交

zsh.$ git log
commit 45922fc31db1c4fcdbedf00febb884a9049f3add (HEAD -> main)
Author: Tony Bai <tony@TonydeMacBook-Pro.local>
Date:   Tue Nov 3 11:57:26 2020 +0800

    wrong commit test

commit 7c1a757d3cb94cf35da78f190286f3387fdf7d8b (origin/main, origin/HEAD) # 注意hash
Author: BlueHarry <harryhuang2012@gmail.com>
Date:   Tue Nov 3 10:18:33 2020 +0800

# git log 可以看出，wrong commit test 已经提交，这个快照成为 main 分支的 HEAD

```

```shell
zsh.$ git reset --soft 7c1a757d3cb94cf35da78f190286f3387fdf7d8b # 回退到上次提交的快照
zsh.$ git status
On branch main
Your branch is up to date with 'origin/main'.

Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
	new file:   test.txt

# 以上命令，让local repo回退到指定的以前提交的快照，而且把提交错误的文件放回了 stage change缓存区，随后可以 restore 到 working tree 进行修改。

```



```shell
zsh.$ git restore --staged test.txt
zsh.$ vim test.txt
zsh.$ cat test.txt
This is a correct commit.
zsh.$ git add .
zsh.$ git commit -m "Correct commit"

# 以上命令，对于软重置后退回的错误代码，修改后再次提交。这是日常开发过程中经常会发生的场景。
```



```shell
zsh.$ git log
commit d42d613ce3316e01d534683d6bb9aaf1aeff28fb (HEAD -> main)
Author: Tony Bai <tony@TonydeMacBook-Pro.local>
Date:   Tue Nov 3 12:01:17 2020 +0800

    Correct commit

commit 7c1a757d3cb94cf35da78f190286f3387fdf7d8b (origin/main, origin/HEAD) # !!
Author: BlueHarry <harryhuang2012@gmail.com>
Date:   Tue Nov 3 10:18:33 2020 +0800

zsh.$ git reset --hard 7c1a757d3cb94cf35da78f190286f3387fdf7d8b # !!

# 以上命令，用 --hard 彻底回退刚才的实验提交，放弃所有更改。通常会用到的硬重置
# 1、像上面的练习场景，练习完成后，彻底擦除实验垃圾数据。
# 2、如果团队开发，某个模块另外一个人已经修改，并且提交，并且合并进版本了。你做的修改已经不需要了，你就需要彻底擦除，不用 push 到 github 的团队共享代码仓了。
```

### 2. 直接覆盖

如果只是为了修改一个错误，最简单粗暴的方式是提交一个正确的文件覆盖错误的文件。这个就很简单，先修改文件，然后【`git add .  ->  git commit `】，不用赘述了。就如同街边电线杆上的广告贴纸，覆盖上去就完事了。



## 放弃不成功的编码（未完成。。。）

日常开发会有这样一种场景，先从github上 “git pull” 下来一个版本，然后开发编码，边开发边 “ git add” 到“staged change“区。突然发现刚才乱写一通的代码根本就不对，想回到前面刚刚 ”git add“的版本。这时可以用 “git checkout ” 。

## 分支

### 1. git branch 创建分支

在 main主分支 HEAD 上创建一个分支

### 2. git checkout 检出分支

第一步：把某一个分支检出到工作区

```shell
zsh.$ git branch branch-test
zsh.$ git checkout branch-test
Switched to branch 'branch-test'
zsh.$ git status
On branch branch-test
nothing to commit, working tree clean

# 检出分支后就可以修改代码，通常是修改某一特定部分代码。比如，修改数据库访问模块的代码。
```

第二步：编写好代码后，提交到 local repo 

```


```





### 3. git merge 合并分支

```shell


```





### 4. git branch -d 删除分支









## 忽略 Mac 的 .DS_Store 文件

.DS_Store是Mac OS用来存储这个文件夹的显示属性的，被作为一种通用的有关显示设置的元数据（比如图标位置等设置）为Finder、Spotlight用。所以在不经意间就会修改这个文件。而文件共享时为了隐私关系将.DS_Store文件删除比较好，因为其中有一些信息在不经意间泄露出去。

为了避免每次提交时的麻烦，可以设置让 git 忽略这个文件。



### 设置全局忽略

在用户home目录下创建文件 ~/.gitignore_global （放在用户home目录下，而不放在一个具体项目的目录下，是为了全局使用不被无意删除）

```shell
$ touch ~/.gitignore_global
```

在.gitignore_global 文件里添加上一行： **/.DS_Store

```shell
$ cat .gitignore_global
**/.DS_Store

$ git config --global core.excludesfile ~/.gitignore_global
```



### 设置本项目目录忽略

在本项目目录下创建 .gitignore文件，在文件中添加一行 **/.DS_Store

```shell
$ cat .gitignore
**/.DS_Store
```



