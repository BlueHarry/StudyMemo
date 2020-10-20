# 阿里云Java课程

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