# Odoo 学习笔记

# 开发

## 安装开发环境

1. 在一台专用的Mac上安装开发环境，参照odoo文档的 source install 指南

2. Mac OS上安装一直都不顺利，所以在Mac OS上装 Multipass 模拟Ubuntu 环境

3. 安装前，要确认 python3的版本最新。

4. ```sh
   sudo apt upgrade python3
   ```

5. 安装Python3的相关依赖包：

   ```sh
   sudo apt install python3-pip python3-dev libxml2-dev libxslt1-dev libldap2-dev libsasl2-dev libssl-dev libpq-dev libjpeg-dev
   # 这是odoo文档上的命令，会报错 python3-pip 装不上
   ```


6. 要用这条命令单独装：

   ```sh
   	sudo python3 -m easy_install install pip
   ```

   

7. 这样就可以用 pip3 来安装odoo的依赖包了

	 ```sh
   pip3 install -r requirements.txt
   
   # 报错 psycopg2 装不上 
   ```
   
8. 使用一下命令单独安装：

	```sh
   sudo apt-get install python3-psycopg2
   # ubuntu 上只会装上 2.8.4-2
   ```

9. 安装支持PDF的包

	 ```sh
     cd /tmp/
    $ sudo wget https://github.com/wkhtmltopdf/wkhtmltopdf/releases/download/0.12.5/wkhtmltox_0.12.5-1.focal_amd64.deb
    $ sudo gdebi --n wkhtmltox_0.12.5-1.focal_amd64.deb
    $ sudo ln -s /usr/local/bin/wkhtmltopdf /usr/bin
    $ sudo ln -s /usr/local/bin/wkhtmltoimage /usr/bin
    
    # 通常会无情地报错，找不到 gdebi
    ```

10. 要先装 gdebi 包管理小工具

	```sh
    # 比较复杂，分3步：
    # Activate the universe repository:
    sudo add-apt-repository universe
    
    # Update the package list: 这一步很重要，否则可能找不到需要的包，因为包list不是最新的
    sudo apt-get update
    
    # Install gdebi-core:
    sudo apt-get install gdebi-core
    ```



## 启动odoo

1. 命令行启动是这样的：

```sh
./odoo-bin --addons-path="addons/,../enterprise/" -d rd-demo
```

2. 大部分的启动选项可以通过配置文件加载

   ```python
   # 配置文件 config/odoo.conf 如下
   [options]
   addons_path = addons,../enterprise
   
   ```

   就可以这样启动了：

   ```sh
   ./odoo-bin -c config/odoo.conf -d rd-demo
   ```

   

   ## 管理Database

   管理页面URL：192.168.64.2:8069/web/database/manager

   



# 业务功能模块




## 权限管理 Access Rights

- 安装一个App时，系统会自动创建一些用户组（user group），用于控制用户访问权限。比如， sales/ user: all documents
- 创建一个user用户时，加入到这些用户组，就获得了对这个App应用的相应访问权限。
- 所以，对一个app应用的访问权限设置是从这个应用的用户组设置开始的，接着把user加入这些用户组就可以了。而且，我们不是只能使用这些由系统自动创建的 user group，我们可以根据权限管理需要自己创建独特的user group。
- 但是，自己创建一个用户组，设置其特殊权限是一件很繁琐的事情。涉及到哪些功能菜单Menu有权限、哪些Views视图有权限、对里面各种Model对象（比如，合同）的CRUD权限、对哪些Model的哪些记录的权限（record rules），需要花很多精力来设计和设置。
- 用户组之间存在继承关系。越在金字塔顶端的用户组其权限越小，odoo用户组金字塔最顶端的group是 internal user，几乎就没啥权限可言。



## 财经模块

### VAT增值税计算和收取方式

	- 开发票（Invoicing）的时候，卖方（seller、vender）代表政府向客户（buyer、customer）收VAT税（Taxes Collection），然后上交政府。
	- 收取额度 = 产品价值总额 X 税率，比如：1000 * 17%。开发票收取VAT税的时候，不计算是否属于增值部分的，是全额计税，叫做 Output Tax。
	- 体现仅增值部分收税的方法：Output Tax - Input Tax。其中Input Tax是商家购买原材料向上游Vender所付出的VAT，商家交税取得的增值税发票用于冲抵。通过这个机制就体现了“仅对增值部分交税”的增值税原则。

### Odoo 的VAT配置

1. 创建不同国家、不同商品的VAT费率规则
2. 将相应的VAT费率绑定到每一件可销售的商品上
3. 开发票时Odoo自动算出每件商品应交增值税

## Fiscal position

- 可以翻译成财务区域，就是不同国家和地区相关的税收规则。比如，欧盟地区的零关税之类的。
- 在odoo上，这个规则是优先于产品绑定的税收规则的，也就是会覆盖产品默认税率。
- 

## Manufacturing and PLM
-  Sebastian Sagramoso 他在youtube 上有一个频道，以讲解制造模块为主。比如， Odoo Manufacturing and PLM 

- Odoo MRP v14: Manufacturing Reinvented，youtube Odoo 频道



## ERP友商研究



### Top 10 小企业ERP

1. Netsuite
2. Microsoft D365 Business centre
3. Odoo
4. Sage
5. Priority Software (Israel)
6. SAP business One
7. QuickBooks Enterprise
8. Epicor
9. salesforce
10. Beacom



### Top 10 ERP

1. Netsuite
2. Microsoft D365
3. Oracle
4. IFS (Europe-oil, construction,etc)
5. Sage
6. SAP S/4 HANA
7. Salesforce
8. Infor (Cloud)
9. Workday (HR)
10. Service Now (service)

## 