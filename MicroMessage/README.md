# MicroMessage
这个 Demo 是通过慕课网视频学习总结而成的。

## 案例分析
### 基本功能
基本功能就是接收粉丝传过来的指令，后台做相应的内容回复。

### 模块划分
大致分为四个模块：
- 回复内容列表
- 回复内容删除
- 回复内容维护
- 对话功能

### 知识点
- 展示层：JSP+JSTL+EL+JS/JQUERY
- 控制层：Servlet+JavaBean
- 数据库层：JDBC/MyBatis+MySQL

## 着手开发
### 控制层
ListServlet 的开发：
- 在 web.xml 中注册 ListServlet。
- 在 com.servlet 包中创建 ListServlet，重写 doGet 和 doPost 方法。并跳转到 list.jsp 中。
- 在 WEB-INF 下创建 list.jsp。
- 向 WebContent 中导入 css，image 文件。
- 在 list.jsp 中修改访问 css 的路径。

需要注意的是：
- 项目命名方式，选择全拼还是英文。
- 注释。

```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
```

### JDBC 数据库连接
模板如下：
``` java
try {
	// 加载驱动
	Class.forName("com.mysql.jdbc.Driver");
	// 获取连接
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message");
	// 编写SQL语句
	String sql = "select id, command, description, content from message";
	// 执行SQL语句
	PreparedStatement pstmt = conn.prepareStatement(sql);
	// 执行查询
	ResultSet rs = pstmt.executeQuery();
	// 将查询的结果保存在此List中
	List<Message> messageList = new ArrayList<>();
	// 遍历结果集
	while (rs.next()) {
		Message message = new Message();
		messageList.add(message);
		message.setId(rs.getString("id"));
		message.setCommand(rs.getString("command"));
		message.setDescription(rs.getString("description"));
		message.setContent(rs.getString("content"));
	}
	// 输出结果集
	req.setAttribute("messageList", messageList);
} catch (ClassNotFoundException e) {
	e.printStackTrace();
} catch (SQLException e) {
	e.printStackTrace();
}
```

需要注意的是：
- 在SQL语句中不能写`select *`。

### 展示数据
在 JSP 中使用 JSTL 来展示从数据库中获取的数据：
- 导入 JSTL 标签。`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`
- 使用 c:forEach 标签编写循环语句。
- 使用 c:if 标签编写隔行换色。
```
<c:forEach items="${messageList}" var="message" varStatus="status">
  <tr <c:if test="${status.index % 2 != 0}">style="background-color:#ECF6EE;"</c:if>>
	<td><input type="checkbox" /></td>
	<td>${status.index + 1}</td>
	<td>${message.command}</td>
	<td>${message.description}</td>
	<td>
	  <a href="#">修改</a>&nbsp;&nbsp;&nbsp;
	  <a href="#">删除</a>
	</td>
  </tr>
</c:forEach>
```

注意：
- forEach 标签中 items, var, varStatus 属性的含义。
- `${}`这个表达式的含义。

### 列表查询
- 先修改 JSP 中的 form 表单，将 action 等内容填充好。
- 接着在 JDBC 中处理 SQL 语句问题。
	- 首先将 sql 的 String 改为 StringBuilder，方便 SQL 的拼接。
	- 从 request 拿到 form 表单参数。
	- 判断参数是否为空，不为空的情况下拼接 SQL 语句。
	- 将需要拼接的 SQL 语句暂存在一个 List 中。
	- 在执行查询之前将 List 中的 SQL 语句取出来，拼接到 SQL 语句后面。
	- 处理值保留问题：将参数放回 request 中。

