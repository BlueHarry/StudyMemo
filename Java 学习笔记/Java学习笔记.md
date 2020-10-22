# Java学习笔记

阿里云 Java 课程（李兴华）笔记 20200215

## Hello World

```java
public class Hello{
  public static void main(String args[]){
    System.out.print('Hello World!');
  }
}
```





## Java 类

1. 所有Java代码必须在一个类里。
1. public 声明的类必须与.java 文件名一致，以便通过文件名被调用。
1. 没有public 的类，文件名与类名称可以不一致，但是javac编译后的 .class 文件名称是所定义的类名称，而不是 .java文件的名称。
1. 在一个.java 源程序文件中可以定义多个类，但是只能有一个public的类，而且javac编译后会为每一个类定义生成一个.class独立文件。也就是说，实际上Java程序的执行单元是一个个.class 类文件。
1. 实际开发项目中，尽量不要在一个.java文件中定义多个类。也就是说，最佳实践是，在一个.java文件中定义一个同名称的 public 类，编译后产生一个同名称的.class文件，供JVM解释执行。
1. 主方法所在的类称为“主类”，都是通过 public class 来定义的。主方法写法要记住：` public static void main(String [] argc){   }; `

## JShell

1.  Jshell 是可以直接执行Java命令的一个CLI工具，方便调试
1.  jshell 进入，/exit 指令退出。在Mac的zsh上用 ^Z 退出jshell。应该是一种非正常退出模式。
1.  /open filename 可以打开一个有Java命令的文件，并执行。
1.  用处不大，偶尔需要的时候拿来验证以下函数之类的使用。


## SET CLASSPATH

1. 在Mac上，运行 ` exprt CLASSPATH="/Users/harry/Java"` 之类的命令。通过 ` echo $CLASSPATH` 可以看到是否有设置成功。
1. 很多情况下要关注缺省设置 `CLASSPATH=. `，就是“点”当前目录。因为有可能这个环境变量被修改了，从而找不到当前目录。出现这种情况就需要运行 ` export CLASSPATH=.` 让CLASSPATH恢复到当前目录的设置。
1. PATH 和 CLASSPATH的区别。PATH 是操作系统可执行文件的搜索路径（供操作系统shell使用），CLASSPATH是 JRE 环境下Java 搜索.class 类文件的路径（供JVM解释执行类文件使用）。



## 类和对象

1. 自然语言中有两种名词，一种是表述一类的，比如，人、狗等。一种是表述具体一个个体的，比如，比尔盖茨、阿飞狗狗等。类是对同一群体共同特征的定义。
1. 面向对象编程语言中的“class类”就是用于描述一类事物的概念，她就像是一个模版。而某一个类的实例化“Object对象”，就是用于指名一个具体个体的。如果是一个人对象，通常就有具体的名字，年龄，联系方式等。
1. 在面向对象的编程语言中，必须先定义class类，然后根据这个类模版个性化出一个具体的对象实体（类实例化出对象）。 就如同现代汽车工业，必须先有汽车图纸，然后根据这个图纸生产出具体的一辆辆实体汽车。

## 定义一个类和对象

1. String 首字母大写这种都是参考类型（数组也是典型的参考类型），如果没有初始化，其默认值是 null 。像int这种首字母小写的基本类型，默认值是0，对于bool型当然就是false，其实也是0。

## 对象的内存分析

1. 栈内存，存储对象地址，也就是对象指针。声明一个参考型变量时，就开辟一个栈内存空间，初始值为 null。比如，`Dog aFei = null;`
1. 堆内存，存储具体实例化对象。new 操作开辟出一个堆内存空间来实例化对象。比如， ` aFei = new dog();`
1. 仅有对象变量声明，没有new实例化对象，会出现 NullPointerException 错误。
1. 同一个堆内存空间，或者说同一个对象实例，可以被不同的栈内存指针指向。一个栈内存指针可以更换指向另一个对象实例。
1. 内存垃圾，没有任何栈内存指向的堆内存空间，就会成为垃圾空间。GC garbage collector垃圾回收器会不定期回收。如果垃圾操作太多就会影响性能，所以要尽量避免垃圾产生。

## 封装性

1. 方法成员，一般是供外部调用的，所以不需要封装。
1. 属性成员，通用的做法是需要封装处理的，通过 private 关键字对属性进行封装。比如，` private String name = "aFei";`
1. private。Java开发规范中，对于private 属性要求通过 setter 和 getter 方法访问。比如，` public void setName(String name);`
1. 构造方法，在对象实例化时被自动调用，通常用来批量初始化属性成员。而不是通过大量调用 setter 方法来初始化 private 属性成员。

