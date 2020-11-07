# Typescript 学习笔记

# Typescript essential

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

## 创建一个Javascript 应用包

进入将要放置package的根目录 ，运行 ：

```shell
npm init --yes

# npm 就会创建一个package.json 包文件
```

可见，npm 是Javascript 开发项目重要的效率工具，虽然这些事情都能手工完成。



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



# Jest 测试工具



测试工具3组件：

- Test Runner : 比如，Mocha
- Assertion Library : 比如，Chai
- Headless Browser ; 比如，Puppeteer，浏览器交互操作模仿器，主要是用于端到端（e2e）测试

Jest = { Test Runner + Assertion Library }  for Javascript ，主要用于Javascript 单元测试和集成测试。

## 安装 jest 开发测试环境

```shell
npm install --save-dev jest
```

jest官网：https://jestjs.io

找到Docs，可以按照 Getting started 指引，学会安装和编写第一个jest测试文件。

安装完之后，要把 package.json 中的 test 工具设置为 jest：

```javascript
{
  "scripts":{
    "test":"jest"
  }
}
```



## 第一个test.js 或者 spec.js 

官网的例子sum.js 文件是这样的：

```javascript
function sum(a,b){
    return a + b;
}
module.exports = sum;
```



而 sum.test.js 或者 sum.spec.js文件是这样的：

``` javascript
const sum = require('./sum');

test('adds 1 + 2', () => {
    expect(sum(1,2)).toBe(3);
});
```



## 运行测试文件

运行命令官网说是 npm run test，实际上好像 npm test 才对：

``` shell
zsh.$ npm test

# 这个命令将运行所有 test.js 和 spec.js 文件
# 通常运行成功后的输出如下：
> jest-learn@1.0.0 test /Users/tony/jProject/jest-learn
> jest

 PASS  ./sum.test.js
  ✓ adds 1 + 2 (1 ms)

Test Suites: 1 passed, 1 total
Tests:       1 passed, 1 total
Snapshots:   0 total
Time:        0.761 s, estimated 1 s
Ran all test suites.
```

也可以只运行其中一个 test,js 或 spec.ts 文件：

``` shell
zsh.$ npm test -t sum.spec.js
```

