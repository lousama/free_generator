# free_generator

free_generator是一款简单,配置灵活的代码生成器,主要用于
生成spring MVC + MyBatis框架用的model/dao/mapper类。
你可以任意配置各种参数来满足不同的要求,甚至可以自定义模版来实现自己想要的效果


## 开始
``` bash
git clone xxxxx
```
``` bash
cd free_generator
mvn clean package
```
等打包完后:

``` bash
cd target
```
配置文件 `config.properties` 打包在了target目录,

``` bash
cd free_generator-release
java -jar free_generator.jar
```
文件生成在free_generator-release目录下

## 配置

**`config.properties`** 是主要的配置文件:

``` bash
config.properties:
------------------

### some default config,most of time you don't need to change this,however,except you really need.
##下面是一些默认的系统配置,大多时候你不需要修改,除非你知道这是作甚

#default use template,support model/dao/mapper now,remove anyone if you don't need
templates=model,dao,mapper  //默认模版名,目前支持model,dao和mapper,可以任意修改,不能为空,自定义的模版名称前缀与默认一致


#use lombok or not
is_lombok=false             //是否使用了lombok,默认不启用

#is hump name style
# 驼峰命名根据下划线后一位大写转换
is_hump_model_class=true    //model类名是否是驼峰格式
is_hump_column=true         //model下属性名是否是驼峰格式

#is there a init sql in dao and xml,do it for get all columns as a string show.
is_init_query=true          //mapper中是否有一条默认查询
init_query_name=select      //mapper中默认查询的名字

# ByteBuffer.allocate默认分配内存,报nio Exception的话可以尝试增大这个值
buffer_capacity=20480

--------------------

##### write
##### your
##### config
##### next

#show in comment
author=lousama      //作者,会出现在注释里

# model/mapper/mapperXml/dao package path
# is wrapper class in model,example:Integer/int,default false
is_wrapper=false    //model类中字段类型是否用包装器类型,如Integer/int,Long/long
# prefix of package path
package_prefix=com.lousama.generator    //包名的前缀

# package_path = {package_prefix} + next value,set {package_prefix} null if you wanna define next
# 包名的后缀路径,完整的包名等于package_prefix + 后缀路径
package_model=model
package_mapper_xml=mapper
package_dao=dao

#default file suffix,don't add fileType here
# 默认的文件前缀,不需要加文件类型,如.java,.xml等
model_suffix=               //model的后缀,这个一般默认空,会直接取表名
mapper_xml_suffix=Mapper    //mapper.xml文件后缀
dao_suffix=Dao              //dao文件后缀,有些人喜欢用Mapper

# jdbc info,support mysql and oracle now
jdbc_database=mysql         //数据库类型,目前支持mysql和oracle
jdbc_url=localhost:3306/config      //数据库连接url,格式为  ip:port/数据库实例
jdbc_username=root
jdbc_password=root
jdbc_table=     //要生成文件的表名,为空的话会取实例下所有的表

```

## 支持的字段类型

暂时支持下面这些表字段类型:
- int/integer/smallint/tinyint
- bigint/long
- varchar/varchar2
- number/numeric/decimal/double/float
- timestamp/datetime/date

## 提示

建议不要连接生产环境的数据库

如果你有更棒的建议,请提issues或者给我邮件`me@lousama.com`




