# <a id="a_top">MyBatis：对象关系映射框架</a>
[MyBatis3-官网](http://www.mybatis.org/mybatis-3/zh/index.html)  
[MyBatis-Github](https://github.com/mybatis)  
[org.mybatis:mybatis:3.4.6](https://search.maven.org/artifact/org.mybatis/mybatis/3.4.6/jar)  
[org.mybatis.spring.boot:mybatis-spring-boot-starter:1.3.2](https://search.maven.org/search?q=g:org.mybatis.spring.boot)  

|作者|Mutistic|
|---|---|
|邮箱|mutistic@qq.com|

---
### <a id="a_catalogue">目录</a>：
1. <a href="#a_mybatis">MyBatis：对象关系映射框架</a>
2. <a href="#a_xml">MyBatis相关配置</a>
3. <a href="#a_insert">MyBatis新增数据</a>
4. <a href="#a_select">MyBatis查询数据</a>
5. <a href="#a_update">MyBatis修改数据</a>
6. <a href="#a_delete">MyBatis删除数据</a>
7. <a href="#a_one">MyBatis映射关系：一对一</a>
8. <a href="#a_more">MyBatis映射关系：一对多</a>
98. <a href="#a_notes">Notes</a>
99. <a href="#a_down">down</a>


---
### <a id="a_mybatis">一、MyBatis：对象关系映射框架：</a> <a href="#a_catalogue">last</a> <a href="#a_xml">next</a>
一、什么是MyBatis：
```
  MyBatis 本是apache的一个开源项目iBatis, 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。2013年11月迁移到Github。

  iBATIS一词来源于“internet”和“abatis”的组合，是一个基于Java的持久层框架。iBATIS提供的持久层框架包括SQL Maps和Data Access Objects（DAOs）
```
二、基本信息：
```
  MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。

  MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Ordinary Java Object,普通的 Java对象)映射成数据库中的记录
```
三、背景：
```
  MyBatis是支持普通 SQL查询，存储过程和高级映射的优秀持久层框架。MyBatis 消除了几乎所有的JDBC代码和参数的手工设置以及结果集的检索。

  MyBatis 使用简单的 XML或注解用于配置和原始映射，将接口和 Java 的POJOs（Plain Ordinary Java Objects，普通的 Java对象）映射成数据库中的记录。
  
  每个MyBatis应用程序主要都是使用SqlSessionFactory实例的，一个SqlSessionFactory实例可以通过SqlSessionFactoryBuilder获得。
SqlSessionFactoryBuilder可以从一个xml配置文件或者一个预定义的配置类的实例获得。

  用xml文件构建SqlSessionFactory实例是非常简单的事情。推荐在这个配置中使用类路径资源（classpath resource)，
但你可以使用任何Reader实例，包括用文件路径或file://开头的url创建的实例。MyBatis有一个实用类----Resources，可以方便地从类路径及其它位置加载资源
```
四、特点：
```
  简单易学：本身就很小且简单。没有任何第三方依赖，最简单安装只要两个jar文件+配置几个sql映射文件易于学习，易于使用，通过文档和源代码，可以比较完全的掌握它的设计思路和实现。

  灵活：mybatis不会对应用程序或者数据库的现有设计强加任何影响。 sql写在xml里，便于统一管理和优化。通过sql语句可以满足操作数据库的所有需求。

  解除sql与程序代码的耦合：通过提供DAO层，将业务逻辑和数据访问逻辑分离，使系统的设计更清晰，更易维护，更易单元测试。sql和代码的分离，提高了可维护性。

  提供映射标签，支持对象与数据库的orm字段关系映射

  提供对象关系映射标签，支持对象关系组建维护
  
  提供xml标签，支持编写动态sql。
```
五、功能架构：
```
  API接口层：提供给外部使用的接口API，开发人员通过这些本地API来操纵数据库。接口层一接收到调用请求就会调用数据处理层来完成具体的数据处理。
  
  数据处理层：负责具体的SQL查找、SQL解析、SQL执行和执行结果映射处理等。它主要的目的是根据调用的请求完成一次数据库操作。
  
  基础支撑层：负责最基础的功能支撑，包括连接管理、事务管理、配置加载和缓存处理，这些都是共用的东西，将他们抽取出来作为最基础的组件。为上层的数据处理层提供最基础的支撑
```
六、框架架构：
```
  加载配置：配置来源于两个地方，一处是配置文件，一处是Java代码的注解，将SQL的配置信息加载成为一个个MappedStatement对象
（包括了传入参数映射配置、执行的SQL语句、结果映射配置），存储在内存中。
  
  SQL解析：当API接口层接收到调用请求时，会接收到传入SQL的ID和传入对象（可以是Map、JavaBean或者基本数据类型），
Mybatis会根据SQL的ID找到对应的MappedStatement，然后根据传入参数对象对MappedStatement进行解析，解析后可以得到最终要执行的SQL语句和参数。

  SQL执行：将最终得到的SQL和参数拿到数据库进行执行，得到操作数据库的结果。

  结果映射：将操作数据库的结果按照映射的配置进行转换，可以转换成HashMap、JavaBean或者基本数据类型，并将最终结果返回
```
七、动态SQL：
```
  MyBatis 最强大的特性之一就是它的动态语句功能。如果您以前有使用JDBC或者类似框架的经历，您就会明白把SQL语句条件连接在一起是多么的痛苦，
要确保不能忘记空格或者不要在columns列后面省略一个逗号等。动态语句能够完全解决掉这些痛苦。
  
  尽管与动态SQL一起工作不是在开一个party，但是MyBatis确实能通过在任何映射SQL语句中使用强大的动态SQL来改进这些状况。
动态SQL元素对于任何使用过JSTL或者类似于XML之类的文本处理器的人来说，都是非常熟悉的。在上一版本中，需要了解和学习非常多的元素，
但在MyBatis 3 中有了许多的改进，现在只剩下差不多二分之一的元素。MyBatis使用了基于强大的OGNL表达式来消除了大部分元素
```

---
### <a id="a_xml">二、MyBatis相关配置：</a> <a href="#a_mybatis">last</a> <a href="#a_insert">next</a>
jdbc.properties：
```properties
## jdbc驱动：使用mysql驱动
jdbc.driverClassName=com.mysql.jdbc.Driver
##  jdbc url
jdbc.url=jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false
##  jdbc 用户名
jdbc.username=root
##  jdbc 密码
jdbc.password=root
```
mybatis-config.xml：
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!-- Mybatis 3 configuration DTD规范 -->
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">

<!-- configuration：配置文件的根标签 -->
<configuration>
  <!-- Mybatis配置文件参考：http://www.mybatis.org/mybatis-3/zh/configuration.html -->

  <!-- properties：属性都是可外部配置且可动态替换的，既可以在典型的 Java 属性文件中配置，亦可通过 properties 元素的子元素来传递 -->
  <properties
    resource="com/mutistic/mybatis/java/resources/jdbc.properties">
    <!-- <property name="username" value="dev_user" /> -->
  </properties>

  <!-- typeAliases：类型别名是为 Java 类型设置一个短的名字。它只和 XML 配置有关，存在的意义仅在于用来减少类完全限定名的冗余 -->
  <typeAliases>
    <typeAlias alias="BizBuyAddress"
      type="com.mutistic.mybatis.java.model.BizBuyAddress" />
  </typeAliases>

  <!-- environments：配置环境，MyBatis 可以配置成适应多种环境，这种机制有助于将 SQL 映射应用于多种数据库之中 -->
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC">
      </transactionManager>
      <dataSource type="POOLED">
        <property name="driver" value="${jdbc.driverClassName}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
      </dataSource>
    </environment>
  </environments>

  <!-- mappers：映射器，定义 SQL 映射语句，使用相对于类路径的资源引用， 或完全限定资源定位符（包括 file:/// 的 URL），或类名和包名 -->
  <mappers>
    <mapper resource="com/mutistic/mybatis/java/insert/mapper/InsertMapper.xml" />
    <mapper class="com.mutistic.mybatis.java.select.mapper.SelectMapper" />
  </mappers>
</configuration>
```
ResourcesTest.java：
```Java
package com.mutistic.mybatis.java.resources;
// 资源文件定位类
public class ResourcesTest { }
```
FileUtil.java：
```Java
package com.mutistic.mybatis.utils;
import com.mutistic.mybatis.java.insert.mapper.InsertMapper;
// 文件工具类 
public class FileUtil {
  public static void main(String[] args) {
    showURLPath("InsertMapper.xml", InsertMapper.class);
  }
  
  /**
   * 打印当前资源的URL路径 
   * @param fileName 文件名
   */
  public static void showURLPath(String fileName, Class<?> classType) {
    if(fileName == null) {
      fileName = "";
    }
    if(classType == null) {
      classType = FileUtil.class;
    }
    
    PrintUtil.two("1、通过Thread获取classes的路径：", Thread.currentThread().getContextClassLoader().getResource(""));
    PrintUtil.two("2、通过ClassLoader获取classes的路径：", ClassLoader.getSystemResource(""));
    PrintUtil.two("3、获取当前Class的classes的路径：[classType=" + classType.getName()+"]", classType.getClassLoader().getResource(""));
    PrintUtil.two("4、获取当前Class下的classes的路径：", classType.getResource("/"));
    PrintUtil.two("5、获取当前Class下的资源的目录路径：", classType.getResource(fileName));
    PrintUtil.two("6、获取当前Class下的资源的classes路径：", classType.getResource(fileName));
    PrintUtil.two("7、通过System获取项目路径：", System.getProperty("user.dir"));
  }
}
```

---
### <a id="a_insert">三、MyBatis新增数据：</a> <a href="#a_xml">last</a> <a href="#a_select">next</a>
SqlSeesionUtil.java：
```Java
package com.mutistic.mybatis.java.utils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.mutistic.mybatis.java.resources.ResourcesTest;
import com.mutistic.mybatis.java.select.mapper.SelectMapper;
import com.mutistic.mybatis.utils.PrintUtil;
// SqlSession 数据库会话工具类
public class SqlSeesionUtil {
  /** Mybatis Config 配置文件名称 */
  private final static String MYBATIS_CONFIG_XML = "mybatis-config.xml";
  /** SqlSessionFactory工厂类 */
  private static SqlSessionFactory sqlSessionFactory;
  /** SqlSession数据库会话 */
  private static SqlSession sqlSession;
  //  创建SqlSessionFactory工厂类
  public static SqlSessionFactory getSqlSessionFactory() {
    try {
      if (sqlSessionFactory == null) {
        System.out.println(ResourcesTest.class.getResourceAsStream(MYBATIS_CONFIG_XML).getClass());
        
        sqlSessionFactory = new SqlSessionFactoryBuilder()
            .build(ResourcesTest.class.getResourceAsStream(MYBATIS_CONFIG_XML));
        
        PrintUtil.one("0、创建SqlSessionFactory工厂类：");
        PrintUtil.two("0.1、Mybatis Config 配置文件名称及路径", "xmlName=" + MYBATIS_CONFIG_XML + ", xmlURL="
            + ResourcesTest.class.getResource(MYBATIS_CONFIG_XML).getPath());
        PrintUtil.two("0.2、通过SqlSessionFactoryBuilder创建工厂类", "sqlSessionFactory=" + sqlSessionFactory);
      }
    } catch (Exception e) {
      PrintUtil.err("0.e、创建数据库会话出现异常，打印堆栈信息：" + e.getMessage());
    }
    return sqlSessionFactory;
  }
  // 创建SqlSession数据库会话
  public static SqlSession openSession() {
    if (null == sqlSessionFactory) {
      getSqlSessionFactory();
    }
    if(null == sqlSession) {
      try {
        sqlSession = sqlSessionFactory.openSession();
        
        PrintUtil.two("0.3、通过SqlSessionFactory.openSession()：创建数据库会话", "SqlSession=" + sqlSession);
      } catch (Exception e) {
        PrintUtil.err("0.3.e、创建数据库会话出现异常，打印堆栈信息：" + e.getMessage());
      }
    }

    return sqlSession;
  }
  // 获取MapperClass实例对象
  public static <T> T getMapper(Class<T> mapperClass) {
    T mapper = null;
    try {
      mapper = openSession().getMapper(mapperClass);
      
      PrintUtil.two("0.4、通过SqlSession.getMapper(Class<T> type)：获取MapperClass实例对象", "MapperClass=" + mapper);
    } catch (Exception e) {
      PrintUtil.err("0.4.e、获取MapperClass实例对象出现异常，打印堆栈信息：" + e.getMessage());
    }
    return mapper;
  }
  // 提交SqlSeession 
  public static void commit() {
    try {
      if(null != sqlSession) {
        PrintUtil.two("0.5、提交SqlSeession", null);
        sqlSession.commit();
      }
    } catch (Exception e) {
      PrintUtil.err("0.5.e、提交SqlSeession出现异常，打印堆栈信息：" + e.getMessage());
    }
  }
  // 关闭SqlSeession 
  public static void close() {
    try {
      if(null != sqlSession) {
        PrintUtil.one("0.6、关闭SqlSeession");
        sqlSession.close();
      }
    } catch (Exception e) {
      PrintUtil.err("0.6.e、关闭SqlSeession出现异常，打印堆栈信息：" + e.getMessage());
    }
  }
  
  public static void main(String[] args) {
    getMapper(SelectMapper.class);
  }
}
```
BaseModel.java：
```Java
package com.mutistic.mybatis.java.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
// Model父类 
@SuppressWarnings("serial")
public class BaseModel implements Serializable {
  /** 主键 */
  private Long id;
  /** 主键集合 */
  private List<Long> ids;
  /** 创建人 */
  private Long createBy;
  /** 创建时间 */
  private Date createTime;
  /** 修改人 */
  private Long updateBy;
  /** 修改时间 */
  private Date updateTime;
  /** 是否逻辑删除：0-未删除，1-已删除 */
  private Integer enable;
  /** 备注 */
  private String remark;
  /**  版本号 */
  private Integer versionNo;
  /** 排序字段 */
  private String orderBy;
  /** 排序规则 */
  private String sortAsc;
  // ...
}
```
BizBuyAddress.java：
```Java
package com.mutistic.mybatis.java.model;
// 收货地址 
@SuppressWarnings("serial")
public class BizBuyAddress extends BaseModel {
  /** 用户ID */
  private Long userId;
  /** 收货人 */
  private String consigneeName;
  /** 收货手机号 */
  private String consigneeMobile;
  /** 收货地址 */
  private String consigneeAddress;
  /** 省份编码 */
  private String provinceCode;
  /** 城市编码 */
  private String cityCode;
  /** 区县编码 */
  private String countyCode;
  /** 是否默认地址：0-非默认，1-默认 */
  private Integer isDefault;
  // ...
}
```
InsertMapper.java：
```Java
package com.mutistic.mybatis.java.insert.mapper;
import com.mutistic.mybatis.java.model.BizBuyAddress;
// InsertMapper 接口
public interface InsertMapper {
  // 新增静态数据
  Long insertByStatic();
  // 新增动态数据 
  Long insertByDynamic(BizBuyAddress entity);
}
```
InsertMapper.xml：
```xml
<?xml version="1.0" encoding="UTF-8" ?>

<!-- Mybatis 3 mapper DTD规范 -->
<!DOCTYPE mapper 
 PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- mappper：Mapper XML的根标签 -->
<mapper namespace="com.mutistic.mybatis.java.insert.mapper.InsertMapper">
  <!-- Mapper XML参考：http://www.mybatis.org/mybatis-3/zh/sqlmap-xml.html-->
  
  <!-- 定义可重用的 SQL 代码段，可以包含在其他语句中 -->
  <sql id="column">
    id_, user_id, consignee_name, consignee_mobile,
    consignee_address,
    province_code, city_code, county_code, is_default,
    create_by, create_time, update_by, update_time,
    remark_, enable_, version_no
  </sql>
  
  <!-- 插入静态数据 -->
  <insert id="insertByStatic" parameterType="com.mutistic.mybatis.java.model.BizBuyAddress">
    INSERT INTO biz_buy_address (
      <include refid="column"></include>
    ) VALUES (
      UNIX_TIMESTAMP(NOW()), '1029209400357347330', '张三',
      '13100000000', '地址', '210000', '210700', '210781', '1',
      '1029209267859283970', '2018-08-14 11:55:45',
      '1029209267859283970', '2018-08-14 11:55:45',
      '', '0', '0'
    )
  </insert>
  
  <!-- 插入动态数据 -->
  <insert id="insertByDynamic" parameterType="BizBuyAddress">
    INSERT INTO biz_buy_address (
      <include refid="column"></include>
    ) VALUES (
      #{id},#{userId}, #{consigneeName}, #{consigneeMobile}, #{consigneeAddress},
      #{provinceCode}, #{cityCode}, #{countyCode}, #{isDefault},
      #{createBy}, #{createTime}, #{updateBy}, #{updateTime},
      #{remark}, #{enable}, #{versionNo}
    )
  </insert>
</mapper>
```
InsertMain.java：
```Java
package com.mutistic.mybatis.java.insert;
import java.util.Date;
import com.mutistic.mybatis.java.insert.mapper.InsertMapper;
import com.mutistic.mybatis.java.model.BizBuyAddress;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;
// MyBatis新增数据
public class InsertMain {
  public static void main(String[] args) {
    InsertMapper mapper = SqlSeesionUtil.getMapper(InsertMapper.class);

    PrintUtil.one("1、MyBatis新增数据");
    showByStatic(mapper);
    showByDynamic(mapper);

    SqlSeesionUtil.close();
  }
  private static void showByStatic(InsertMapper mapper) {
    PrintUtil.one("2、新增静态数据");

    Long result = mapper.insertByStatic();
    SqlSeesionUtil.commit();
    PrintUtil.two("2.1、新增静态数据结果：", "result=" + result);
  }
  private static void showByDynamic(InsertMapper mapper) {
    PrintUtil.one("3、新增动态数据：");

    BizBuyAddress entity = new BizBuyAddress();
    entity.setId(System.currentTimeMillis());
    entity.setCityCode("210700");
    entity.setConsigneeAddress("testAddress");
    entity.setConsigneeMobile("13600000000");
    entity.setConsigneeName("test");
    entity.setCountyCode("210781");
    entity.setCreateBy(99999l);
    entity.setCreateTime(new Date());
    entity.setEnable(0);
    entity.setIsDefault(1);
    entity.setProvinceCode("210000");
    entity.setRemark("testRemark");
    entity.setUpdateBy(entity.getCreateBy());
    entity.setUpdateTime(entity.getCreateTime());
    entity.setUserId(111111l);
    entity.setVersionNo(0);

    PrintUtil.two("3.1、准备动态数据：", "entity=" + entity);
    Long result2 = mapper.insertByDynamic(entity);
    SqlSeesionUtil.commit();
    PrintUtil.two("3.2、新增动态数据结果：", "result=" + result2);
  }
}
```

---
### <a id="a_select">四、MyBatis查询数据：</a> <a href="#a_insert">last</a> <a href="#a_update">next</a>
Pagination.java：
```Java
package com.mutistic.mybatis.java.model;
import java.io.Serializable;
import java.util.List;
// 分页对象
@SuppressWarnings("serial")
public class Pagination<T> implements Serializable {
  /** 总条数 */
  private Long total;
  /** 总页数 */
  private Integer current;
  /** 当前页条数 */
  private Integer size;
  /** 当前页 */
  private Integer pages;
  /** 当前页数据 */
  private List<T> records;
  // ...
}
```
SelectMapper.java：
```Java
package com.mutistic.mybatis.java.select.mapper;
import java.util.List;
import java.util.Map;
import com.mutistic.mybatis.java.model.BizBuyAddress;
// SelectMapper 接口
public interface SelectMapper {
  // 根据ID查询数据
  BizBuyAddress queryById(Long id);
  // 根据实体查询集合
  List<BizBuyAddress> queryList(BizBuyAddress param);
  // 分页查询-查询总条数
  List<Long> selectCount(Map<String, Object> params);
  // 分页查询-查询当前内容数
  List<BizBuyAddress> queryPage(Map<String, Object> params);
}
```
SelectMapper.xml：
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!-- Mybatis 3 mapper DTD规范 -->
<!DOCTYPE mapper 
 PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.mutistic.mybatis.java.select.mapper.SelectMapper">
  <!-- 映射结果集 -->
  <resultMap id="resultMap" type="BizBuyAddress">
    <id column="id_" property="id" />
    <result column="user_id" property="userId" />
    <result column="consignee_name" property="consigneeName" />
    <result column="consignee_mobile" property="consigneeMobile" />
    <result column="consignee_address" property="consigneeAddress" />
    <result column="province_code" property="cityCode" />
    <result column="city_code" property="cityCode" />
    <result column="county_code" property="countyCode" />
    <result column="is_default" property="isDefault" />
    <result column="create_by" property="createBy" />
    <result column="create_time" property="createTime" />
    <result column="update_by" property="updateBy" />
    <result column="update_time" property="updateTime" />
    <result column="remark_" property="remark" />
    <result column="enable_" property="enable" />
    <result column="version_no" property="versionNo" />
  </resultMap>

  <!-- 定义可重用的 SQL 代码段，可以包含在其他语句中 -->
  <sql id="entityColumn">
    id_, user_id, consignee_name, consignee_mobile, consignee_address,
    province_code, city_code, county_code, is_default,
    create_by, create_time, update_by, update_time,
    remark_, enable_, version_no
  </sql>
  <!-- 实体动态条件 -->
  <sql id="entityIf">
    <if test="id != null">
      AND id_ = #{id}
    </if>
    <if test="ids != null and !ids.isEmpty()">
      <!-- <if test="ids != null and ids.size() > 0"> -->
      AND id_ IN
      <foreach collection="ids" item="key" separator="," open="("
        close=")">
        ${key}
      </foreach>
    </if>
    <if test="userId != null">
      AND user_id = #{userId}
    </if>
    <if test="consigneeName != null and consigneeName != ''">
      AND consignee_name = #{consigneeName}
    </if>
    <if test="consigneeMobile != null and consigneeMobile != ''">
      AND consignee_mobile = #{consigneeMobile}
    </if>
    <if test="consigneeAddress != null and consigneeAddress != ''">
      AND consignee_address = #{consigneeAddress}
    </if>
    <if test="provinceCode != null and provinceCode != ''">
      AND province_code = #{provinceCode}
    </if>
    <if test="cityCode != null and cityCode != ''">
      AND city_code = #{cityCode}
    </if>
    <if test="countyCode != null and countyCode != ''">
      AND county_code = #{countyCode}
    </if>
    <if test="isDefault != null"> AND is_default = #{isDefault}
    </if>
    <if test="createBy != null">
      AND create_by = #{createBy}
    </if>
    <if test="createTime != null">
      AND create_time = #{createTime}
    </if>
    <if test="updateBy != null">
      AND update_by = #{updateBy}
    </if>
    <if test="updateTime != null">
      AND update_time = #{updateTime}
    </if>
    <if test="remark != null and remark != ''">
      AND remark_ = #{remark}
    </if>
    <if test="enable != null">
      AND enable_ = #{enable}
    </if>
    <if test="versionNo != null">
      AND version_no = #{versionNo}
    </if>
  </sql>

  <!-- 根据ID查询数据 -->
  <select id="queryById" parameterType="java.lang.Long" resultMap="resultMap">
    SELECT
      <include refid="entityColumn"></include>
    FROM biz_buy_address
      WHERE id_ = #{id}
  </select>

  <!-- 实体参数Where -->
  <sql id="entityWhere">
    <where>
      <include refid="entityIf"></include>
    </where>
    <if test="orderBy != null"> order by ${orderBy} </if>
    <if test="sortAsc != null"> ${sortAsc} </if>
  </sql>
  <!-- 集合查询 -->
  <select id="queryList" parameterType="BizBuyAddress" resultMap="resultMap">
    SELECT
      <include refid="entityColumn"></include>
    FROM biz_buy_address
      <include refid="entityWhere"></include>
  </select>

  <!-- 扩展参数Where -->
  <sql id="mapWhere">
    <where>
      <include refid="entityIf"></include>
      <if test="consigneeNameLike != null and consigneeNameLike != ''">
        AND consignee_name LIKE CONCAT('%', #{consigneeNameLike}, '%')
      </if>
    </where>
    <if test="orderBy != null"> order by ${orderBy} </if>
    <if test="sortAsc != null"> ${sortAsc} </if>
  </sql>
  <!-- 查询ID集合 -->
  <select id="selectCount" resultType="java.lang.Long">
    SELECT id_  FROM biz_buy_address
    <include refid="mapWhere"></include>
  </select>
  <!-- 分页查询 -->
  <select id="queryPage" parameterType="java.util.Map" resultMap="resultMap">
    SELECT
      <include refid="entityColumn"></include>
    FROM biz_buy_address
      <include refid="mapWhere"></include>
      <if test="limit != null"> LIMIT ${limit} </if>
      <if test="offset != null"> OFFSET ${offset} </if>
  </select>
</mapper>
```
SelectMain.java：
```Java
package com.mutistic.mybatis.java.select;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mutistic.mybatis.java.model.BizBuyAddress;
import com.mutistic.mybatis.java.model.Pagination;
import com.mutistic.mybatis.java.select.mapper.SelectMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;
// MyBatis查询数据
public class SelectMain {
  public static void main(String[] args) {
    SelectMapper mapper = SqlSeesionUtil.getMapper(SelectMapper.class);
    PrintUtil.one("1、MyBatis查询数据");
    showByQueryById(mapper);
    showByQueryList(mapper);
    showByQueryPage(mapper);
    SqlSeesionUtil.close();
  }
  private static void showByQueryById(SelectMapper mapper) {
    PrintUtil.one("2、根据ID查询数据： ");
    BizBuyAddress entity = mapper.queryById(1547713057l);
    PrintUtil.two("2.1、查询结果：", "entity=" + entity);
  }
  private static void showByQueryList(SelectMapper mapper) {
    PrintUtil.one("3、根据实体查询集合：");

    BizBuyAddress params = new BizBuyAddress();
    params.setId(1547713057l);
    List<BizBuyAddress> entityList = mapper.queryList(params);
    PrintUtil.two("3.1、查询结果：", "entityList=" + entityList);
  }
  private static void showByQueryPage(SelectMapper mapper) {
    PrintUtil.one("4、根据Map分页：");

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("consigneeNameLike", "test");
    List<Long> idList = mapper.selectCount(params);
    PrintUtil.two("4.1、查询总条数（id集合）：", "idList=" + idList);
    PrintUtil.println();

    Map<String, Object> pageParam = new HashMap<String, Object>();
    pageParam.put("offset", 0);
    pageParam.put("limit", 10);
    pageParam.put("ids", idList);
    List<BizBuyAddress> resultList = mapper.queryPage(pageParam);
    PrintUtil.two("4.2、分页查询结果（集合大小）：", "idList=" + resultList.size());
    PrintUtil.println();

    Integer offset = (Integer) pageParam.get("offset");
    Integer limit = (Integer) pageParam.get("limit");
    Pagination<BizBuyAddress> page = new Pagination<BizBuyAddress>();
    page.setTotal(Long.valueOf(idList.size()));
    page.setPages(offset);
    page.setSize(resultList.size());
    page.setRecords(resultList);
    
    int current = (int)(page.getTotal() / limit);
    if(page.getTotal() % limit != 0) {
      current++;
    }
    page.setCurrent(current);
    PrintUtil.two("4.3、封装成分页对象：", "Pagination=" + page);
  }
}
```

---
### <a id="a_update">五、MyBatis修改数据：</a> <a href="#a_select">last</a> <a href="#a_delete">next</a>
UpdateMapper.java：
```Java
package com.mutistic.mybatis.java.update.mapper;
import com.mutistic.mybatis.java.model.BizBuyAddress;
// pdateMapper 接口
public interface UpdateMapper {
	// 直接修改数据
	Long updateEntity(BizBuyAddress entity);
	// 当字段不为null时修改数据
	Long updateByNotNull(BizBuyAddress entity);
}
```
UpdateMapper.xml：
```XML
<?xml version="1.0" encoding="UTF-8" ?>
<!-- Mybatis 3 mapper DTD规范 -->
<!DOCTYPE mapper 
 PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mutistic.mybatis.java.update.mapper.UpdateMapper">
	<!-- 直接修改数据 -->
	<update id="updateEntity" parameterType="BizBuyAddress">
		UPDATE biz_buy_address
			SET user_id = #{userId},
			consignee_name = #{consigneeName},
			consignee_mobile = #{consigneeMobile},
			consignee_address = #{consigneeAddress},
			province_code = #{provinceCode},
			city_code = #{cityCode},
			county_code = #{countyCode},
			 is_default = #{isDefault},
			create_by = #{createBy},
			create_time = #{createTime},
			update_by = #{updateBy},
			update_time = #{updateTime},
			remark_ = #{remark},
			enable_ = #{enable},
			version_no = #{versionNo}
		WHERE id_ = #{id}
	</update>
	
	<!-- 当字段不为null时修改数据 -->
	<update id="updateByNotNull" parameterType="BizBuyAddress">
		UPDATE biz_buy_address
		<!-- <trim prefix="SET" suffixOverrides=","></trim> -->
		<set>
			<if test="userId != null">
				user_id = #{userId},
			</if>
			<if test="consigneeName != null">
				consignee_name = #{consigneeName},
			</if>
			<if test="consigneeMobile != null">
				consignee_mobile = #{consigneeMobile},
			</if>
			<if test="consigneeAddress != null">
				consignee_address = #{consigneeAddress},
			</if>
			<if test="provinceCode != null">
				province_code = #{provinceCode},
			</if>
			<if test="cityCode != null">
				city_code = #{cityCode},
			</if>
			<if test="countyCode != null">
				county_code = #{countyCode},
			</if>
			<if test="isDefault != null">
				is_default = #{isDefault},
			</if>
			<if test="createBy != null">
				create_by = #{createBy},
			</if>
			<if test="createTime != null">
				create_time = #{createTime},
			</if>
			<if test="updateBy != null">
				update_by = #{updateBy},
			</if>
			<if test="updateTime != null">
				update_time = #{updateTime},
			</if>
			<if test="remark != null">
				remark_ = #{remark},
			</if>
			<if test="enable != null">
				enable_ = #{enable},
			</if>
			<if test="versionNo != null">
				version_no = #{versionNo},
			</if>
		</set>
		WHERE id_ = #{id}
	</update>
</mapper>
```
UpdateMain.java：
```Java
package com.mutistic.mybatis.java.update;
import java.util.Date;
import com.mutistic.mybatis.java.model.BizBuyAddress;
import com.mutistic.mybatis.java.update.mapper.UpdateMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;
//  MyBatis修改数据
public class UpdateMain {
	public static void main(String[] args) {
		UpdateMapper mapper = SqlSeesionUtil.getMapper(UpdateMapper.class);
		PrintUtil.one("1、 MyBatis修改数据");
		
		BizBuyAddress entity = new BizBuyAddress();
		entity.setId(1029214969835257858l);
		entity.setCityCode("210700");
		entity.setConsigneeAddress("testAddress");
		entity.setConsigneeMobile("13600000000");
		entity.setConsigneeName("test");
		entity.setCountyCode("210781");
		entity.setCreateBy(99999l);
		entity.setCreateTime(new Date());
		entity.setEnable(0);
		entity.setIsDefault(1);
		entity.setProvinceCode("210000");
		entity.setRemark("testRemark");
		entity.setUpdateBy(entity.getCreateBy());
		entity.setUpdateTime(entity.getCreateTime());
		entity.setUserId(111111l);
		entity.setVersionNo(1);
		
		showByUpdateEntity(mapper, entity);
		showByUpdateByNotNull(mapper, entity);
		SqlSeesionUtil.close();
	}
	private static void showByUpdateEntity(UpdateMapper mapper, BizBuyAddress entity) {
		PrintUtil.one("2、直接修改数据： ");
		Long result = mapper.updateEntity(entity);
		PrintUtil.two("2.1、修改结果", "result="+result);
		SqlSeesionUtil.commit();
	}
	private static void showByUpdateByNotNull(UpdateMapper mapper, BizBuyAddress entity) {
		PrintUtil.one("3、当字段不为null时修改数据： ");
		entity.setRemark("");
		entity.setVersionNo(2);
		Long result = mapper.updateByNotNull(entity);
		PrintUtil.two("3.1、修改结果", "result="+result);
		SqlSeesionUtil.commit();
	}
}
```

---
### <a id="a_delete">六、MyBatis删除数据</a> <a href="#a_update">last</a> <a href="#a_one">next</a>
DeleteMapper.java：
```Java
package com.mutistic.mybatis.java.delete.mapper;
import com.mutistic.mybatis.java.model.BizBuyAddress;
// DeleteMapper 接口
public interface DeleteMapper {
	// 根据实体删除数据
	Long deleteEntity(BizBuyAddress entity);

	// 根据ID删除数据 
	Long deleteById(Long id);
}
```
DeleteMapper.xml：
```XML
<?xml version="1.0" encoding="UTF-8" ?>
<!-- Mybatis 3 mapper DTD规范 -->
<!DOCTYPE mapper 
 PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mutistic.mybatis.java.delete.mapper.DeleteMapper">
	<!-- 根据实体删除数据 -->
	<delete id="deleteEntity" parameterType="BizBuyAddress">
		DELETE FROM biz_buy_address WHERE id_ = #{id}
	</delete>
	<!-- 根据ID删除数据  -->
	<delete id="deleteById" parameterType="java.lang.Long">
		DELETE FROM biz_buy_address WHERE id_ = #{id}
	</delete>
</mapper>
```
DeleteMain.java：
```Java
package com.mutistic.mybatis.java.delete;
import com.mutistic.mybatis.java.delete.mapper.DeleteMapper;
import com.mutistic.mybatis.java.model.BizBuyAddress;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;
// MyBatis删除数据
public class DeleteMain {
	public static void main(String[] args) {
		DeleteMapper mapper = SqlSeesionUtil.getMapper(DeleteMapper.class);
		PrintUtil.one("1、 MyBatis删除数据");
		showByDeleteEntity(mapper);
		showByDeleteById(mapper);
		SqlSeesionUtil.close();
	}
	private static void showByDeleteEntity(DeleteMapper mapper) {
		PrintUtil.one("2、根据实体删除数据：");
		BizBuyAddress entity = new BizBuyAddress();
		entity.setId(1029214969835257858l);
		Long result = mapper.deleteEntity(entity);
		PrintUtil.two("2.1、删除结果", "result="+ result);
	}
	private static void showByDeleteById(DeleteMapper mapper) {
		PrintUtil.one("3、根据ID删除数据");
		Long result = mapper.deleteById(1547720793414l);
		PrintUtil.two("3.1、删除结果", "result="+ result);
	}
}
```

---
### <a id="a_one">七、MyBatis映射关系：一对一</a> <a href="#a_delete">last</a> <a href="#a_more">next</a>
OneToOneDto.java：
```Java
package com.mutistic.mybatis.java.one.dto;
import java.io.Serializable;
import com.mutistic.mybatis.java.model.BizAddress;
import com.mutistic.mybatis.java.model.BizUser;
// 一对一关系映射DTO对象
@SuppressWarnings("serial")
public class OneToOneDto implements Serializable {
	/** BizUser对象 */
	private BizUser bizUser;
	/** BizAddress对象 */
	private BizAddress bizAddress;
	// ... 
}
```
OneToOneMapper.java：
```Java
package com.mutistic.mybatis.java.one.mapper;
import com.mutistic.mybatis.java.one.dto.OneToOneDto;
// OneToOneMapper 接口
public interface OneToOneMapper {
	// 通过ResultMap映射查询结果集
	OneToOneDto queryByResultMap(Long id);
	// 通过association的ResultMap属性映射查询结果集 
	OneToOneDto queryByAssociationResultMap(Long id);
	// 通过association的Select根据外键查询结果集 
	OneToOneDto queryByAssociationSelect(Long id);
}
```
OneToOneMapper.xml：
```XML
<?xml version="1.0" encoding="UTF-8" ?>
<!-- Mybatis 3 mapper DTD规范 -->
<!DOCTYPE mapper 
 PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mutistic.mybatis.java.one.mapper.OneToOneMapper">
	<!-- 通过ResultMap映射查询结果集 -->
	<resultMap id="ResultMap" type="com.mutistic.mybatis.java.one.dto.OneToOneDto">
		<result column="id_" property="bizUser.id"/>
		<result column="name_" property="bizUser.name" />
		<result column="account_" property="bizUser.account" />
		<result column="password_" property="bizUser.password" />
		<result column="mobile_" property="bizUser.mobile" />
		<result column="create_by" property="bizUser.createBy" />
		<result column="create_time" property="bizUser.createTime" />
		<result column="update_by" property="bizUser.updateBy" />
		<result column="update_time" property="bizUser.updateTime" />
		<result column="remark_" property="bizUser.remark" />
		<result column="enable_" property="bizUser.enable" />
		<result column="version_no" property="bizUser.versionNo" />
		
		<result column="id_1" property="bizAddress.id" />
		<result column="user_id" property="bizAddress.userId" />
		<result column="consignee_name" property="bizAddress.consigneeName" />
		<result column="consignee_mobile" property="bizAddress.consigneeMobile" />
		<result column="consignee_address" property="bizAddress.consigneeAddress" />
		<result column="province_code" property="bizAddress.cityCode" />
		<result column="city_code" property="bizAddress.cityCode" />
		<result column="county_code" property="bizAddress.countyCode" />
		<result column="is_default" property="bizAddress.isDefault" />
		<result column="create_by1" property="bizAddress.createBy" />
		<result column="create_time1" property="bizAddress.createTime" />
		<result column="update_by1" property="bizAddress.updateBy" />
		<result column="update_time1" property="bizAddress.updateTime" />
		<result column="remark_1" property="bizAddress.remark" />
		<result column="enable_1" property="bizAddress.enable" />
		<result column="version_no1" property="bizAddress.versionNo" />
	</resultMap>
	<select id="queryByResultMap" parameterType="java.lang.Long" resultMap="ResultMap">
		SELECT 
		    bizUser.id_, bizUser.name_, bizUser.account_, bizUser.password_, bizUser.mobile_,
			bizUser.create_by, bizUser.create_time, bizUser.update_by, bizUser.update_time,
			bizUser.remark_, bizUser.enable_, bizUser.version_no,
			bizAddress.id_, bizAddress.user_id, bizAddress.consignee_name, bizAddress.consignee_mobile, bizAddress.consignee_address,
			bizAddress.province_code, bizAddress.city_code, bizAddress.county_code, bizAddress.is_default,
			bizAddress.create_by, bizAddress.create_time, bizAddress.update_by, bizAddress.update_time,
			bizAddress.remark_, bizAddress.enable_, bizAddress.version_no
		FROM biz_address bizAddress
		LEFT JOIN biz_user bizUser on bizUser.id_ = bizAddress.user_id
		WHERE bizAddress.id_ = #{id}
	</select>
	
	<!-- 通过association的ResultMap属性映射查询结果集 -->
	<resultMap id="AssociationResultMap" type="com.mutistic.mybatis.java.one.dto.OneToOneDto">
		<association property="bizUser" resultMap="BizUserResult"></association>
		<association property="bizAddress" resultMap="BizAddressResult"></association>
	</resultMap>
	<resultMap id="BizUserResult"  type="BizUser">
		<result column="id_" property="id"/>
		<result column="name_" property="name" />
		<result column="account_" property="account" />
		<result column="password_" property="password" />
		<result column="mobile_" property="mobile" />
		<result column="create_by" property="createBy" />
		<result column="create_time" property="createTime" />
		<result column="update_by" property="updateBy" />
		<result column="update_time" property="updateTime" />
		<result column="remark_" property="remark" />
		<result column="enable_" property="enable" />
		<result column="version_no" property="versionNo" />
	</resultMap>
	<resultMap id="BizAddressResult"  type="BizAddress">
		<result column="id_1" property="id" />
		<result column="user_id" property="userId" />
		<result column="consignee_name" property="consigneeName" />
		<result column="consignee_mobile" property="consigneeMobile" />
		<result column="consignee_address" property="consigneeAddress" />
		<result column="province_code" property="cityCode" />
		<result column="city_code" property="cityCode" />
		<result column="county_code" property="countyCode" />
		<result column="is_default" property="isDefault" />
		<result column="create_by1" property="createBy" />
		<result column="create_time1" property="createTime" />
		<result column="update_by1" property="updateBy" />
		<result column="update_time1" property="updateTime" />
		<result column="remark_1" property="remark" />
		<result column="enable_1" property="enable" />
		<result column="version_no1" property="versionNo" />
	</resultMap>
	<select id="queryByAssociationResultMap" parameterType="java.lang.Long" resultMap="AssociationResultMap">
		SELECT 
		    bizUser.id_, bizUser.name_, bizUser.account_, bizUser.password_, bizUser.mobile_,
			bizUser.create_by, bizUser.create_time, bizUser.update_by, bizUser.update_time,
			bizUser.remark_, bizUser.enable_, bizUser.version_no,
			bizAddress.id_, bizAddress.user_id, bizAddress.consignee_name, bizAddress.consignee_mobile, bizAddress.consignee_address,
			bizAddress.province_code, bizAddress.city_code, bizAddress.county_code, bizAddress.is_default,
			bizAddress.create_by, bizAddress.create_time, bizAddress.update_by, bizAddress.update_time,
			bizAddress.remark_, bizAddress.enable_, bizAddress.version_no
		FROM biz_address bizAddress
		LEFT JOIN biz_user bizUser on bizUser.id_ = bizAddress.user_id
		WHERE bizAddress.id_ = #{id}
	</select>
	
	<!-- 通过association的Select根据外键查询结果集 -->
	<resultMap id="AssociationSelect" type="com.mutistic.mybatis.java.one.dto.OneToOneDto">
		<association property="bizAddress" resultMap="com.mutistic.mybatis.java.select.mapper.SelectMapper.resultMap"/>
		<association property="bizUser" column="user_id" 
			select="com.mutistic.mybatis.java.bizuser.mapper.BizUserMapper.queryById"/>
	</resultMap>
	<select id="queryByAssociationSelect" parameterType="java.lang.Long" resultMap="AssociationSelect">
		SELECT 
		   id_, user_id, consignee_name, consignee_mobile, consignee_address,
			province_code, city_code, county_code, is_default,
			create_by, create_time, update_by, update_time,
			remark_, enable_, version_no
		FROM biz_address
		WHERE id_ = #{id}
	</select>
</mapper>
```
OneToOneMain.java：
```Java
package com.mutistic.mybatis.java.one;
import com.mutistic.mybatis.java.one.dto.OneToOneDto;
import com.mutistic.mybatis.java.one.mapper.OneToOneMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;
// MyBatis映射关系：一对一
public class OneToOneMain {
	public static void main(String[] args) {
		OneToOneMapper mapper = SqlSeesionUtil.getMapper(OneToOneMapper.class);
		PrintUtil.one("1、MyBatis映射关系：一对一");
		
		showByQueryByResultMap(mapper);
		showByQueryByAssociationResultMap(mapper);
		showByQueryByAssociationSelect(mapper);
		SqlSeesionUtil.close();
	}
	private static void showByQueryByResultMap(OneToOneMapper mapper) {
		PrintUtil.one("2、 通过ResultMap映射查询结果集：");
		OneToOneDto dto = mapper.queryByResultMap(1547720793414l);
		PrintUtil.two("2.1、查询结果：", "dto=" + dto);
	}
	private static void showByQueryByAssociationResultMap(OneToOneMapper mapper) {
		PrintUtil.one("3、 通过association的ResultMap属性映射查询结果集：");
		OneToOneDto dto = mapper.queryByAssociationResultMap(1547720793414l);
		PrintUtil.two("3.1、查询结果：", "dto=" + dto);
	}
	private static void showByQueryByAssociationSelect(OneToOneMapper mapper) {
		PrintUtil.one("4、通过association的Select根据外键查询结果集：");
		OneToOneDto dto = mapper.queryByAssociationSelect(1547720793414l);
		PrintUtil.two("4.1、查询结果：", "dto=" + dto);
	}
}
```


---
### <a id="a_more">八、MyBatis映射关系：一对多</a> <a href="#a_one">last</a> <a href="#">next</a>
OneToMoreDto.java：
```Java
package com.mutistic.mybatis.java.more.dto;
import java.util.List;
import com.mutistic.mybatis.java.model.BizAddress;
import com.mutistic.mybatis.java.model.BizUser;
// 一对多关系映射DTO对象
public class OneToMoreDto {
	/** BizUser对象 */
	private BizUser bizUser;
	/** BizAddress集合 */
	private List<BizAddress> bizAddressList;
	// ...
}
```
OneToMoreMapper.java：
```Java
package com.mutistic.mybatis.java.more.mapper;
import com.mutistic.mybatis.java.more.dto.OneToMoreDto;
// OneToMoreMapper 接口
public interface OneToMoreMapper {
	// 通过Collection的Select根据外键查询一对多结果集 
	OneToMoreDto queryByCollection(Long id);
}
```
OneToMoreMapper.xml：
```XML
<?xml version="1.0" encoding="UTF-8" ?>

<!-- Mybatis 3 mapper DTD规范 -->
<!DOCTYPE mapper 
 PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mutistic.mybatis.java.more.mapper.OneToMoreMapper">
	<!-- 通过Collection的Select根据外键查询一对多结果集 -->
	<resultMap id="Collection" type="com.mutistic.mybatis.java.more.dto.OneToMoreDto">
		<association property="bizUser" resultMap="com.mutistic.mybatis.java.bizuser.mapper.BizUserMapper.BizUserMap"/>
		<collection property="bizAddressList" column="id_"
			select="com.mutistic.mybatis.java.select.mapper.SelectMapper.queryByUserId"/>
	</resultMap>
	<select id="queryByCollection" parameterType="java.lang.Long" resultMap="Collection">
		SELECT 
			id_, name_, account_, password_, mobile_,
			create_by, create_time, update_by, update_time,
			remark_, enable_, version_no
		FROM biz_user
		WHERE id_ = #{id}
	</select>
</mapper>
```

---
### <a id="a_notes">[Notes](https://github.com/mutistic/mutistic.mybatis/blob/master/com.mutistic.mybatis/notes)</a> <a href="#top">last</a> <a href="#a_catalogue">next</a>
[Pit1：mybatis-config.xml问题集](https://github.com/mutistic/mutistic.mybatis/blob/master/com.mutistic.mybatis/notes/pit/Pit1-mybatis-config.xml%E9%97%AE%E9%A2%98%E9%9B%86.doc)  
[Pit2：mapper.xml问题集](https://github.com/mutistic/mutistic.mybatis/blob/master/com.mutistic.mybatis/notes/pit/Pit2-mapper.xml%E9%97%AE%E9%A2%98%E9%9B%86.doc)  

---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>