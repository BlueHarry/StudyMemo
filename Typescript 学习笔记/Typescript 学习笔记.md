# Typescript 学习笔记



## 环境安装

1. 安装 Typescript 编译器 tsc

```shell
% sudo npm install -g tsc       

npm WARN deprecated tsc@1.20150623.0: you probably meant to install typescript
/usr/local/bin/tsserver -> /usr/local/lib/node_modules/tsc/bin/tsserver
/usr/local/bin/tsc -> /usr/local/lib/node_modules/tsc/bin/tsc
```

上图：从输出信息 /usr/local/bin/tsc -> /usr/local/lib/node_modules/tsc/bin/tsc 可以看出来，npm安装的组件都放在/usr/local/lib/node_modules/目录下。然后创建一个 link 指向tsc，就不用修改 PATH 环境参数了，因为 /usr/local/bin/ 缺省已经包含在PATH里面了，这个方式挺聪明的。

2. 安装VSCode之类的代码编辑器。



## 数据类型



类型声明（Type assertion）方式，采用‘ ：’ 后置，看起来比较现代比较酷。

```typescript
let name:string;

let result;
let outputBig = (<string>result).big();
let outputBlink = (result as string).blink();
```

上方代码：如果像变量`result`没有声明数据类型，TS编译器就会自动设置为 `any` 类型。在使用过程中需要做强制性类型转换时，可以用 `<>`和`a`s 两种方式。



## => 函数



在其它的语言中有的叫做 Lambda表达式（Lambda express），在TS中叫做箭头函数（Arrow function）。叫lambda表达式在使用时似乎比较容易理解，因为可以把整个函数看成一个表达式，放进任何地方，比如作为其它函数的参数。

```typescript
let saySomething = function(words){
  console.log(words);
}

let saySomething = (words) => {
	console.log(words）
}；

let saySomething = (words) => console.log(words）； 

let saySomething = words => console.log(words）；                  
```

上方代码：以上代码中几个函数是等价的。

