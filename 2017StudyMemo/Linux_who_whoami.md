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