```
// jsp
<input name="command" type="text" class="allInput" value="${command}"/>
// 设置编码，解决乱码(可以用过滤器)
req.setCharacterEncoding("UTF-8");
// 拿到表单参数
String command = req.getParameter("command");
String description = req.getParameter("description");
// 将从request中接到的值放入request中，做一个值保留(需要在JSP中将value栏的值填入)
req.setAttribute("command", command);
req.setAttribute("description", description);
// 加载驱动
// 获取连接
// 编写SQL语句
StringBuilder sql = new StringBuilder("select id, command, description, content from message where 1 = 1");
// 使用一个List缓存SQL语句
List<String> paramList = new ArrayList<>();
// 判断表单参数是否为空
if (command != null && !"".equals(command.trim())) {
	sql.append(" and command = ?");
	paramList.add(command);
}
if (description != null && !"".equals(description.trim())) {
	sql.append(" and description like '%' ? '%' ");
	paramList.add(description);
}
// 执行SQL语句
PreparedStatement pstmt = conn.prepareStatement(sql.toString());
// 在执行查询之前将表单参数的SQL语句拼接起来
for (int i=0; i < paramList.size(); i++) {
	// 拼接从 i+1 开始
	pstmt.setString(i + 1, paramList.get(i));
}
// 执行查询
ResultSet rs = pstmt.executeQuery();
```

注意：
- input 标签中 name, value 属性值。

### 重构代码
分层：
- Servlet：负责设置编码（也可以用过滤器），接收页面的值，向页面传值，调用 service 并将只传给 request，以及向页面跳转几个功能。
- dao：负责数据库的相关操作（查询，删除等），也就是 JDBC 那部分的代码。
- service：业务逻辑层。用于把 dao 层查询的结果返回给 Servlet。
- bean：对应数据库表中的实体类。

### MyBatis 初识
下载 jar 包和源代码，源代码里面有 test 演示文件。

配置文件：
- Configuration.xml：总的配置文件，在里面配置一些数据库连接以及其他配置文件等信息。
	- enviroment 标签：配置数据库连接。
	- mapper 标签：配置其他的配置文件。
- user.xml：Sql 配置文件。配置一条select，delete 语句等给 SqlSession，使之能执行。
	- namespace 不能少。
	- resultMap 标签：配置数据库相关字段信息。
		- type：所对应的数据库实体类全限包名。
		- id：唯一的名字。
		- 子标签 id：数据库中的主键列。
		- 子标签 result：数据库中的普通列。
	- select 标签：配置 select 语句。
		- id：唯一的名字。和 resultMap 中的 id 结合可以在 Java 代码中调用此 SQL。
		- resultMap：所映射的数据库。

```
<!-- Configuration.xml -->
<environments default="development">
  <environment id="development">
    <transactionManager type="JDBC">
      <property name="" value=""/>
    </transactionManager>
    <dataSource type="UNPOOLED">
      <property name="driver" value="com.mysql.jdbc.Driver"/>
      <property name="url" value="jdbc:mysql://localhost:3306/micro_message" />
      <property name="username" value="root"/>
      <property name="password" value="yjzsgNHZok147"/>
    </dataSource>
  </environment>
</environments>

<mappers>
  <mapper resource="com/config/sqlxml/Message.xml"/>
</mappers>

<!-- Message.xml -->
<mapper namespace="Message">
  <resultMap type="com.bean.Message" id="MessageResult">
    <id column="id" jdbcType="INTEGER" property="id"/>
    <result column="command" jdbcType="VARCHAR" property="command"/>
    <result column="description" jdbcType="VARCHAR" property="description"/>
    <result column="content" jdbcType="VARCHAR" property="content"/>
  </resultMap>

  <select id="queryMessageList" parameterType="com.bean.Message" resultMap="MessageResult">
    select id, command, description, content from message where 1 = 1
  </select>   
</mapper>
```

### SQL 动态拼接
主要使用 OGNL 在 XML 配置文件中进行动态拼接，主要代码如下：
```
<select id="queryMessageList" parameterType="com.bean.Message" resultMap="MessageResult">
 select id, command, description, content from message where 1 = 1
 <!-- 双引号要转义一下，&&要转义或者替换为 and。 -->
 <!-- ? 要替换为 #{} 这个表达式 -->
 <if test="command != null and !&quot;&quot;.equals(command.trim()) ">and command = #{command}</if>
 <if test="description != null and !&quot;&quot;.equals(description.trim()) ">and description like '%' #{description} '%'</if>
</select>
```

