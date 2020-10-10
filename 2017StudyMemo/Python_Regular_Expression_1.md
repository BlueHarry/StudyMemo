
__知识点__
- RE 模块基本功能
- RE 基本函数

***
# @ 废话连篇

BRE: Basic Regular Expression  
ERE: Extension Regular Expression  


# @ 基本 RE 函数  

【 在这里讲几句废话 】

### $) 查找匹配: re.search(), re.match(), re.findall()

- re.search(pattern, string[, flags])   
- re.match(pattern, string[, flags])    
- re.findall(pattern, string)

这几个函数是用于查找一个字符串（第二个参数string）里，有没有匹配（occurrence）给定的正则表达式模式（RE pattern）的部分?  
或者按照英语原文的表述习惯这么来说，看看给定的正则表达式模式（第一个参数给出的 RE pattern）有没有在第二个参数 String 字符串中出现（occurrence）?  

#### re.search()  
- re.search(pattern, string[, flags])

re.search() 返回一个 Match Object对象，可以简单地理解为返回布尔值（boolean value）：生存或是毁灭，True 或是 None

```
>>> import re
>>> if re.search('[g][o]+','good morning, google! let\'s go!'):
...     print('found it!')
...
found it!

```

#### re.match()  
- re.match(pattern, string[, flags])

re.match()和re.search()类似，只是它只有在字符串的开头匹配给定 RE pattern 时才返回 True  
这个有时很有用，比如，查找以 ‘#’ 开头的注释行就很方便

```
>>> if re.search('[o]+','good morning, google! let\'s go!'):
...     print('found it!')
...
found it!
>>> if re.match('[o]+','good morning, google! let\'s go!'):
...     print('found it!')
... else:
...     print('didn\'t find it!')
...
didn't find it!

```
那我们把正则模式 RE pattern 改一下试试

```
>>> if re.match('[g][o]+','good morning, google! let\'s go!'):
...     print('found it!')
...
found it!
```
因为字符串的开头匹配了 ‘[g][o]+’  

#### re.findall()   
- re.findall(pattern, string)

re.search() 只要找到第一处匹配就返回 True  
而 re.findall() 会查找所有匹配的地方，然后返回一个包含所有匹配部分的 list 列表  

```
>>> re.findall('[g][o]+','good morning, google! let\'s go!')
['goo', 'goo', 'go']
```
***

### $) re.split()    

#### re.split()
- re.split(pattern, string[, maxsplit=0])

re.split() 函数是用特定的正则模式 RE pattern （通常是，冒号 [:]、逗号[,] 、空格[]、星号[\*]、等等 ）把字符串切割开，返回切开后的子字符串构成的 list 列表。  
不用说，这个函数很有用，我们经常需要这么干。比如，把 /etc/passwd 文件解构开，下面的例子就是取了 passwd 文件的一行。   


```
>>> import re
>>> re.split('[:]',r'daemon:*:1:1:System Services:/var/root:/usr/bin/false')
['daemon', '*', '1', '1', 'System Services', '/var/root', '/usr/bin/false']
```
第3个参数是指定切割多少次后，就提前下班，草草收工  


```
re.split('[:]',r'daemon:*:1:1:System Services:/var/root:/usr/bin/false',4)
['daemon', '*', '1', '1', 'System Services:/var/root:/usr/bin/false']

```

### $) 两个小工具 re.compile() 和 re.escape()

做一些预处理的准备工作  

#### re.compile()  
- compile(pattern[, flags])

创建一个 RE pattern 对象   
这个好像没什么用，因为用 “=” 赋值语句不是可以实现同样的功能吗？ 比如， rePattern = '[a-z][0-9]+'  

【以后再研究】  

#### re.escape()
- re.escape(string)  

对一个含有大量 RE 特殊字符的字符串进行预处理，免得自己添加 N 多 backslash '\'  
这玩意儿好像有点儿用，即便不在代码中使用，有时我们不知道该在哪儿加 ‘\’ 时，用它来 escape 一下就搞定了。  

```
>>> re.escape('Please find the code in http://github.com/pypa')
'Please\\ find\\ the\\ code\\ in\\ http\\:\\/\\/github\\.com\\/pypa'

```
### $) 烧脑神器 MatchObject.group() 和 re.sub()
- MatchObject.group([group1, ...])
- sub(pat, repl, string[, count=0])

#### MatchObject.group() 和 括号 ‘（ ）’

在研究 re.match() 函数的时候，我们说可以简单地把它的返回值理解为一个布尔值，那确实是一种不精确的简单化说法。因为它实际返回的是一个 Match Object 对象，包含了更丰富的信息，不只是 True 和 None 这么简单。  
实际上，在返回的 Match Object 对象中存储了匹配成功的子字符串（因为re.match()函数从头开始匹配，所以这个子字符串就是原字符串从开头到中间某个点切断下来的一段）。  
在 RE pattern 中加上括号后，Match Object 对象还会把匹配子字符串再切断分组存储下来，供 group（）函数访问。  

```
>>> MatchObject = re.match(r'.{4}(github)\.((.{2})..(.{3}))','www.github.com/pypa/pip')
>>> MatchObject.group(0)
'www.github.com/pyp'
>>> MatchObject.group(1)
'github'
>>> MatchObject.group(2)
'com/pyp'
>>> MatchObject.group(3)
'co'
>>> MatchObject.group(4)
'pyp'

```



#### re.sub()


sub 是指 substitute 替换，咋一看容易误以为是 substring “子”字符串  
它是要在第3个参数字符串 string 中，找到匹配第一个参数正则模式 pat 的地方，用第2个参数 repl 去替换   
神奇的是 repl 不仅可以是字符串，可以是一个函数  

```
>>> re.sub(r'(.{6})Huang',r'\1Hung','Harry Huang,Jerry Huang,Jason Huang')
'Harry Hung,Jerry Hung,Jason Hung'

```


### $) 典型正则模式 typical RE pattern

#### 点号'.' 和 '\*'、'?'、'+'

点号'.' 是： 通配符 wildcards   
'\*'、'?'、'+' 是： 重复算符 repetition operators

Wildcard 这个词显然来源于纸牌游戏中的“百当牌”，就是那一张（或几张）你想把它当成什么都行的牌，凑个炸弹或是同花顺什么的。那张牌就叫 wild card，够野！  
在 UNIX \ LINUX 的shell中，* 和 ？ 是常用的通配符  
而在 RE 中通配符是点号 '.'，‘\*’和'?' 成了重复算符  

r'\*[.]+\*' : 夹在两个星号之间的子字符串，比如，\*Python\* 和  \*Python\*Java\*   
r'\*[^\*]+\*' : 夹在两个星号之间的,里面不含有星号的，子字符串。\*Python\*Java\* 不匹配  
