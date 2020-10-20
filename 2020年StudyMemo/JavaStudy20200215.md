# 阿里云 Java 课程（李兴华）笔记 20200215
*用MacBook边学习边做笔记是一种长期的学习模式。我的方法是，找到一种合适自己的模式，然后坚持做下去，带来长期收益*

## Java 类
1. 所有Java代码必须在一个类里。
1. public 声明的类必须与.java 文件名一致，以便通过文件名被调用。
1. 没有public 的类，文件名与类名称可以不一致，但是javac编译后的 .class 文件名称是所定义的类名称，而不是 .java文件的名称。
1. 在一个.java 源程序文件中可以定义多个类，但是只能有一个public的类，而且javac编译后会为每一个类定义生成一个.class独立文件。也就是说，实际上Java程序的执行单元是一个个.class 类文件。
1. 实际开发项目中，尽量不要在一个.java文件中定义多个类。也就是说，最佳实践是，在一个.java文件中定义一个同名称的 public 类，编译后产生一个同名称的.class文件，供JVM解释执行。
1. 主方法所在的类称为“主类”，都是通过 public class 来定义的。主方法写法要记住：` public static void main(String [] argc){   }; `

## JShell

1.  Jshell 是可以直接执行Java命令的一个CLI工具，方便调试
1. jshell 进入，/exit 指令退出。在Mac的zsh上用 ^Z 退出jshell。应该是一种非正常退出模式。
1. /open filename 可以打开一个有Java命令的文件，并执行。
1. 用处不大，偶尔需要的时候拿来验证以下函数之类的使用。


## SET CLASSPATH

1. 在Mac上，运行 ` exprt CLASSPATH="/Users/harry/Java"` 之类的命令。通过 ` echo $CLASSPATH` 可以看到是否有设置成功。
1. 很多情况下要关注缺省设置 `CLASSPATH=. `，就是“点”当前目录。因为有可能这个环境变量被修改了，从而找不到当前目录。出现这种情况就需要运行 ` export CLASSPATH=.` 让CLASSPATH恢复到当前目录的设置。
1. PATH 和 CLASSPATH的区别。PATH 是操作系统可执行文件的搜索路径（供操作系统shell使用），CLASSPATH是 JRE 环境下Java 搜索.class 类文件的路径（供JVM解释执行类文件使用）。