注意以下几点：
- 在做查询时，不能同时传递两个参数，所以我们把这两个参数封装到一个 message 对象中。
- 在 XML 文件中，双引号要转义一下，? 要替换为 #{} 这个表达式。
- select 标签的 parameterType 要定义为全限包名。

### 使用 log4j 进行调试
需要导入 jar 包和配置文件两样东西，配置文件可以直接放在 src 目录下而不做任何处理。

log4j 配置文件中各个语句的含义：
- log4j.rootLogger：输出的级别和位置。这个位置只是一个代号，真正决定输出位置的是第二条语句的 ConsoleAppender 包。debug 是最低级别。
- log4j.appender.Console.layout：布局。这里使用自定义布局。
- log4j.appender.Console.layout.ConversionPattern：自定义布局。
	- %d：输出时间
	- %t：输出当前线程
	- %p：输出调试级别
	- %c：输出当前类的全限包名
	- %m：输出附加信息
	- %n：换行

```
### Global logging configuration
log4j.rootLogger=DEBUG,Console
### Console output...
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n
### personlize
log4j.logger.org.apache=INFO
```

### 实现单条删除
- 在 message.xml 中配置 delete 语句。
- 在 dao 层编写 deleteOne 方法来调用 xml 文件中的语句。
- 在 service 层调用 dao 层的 deleteOne 方法，并注意 id 转型与判空。
- 在 servlet 层调用 service 层的 deleteOne 方法，并转到`/List.action`重新初始化。
- 在 web.xml 中注册 servlet。
- 在 JSP 中用 get 方法提交表单。

```
<a href="${basePath}DeleteOneServlet.action?id=${message.id}">删除</a>
```

注意：
- 如何使用 POST 方法提交，并结合 JS 完成删除前的提示。
- Mybatis 不是自动提交，所以增删改之后要手动提交。

### 实现批量删除
处理的方法步骤大致和单条删除一样，只是细节不一样。

1、在 message.xml 中：由于批量处理的是一个 id 的List，所以用到了 foreach 标签。separator 属性使用了分隔符，Mybatis 将实现分隔功能。
```
<delete id="deleteBatch" parameterType="java.util.List">
  delete from message where id in(
    <foreach collection="list" item="item" separator=",">
	  #{item}
  	</foreach>
  )
</delete>
```

2、在 servlet 中：注意多个表单的获取方法。相应的，在 service 层需要将 String[] 转换为 `List<Integer>`。
```
String[] ids = req.getParameterValues("id");
```

3、在 list.jsp 中：
```
<!-- 导入JavaScript和jQuery -->
<script src="<%= basePath %>resources/js/list.js"></script>
<script src="<%= basePath %>resources/js/jquery-1.8.0.min.js"></script>
<!-- 将checkbox取个name，传进去value，就能及时统计id的多少 -->
<input type="checkbox" name="id" value="${message.id}"/>

<!-- 在批量删除时使用了javascript -->
<a class="btn03" href="javascript:deleteBatch('<%=basePath%>');">删 除</a>
```
4、在 list.js 中：
``` javascript
/**
 * 调用后台批量删除方法
 * @returns
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("action", basePath + "DeleteBatchServlet.action");
	$("#mainForm").submit();
}
```

### 实现自动回复功能
1、导入三个 js 框架，一个 css 文件，一个 talk.js 文件，以及一个 talk.jsp 文件。talk.jsp 就是模拟的微信公众号页面。

2、修改 talk.jsp 中的 image, css 的访问路径。

3、编写一个 InitTalkServlet 来作为对话页面初始化控制器，依次测试对话页面是否正常呈现。

4、这时可以测试一下 talk.jsp 是否显示正常。

5、编写点击发送后执行的代码。
- 首先写一个点击事件 send() 方法。
- 解析 talk.js，特别是 send() 方法。send() 方法包括了向后台发起 ajax 请求，以及向页面展示对话框。
- 编写后台自动回复功能 AutoReplyServlet，主要是将指令作为参数传进来，然后处理请求。
- 根据指令查询数据库的部分在 service 层，编写 QueryCommandById() 方法来检索指令。以及加上帮助指令。
- 测试，完成。