## 其他

- 内存分析
- 封装



【心得】这个课程把视频切分成10分钟左右的主题片段，是一种很好的实践。太长的视频不利于以后复习和查找。

## 构造函数

1. 即便是我们没有定义构造函数，Java程序 **编译时** 也会自动生成一个什么都不做的“无参数构造函数”。这就是为什么我们总是可以使用例如 ` Dog myDog = new Dog();` 这样的语句来创建一个对象，并用缺省的无参构造函数来初始化。在这里无参构造函数就是 ` Dog()`，虽然我们没有定义，可是她却天然存在。
1. 注意：如果我们已经定义了构造函数（一般都是有参函数），编译时就不会自动生成那个“无参构造函数”。` new Dog()；` 语句会触发报错。
1. 定义构造函数时没有像普通函数那样设置返回值类型，是为了让编译器分析时识别这是一个特殊的函数（构造函数），并在实例化对象时自动调用执行。普通函数是这样的：` public void setName(String name){...};`，而构造函数是这样的：` public Dog(String name, int age){...};`。第二个原因是，构造函数如果说有返回值，返回的就是这个实例化对象的地址指针。观察` Dog myDog = new Dog();`，可以认为 Dog() 返回了地址指针给 myDog 。

## 匿名对象

1. 还是看这段代码 ` Dog myDog = new Dog();`，在这里myDog是对象名称，是指向对象 ` new Dog() ` 的。
1. 如果让任何一个对象名称变量指向 ` new Dog() ` ，像这样直接使用 ` new Dog().setName("aFei") `是可以的。这时 ` new Dog() ` 就叫做匿名对象。
1. 匿名对象仅仅被使用一次就变成了内存垃圾，将会被GC回收。

## this关键字

- `this.name` 访问当前对象的name属性
- `this.setAge() ` 访问当前对象的 setAge() 方法
- `this()` 访问当前对象的构造函数
- 观察构造函数：

```java
public Dog(String name, int age){
    this.name = name;
    this.age = age;
};
```

如果这里不用 `this.name = name;` ，而是这样写  `name = name `语法上是正确的。

```java
public Dog(String name, int age){
    name = name;
    age = age;
};
```

但是并没有赋值给对象属性 `this.name` ，编译器会把两个name 都解析为参数name，就是自己赋值给了自己，没有任何意义。这是因为如果发生变量重名，会优先在{}界定的程序块内匹配和解析 `name` 参数变量，而不会认为左边的name是属性变量 `this.name`。


- 规范编程方式是，所有对对象属性变量的访问都要加上this关键字。如，`this.name; this.age;`
- this() 调用，在一些特殊情况下可以用于减少代码重复。在有多个构造函数的情况下，复杂的构造函数可能包含简单构造函数的重复代码，用包含this()的方式可以减少代码重复。但是，this() 只能被另一个构造函数调用，不能被其它方法调用，而且 `this();` 必须放在第一行。第三就是要避免循环调用，产生递归构造器调用错误。这是严格的语法要求。

## 简单Java类

- 像描述一个人、一辆车、一本书等，没有复杂逻辑，仅仅用于存储信息的类，称为Java的简单类。
- 类的所有属性都应该用 private 进行封装，并提供 setter 和 getter 供外部访问。
- 通常提供多个不同参数数量的构造方法，必须有一个无参构造方法。
- 类里面不应有任何输出语句，所有信息的获取都通过返回语句实现。
- 通常提供一个获取对象属性详细信息的方法，比如，getInfo() 。

## static 关键字

