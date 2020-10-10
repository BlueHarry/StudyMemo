# 阿里云Java课程学习笔记

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