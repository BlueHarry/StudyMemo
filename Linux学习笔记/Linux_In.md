LinuxCmd
Linux下 ln 命令的用法
***
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
