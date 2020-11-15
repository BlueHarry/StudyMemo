# Mongodb 学习笔记



学习资源：

- Mongodb 官方免费课程 -- http://university.mongodb.com  。通过Google账号登录，即可免费学习。但视频课程使用的是youtube，所以必须到墙外才能看。
- 菜鸟教程的 Mongodb课程



## MongoDB Shell 基本命令

1. 用 URI String 连接服务器

   ```shell
   > mongodb://admin:123456@mongodb_server.3jschool.com/
   
   # 以 { 用户名 : admin , 密码 ：123456 } 登录 laocalhost 上的 Mongodb
   # 一个比较容易记忆的类比是，通过特定通道（URI）进入了一栋档案大楼，localhost 或者是 mongodb_server.3jschool.com 就是大楼的地址。当然，进入大楼需要用户名和密码。
   ```

2. 进入某个db，看看有哪些 collections， 然后查询一个 document

   ```shell
   > show dbs
   # 看看我们连接的 Mongodb 服务器上有哪些 db
   # 看看我们进入的这栋档案大楼里有多少个房间（db）
   
   > use onlineschool_db
   # 进入数据库 onlineschool_db
   # 进入一个房间
   
   > show collections
   # 看看这个db 里面有哪些 collections
   # 看看这个房间（db）里，有哪些文件柜
   
   > db.students.find({"name":"Jason"})
   # 在 students 这个 collection 里面查找 name 为 Jason 的学生档案document
   # 在 学生（students）这个文件柜里，查找 name 为 Jason 的学生档案（document）
   
   > it
   # 我们发现有超过一屏（20个 document）涉及到Jason，于是用it （iterate）显示下一屏
   # 不是 more 哦 ！
   
   > db.students.find({"name":"Jason"}).count()
   # 我们发现N多屏显示不完，很想知道到底有多少 document 中name为Jason的
   # 于是用 .count() 命令
   ```

   


3. 用 MQL （Mongodb Query Language）来查询出文档 

```shell
db.grades.find({"$and": [{"student_id":{"$gt":25}},{"student_id":{"$lt":100}}]})

# 或者，更简洁的方式：

db.grades.find({"student_id": {"$gt":25,"$lt":100}})

```

- db ： 代表当前 use 的 database
- grades ：代表当前db里面的 grades 集合（collection）
- "$gt"：是一个比较运算符
- "$and"：是一个逻辑运算符



4. Aggregation syntax 

```shell
{"$expr": {
					"$and": [
									{"$gt":["$tripduration", 1200]},
									{"$eq":["$end station id", "$start station id"]}
									]
					}

}

```

这是Mongodb 官方培训课程的一个查询例子，看明白了基本就算会了。

- "$tripduration"：代表文档中Field tripduration的值
- "$expr"：统领一个查询条件表达式