# free_generator

a free code-generator for spring MVC and MyBatis.
automatic create dao/model/mapper.
you can custom everything you need

## HOW TO BEGIN
``` bash
git clone xxxxx
```
``` bash
cd free_generator
mvn clean package
```
after build success,then:

``` bash
cd target
```
then vim yourself `config.properties` follow next;

``` bash
cd free_generator-release
java -jar free_generator.jar
```
then here come files now!

## CONFIG.PROPERTIES

**`config.properties`** the mainly config file

``` bash
config.properties:
------------------

### some default config,most of time you don't need to change this,however,except you really need.

#default use template,support model/dao/mapper now,remove anyone if you don't need
templates=model,dao,mapper

#default file suffix,don't add fileType here
model_suffix=               //the suffix of model,default null
mapper_xml_suffix=Mapper    //the suffix of xml,default Mapper,for e.g.,StudentMapper
dao_suffix=Dao              //the suffix of dao,default Dao,for e.g.,StudentDao, someone maybe still need Mapper

#use lombok or not
is_lombok=false             //if use lombok,default false

#is hump name style
is_hump_model_class=true    //is a hump name stylefor model class
is_hump_column=true         //is a hump name style for column in model

#is there a init sql in dao and xml,do it for get all columns as a string show.
is_init_query=true          //is a default query in xxxDao and xxMapper
init_query_name=select      //method name in xxxDao

#default capacity of ByteBuffer.allocate,change bigger if throw a nio Exception
buffer_capacity=20480

--------------------

##### write
##### your
##### config
##### next

#show in comment
author=lousama      //show in comment

# model/mapper/mapperXml/dao package path
# is wrapper class in model,example:Integer/int,default false
is_wrapper=false    //is wrapper class type to modified column in model,e.g.,Integer/Long,or int/long
# prefix of package path
package_prefix=com.lousama.generator    //prefix of the package

# package_path = {package_prefix} + next value,set {package_prefix} null if you wanna define next
package_model=model         //last package name of class
package_mapper_xml=mapper
package_dao=dao

# jdbc info,support mysql and oracle now
jdbc_database=mysql         //mysql or oracle
jdbc_url=localhost:3306/config      //ip:port/dbname
jdbc_username=root
jdbc_password=root
jdbc_table=     //table schema you want to generator,get all tables if be null

```
## CUSTOM-MADE MODEL
this project use velocity to create file,you can use custom model if you need.

the params to velocity is `List<Packages>`,`Packages` for next:

``` java
    private String dao;  //package path of dao ,eg:com.lousama.generator.dao
    private String model;   //same as above
    private String mapperXml;   //same as above

    private String daoName; //name of dao,eg:StudentDao
    private String modelName;   //same as above
    private String mapperXmlName;   //same as above

    private String tableName;   //name of table in database,all words upper

    private String isInitQuery = ResourceUtil.getString("is_init_query");
    private String initQuery = ResourceUtil.getString("init_query_name");
    private String author = ResourceUtil.getString("author");
    private String isLombok = ResourceUtil.getString("is_lombok");

    private String initSql; //init sql in mapper.xml,eg:select id,name from table

    private String pkCondition; //priamry key condition in sql in mapper.xml,eg:id1=#{id1} and id2=#{id2}

    private List<Column> columnList;    // all columns in tableName above

    private Set<String> importSet;  //import package set in model class
```
now next `Column` here:
``` java
    private String className;   //the class type of column eg:String,int,BigDecimal
    private String importClass; //some class type must import before class,eg:java.math.BigDecimal
    private String dbColName;   //the real column name in database,if ${is_hump_column}=false,it will same as colName next;
    private String colName; //column name in model class file
    private String getMethod;   //getMethod in model class file
    private String setMethod;   //setMethod in model class file
    private int colSize;    //size of column in databese
    private int scale;  //scale of column indatabase,if>0,numberic will parse BigDecimal
    private int isPk;   //if this column is primary key
```
if you understand grammar,you can got your custom xxx.vm file whatever you want


## SUPPORT COLUMN TYPE

the next database column type can be parse correctly
- int/integer/smallint/tinyint
- bigint/long
- varchar/varchar2
- number/numeric/decimal/double/float
- timestamp/datetime/date

##TIPS

 Better not connect database on product enviroment!

## UPDATE LOG

### 2015-05-20
    v1.1:
    - add analysis for primary key in mapper.xml---resultMap node
    - Packages add a attribute:
        - pkCondition: priamry key condition in sql in mapper.xml,e.g.:id1=#{id1} and id2=#{id2}
    - add more comment