### 一对多关系的配置
1、创建两张表 command+command_content。command_content 中的 command_id 关联 command 中的 id。
```
CREATE TABLE command(
 ID int(11)  PRIMARY KEY NOT NULL AUTO_INCREMENT,
 NAME varchar(16),
 DESCRIPTION varchar(32)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE command_content(
 ID int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
 CONTENT varchar(2048),
 COMMAND_ID int(11),
 FOREIGN KEY (COMMAND_ID) REFERENCES command(ID)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;
```

2、编写两个实体类。注意主表中要有从表的 List 字段。
`private List<CommandContent> contentList;`

3、两个实体类对应的 xml 文件。
- 主表的 xml 文件中要注意映射从表时 select 语句的写法，以及在 resultMap 下面加上 collection 属性。
- 从表的 xml 文件仅仅包含各个字段。
- 在 Configuration.xml 中映射两个 xml 文件。

```
<!-- Command.xml -->
<resultMap type="com.bean.Command" id="Command">
  <id column="c_id" jdbcType="INTEGER" property="id"/>
  <result column="name" jdbcType="VARCHAR" property="name"/>
  <result column="description" jdbcType="VARCHAR" property="description"/>
  <collection property="contentList" resultMap="CommandContent.Content"></collection>
</resultMap>

<select id="queryCommandList" parameterType="com.bean.Command" resultMap="Command">
  <!-- 由于在加载列名时不会包含前缀(a,b) -->
  <!-- 防止两个id重复，需要给第一个id起个别名，同时在resultMap中也要修改id的column属性 -->
  select a.id c_id, a.name, a.description, b.id, b.content, b.command_id
  from command a left join command_content b
  on a.id=b.command_id
  <where>
	<if test="name != null and !&quot;&quot;.equals(name.trim()) ">and name = #{name}</if>
	<if test="description != null and !&quot;&quot;.equals(description.trim()) ">and description like '%' #{description} '%'</if>
 </where>
</select>
```

4、稍作修改 CommandDao + QueryService。

### 常用标签
- where
- sql
- set：类似于 where，解决拼接时","的问题。
- trim：可以代替 where 或者 set 标签。
- choose：if/else
- association：次表关联主表时的映射

```
<!-- sql标签 -->
<select id="queryCommandList" parameterType="com.bean.Command" resultMap="Command">
  select <include refid="columns" />
  from command a left join command_content b
  on a.id=b.command_id
</select>

<sql id="columns">
	a.id c_id, a.name, a.description, b.id, b.content, b.command_id
</sql>

<!-- set标签 -->
<update id="update">
  update message
  <set>
  	<if test="name != null">
	  	and name = #{name}
  	</if>
  	<if test="description != null">
  		and description = #{description}
  	</if>
  </set>
</update>

<!-- trim标签 -->
<!-- 代替 where -->
<trim prefix="where" prefixOverrides="and/or">
</trim>
<!-- 代替 set -->
<trim prefix="set" suffixOverrides=",">
</trim>

<!-- choose标签 -->
<choose>
	<when test=""></when>
	<when test=""></when>
	<otherwise></otherwise>
</choose>

<!-- association标签 -->
<association property="command" resultMap="Command.Command"></association>
property:在次表中 Command 的对象 command。
resultMap：主表的namespace.(resultMap)id
```

### 容易混淆的概念
- resultMap && resultType：前者是映射，所以必须写resultMap的映射文件。后者是类型，写一个类名或包名等等。后者好用但有限制。
- parameterMap && parameterType：前者已被淘汰。
- #{} && ${}：前者有预编译效果，后者没有。后者的运用场景为表头排序。
- #{} && ognl：前者除了写 `_parameter` 之外其他的也可以，后者必须写 `_parameter`。

## 一些错误
1、【未解决】在批量删除时遇到的：在 talk.jsp 中引入 jQuery 这个 js 文件时对路径报错。
```
Syntax error on token "Invalid Regular Expression Options", no accurate corr
```

## 待完成
- 修改页面和功能
- 表头排序
