# ElasticSpring
The purpose of ElasticSpring is to implement elasticsearch functions using Spring. 


# Preparations
## Basic Softwares
In order to make everything run as expected, you need intall the softwares in the URL:http://www.cnblogs.com//hts-technology/p/8477258.html
## Basic Plugins
plugin installation:https://github.com/medcl/elasticsearch-analysis-ik
## Basic Knowledge
* about maven: https://spring.io/guides/gs/maven/

      Maven配置文件pom.xml中<scope>属性的取值及其含义：
      * compile(默认值)：依赖包在编译时需要就绪
      * Provided：编译时要求的依赖包尽在容器运行代码时需要就绪；
      * Test：编译和运行测试代码时依赖包就绪，运行时不需要；
* about mapping:https://blog.csdn.net/napoay/article/details/73100110?locationNum=9&fps=1#324-termvector
mapping用于创建type(类似于MySQL中的表格)，针对type的每一项都可以设置相应的属性，这里重点来看看
analyzer和search_analyzer两个属性。
# Mapping
mapping用于确定document（记录）及其所包含的field（域）的存储和索引方式。比如，mapping用于确定
以下内容：

* 需要以全文本域来处理的字符串域；
* 域的类型：数字，日期和地理位置；
* document中哪些域值应该以catch-all形式进行索引；
* 日期值的格式；
* 支持自定义规则用来控制动态添加域的mapping。

mapping types在ES6.0之后弃用，从此，所有的索引只有一种mapping类型。<b>事实上，大家关于ES普遍的共识"
type相当于关系数据中的表格"是错误的。</b>

Analysis
Analysis将text（文本），比如邮件的内容，转换为tokens或者terms，然后添加到倒排索引中用于检索。
Analysis的工作由分析器（analyzer）来完成，分析器可以使用系统自带的也可以由用户自定义。

search analysis
search analysis 将用户输入的搜索文本进行分词。
* about markdown: https://www.cnblogs.com/liugang-vip/p/6337580.html

## Article-Structure
引题
标题
副题
作者
责编
版面
分类
日期
体裁
正文

## about usage of Head plugin in ES
modify the build mapping element properties:
http://localhost:9200/papers/article/_mapping?pretty

```
{
	"article":{
		"properties":{
			"正文": {
				"type": "text",
				"term_vector": "with_positions_offsets",
				"analyzer": "ik_max_word",
				"search_analyzer": "ik_smart"
					}
			}
		}
}
```

normal query
http://localhost:9200/papers/_search/  POST
```aidl
{"query":{"match":{"articleText":"十二届"}}}
```

{
"query":{
"bool":{
"must":[
{"match":{"articleText":"十二届"}},
{"match":{"authorsName":"代烽"}}
]
}
},
"from":0,
"size":1000
}
test the analyzer
http://localhost:9200/_analyze

```aidl
{"analyzer":"ik_smart","text":"有为有守方能合格 ■米斌斌 “四讲四有”是合格党员的标准"}
```

https://www.cnblogs.com/LittleFeiHu/p/6664575.html?utm_source=itdadao&utm_medium=referral