# Odoo 学习笔记

# 安装开发环境





# 业务功能模块





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