1. 用来修饰属性和方法。
1. static 属性会存储在全局数据区，供所有对象公用。一旦一个对象对这个static 属性进行了修改，所有其它对象的这个属性也都被修改了，因为仅有一份 static（静态）数据存储在全局数据区，个对象堆内存中没有存储这个static 属性。
1. 虽然每个对象都可以对他们的static公共属性进行修改，但是规范的做法是通过类来修改。正因为如此，**static属性可以通过类名称直接访问** 。比如，` static String dad; ... Dog.dad = "Harry";`
1. static 属性虽然在类中定义，但是不受类的实例化对象的控制。因此，也可以在没有实例化对象的时候使用。
1. 在类设计过程中，首先考虑的是使用非static属性（必须通过实例化对象访问），只有在需要保存公共信息时才会使用static属性（可以通过类直接访问）。
1. static用来修饰方法时，也是可以通过类名称直接调用。比如，` Dog.run();` 不必一定要有实例化对象。
1. 很多系统提供的类库方法是静态方法，可以像调用类似C语言函数库那样调用，而不必需要先创建一个对象。这个特性以前不知道，困扰了我很久。为什么我的类方法非要实例化对象后才能调用，而类库提供的有些方法不需要呢？因为我没有用 static 这一特性。
1. static 方法只允许调用 static 属性或 static 方法。因为它在静态全局变量空间，无法访问到实例化对象的堆存储空间。
1. 相反，非static 方法是可以调用static 属性和方法的。也就是，从实例化对象的对空间，是允许访问静态全局变量空间的。

```java
public class Dog{
    private String name;
    private String color;
    private int age;
    private static String from = "England";

    public static void setFrom(String from){
        Dog.from = from;
    };
  
    public void setDog(String name, String color, int age){
            this.name = name;
            this.color = color;
            this.age = age;
    };
    public String getInfo(){
        return "狗狗名字：" + this.name + "、狗狗颜色：" + this.color + "、狗狗年龄：" + this.age +"岁、狗狗原产地：" + from;
    };
}


public class staticDemo{
    public static void main(String argc[] ){
        Dog myDog = new Dog();
        myDog.setDog("阿飞","黑白", 3);
        Dog.setFrom("德国");

        System.out.println("\n"+myDog.getInfo()+"\n");
    
    };
};
```


## 代码块

1. 在Java中，{ ... } 所框定的区域称为一个代码块。
1. 有：普通代码块、构造代码块、静态代码块和同步代码块。
1. 普通代码块，是定义在一个方法中的代码块。同一名称的变量不能在同一个方法中存在的，会发生命名冲突。通过增加 { } 可以划出一个普通代码块，避免变量冲突。在一个方法代码很长的情况下可以使用这个技巧避免变量命令冲突。
1. 构造代码块，是定义在一个类中的代码块，就是在一个类中用 { }划定一段代码形成的代码块。每次实例化对象是构造块会被调用执行，而且构造块会优先于构造方法执行。
1. 静态代码块，是用 static 修饰的代码块，主要是用于为static属性初始化。静态代码块会在对象实例化时最先执行，甚至优先于构造代码块。无论类进行几次对象实例化操作，静态代码块只会执行一次。因为主要作用是为类的static静态属性初始化，因此也只需要执行一次。
1. 主类中的静态代码块，会优先于主方法 main() 执行，这就给主方法执行前初始化操作提供了时机。

## 数组

1. 动态初始化。因为数组是参考类型，所以要使用 new 来在堆内存开辟存储空间。如，`int salary [] = new int[10];` 
1. 静态初始化。等同于用 new 在堆内存中开辟存储空间，并完成初始化赋值。如，`int salary [] = {5000, 6000, 4500};` 或者使用完整格式： ` int salary [] = new int [] {5000, 6000, 4500}`
1. 用 for 循环处理数组是最常用的方式。如，` for(int i = 0, i < salary.length, i++){salary[i] = salary[i] + 300};`
1. 引用传递。既然是参考类型，就可以发生引用传递，也就是堆内存的内容可以被多个栈内存指针指向引用。
1. foreach循环。一种不用 Index 来循环获取每一个数组元素值的方便形式。比如，` for(int temp : salary){};`

## 多维数组

1. 多维数组最好的理解形式并不是多维表，而是多层属性结构。比如，对 myArray[x][y][z] 来说，x 就是myArray 这棵树的第一层分支个数，y 就是第二层分支个数，如此类推。这种理解很容易设计 for 循环。
1. 代码实例：

```java
public class Array{  // 主类
    public static void main(String args[]) { // 主方法
        
        int myArray[][][] = new int [][][] {
            {
                {111, 112, 113, 114},
                {121,122,123,124},
                {231,232,233,234}
            } ,
            {
                {211,212,213,214},
                {221,222,223,224},
                {231,232,233,234}
            } 
         } ;

        for(int i = 0; i < myArray.length; i++) {
            for(int j = 0; j < myArray[i].length; j++){
                for(int k = 0;k < myArray[i][j].length; k++){

                    System.out.println("第" + i + "_"+j +"_"+ k +"层: " + myArray[i][j][k]);
                }
            }
        } 

    }

}
```