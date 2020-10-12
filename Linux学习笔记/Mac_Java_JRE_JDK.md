__知识点__

- Java JRE，JDK 缺省安装目录

***
# @ 它们是什么东东？  

Java JRE    
Java Runtime Environment ，即Java运行时环境。
是运行Java程序的 JVM 环境
通常的应用场景，比如，浏览器运行 Java Applet时需要这个平台。

Java JDK  
Java Development Kits，即Java开发工具包。  
是在本机做 Java 开发需要的编译、调试环境。  
也包括一个 JVM ，但是只用于开发调试，这个JVM不是本机缺省的 Java 运行虚拟机。  

# @ 它们缺省安装在哪个目录下？

JER

```
/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home/bin/java
```
JDK

```
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home"
export PATH=$JAVA_HOME/bin:$PATH
```

 


