package com.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.entity.Page;

/**
 * 分页拦截器
 * @author Kexin_Li
 *
 */
// 确定拦截时机，即拦截StatementHandler下的带Connection, Integer参数的prepare方法。
@Intercepts(value = { @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}) })
public class PageInterceptor implements Interceptor{

	@Override
	/**
	 * 完成拦截下来需要做的事：即将sql语句变为具有分页的sql
	 * 拦截下来的对象需要提供：原始sql语句和配置参数。invocation这个参数中就有被拦截下来的对象(即实现了StatementHandler接口的对象)
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		// 获取拦截对象
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// 可以看做和statementHandler一样，只不过metaObject提供了方法使得我们很方便地访问属性
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		// 通过metaObject拿到参数
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		// 配置文件中sql语句的id(即queryMessageListByPage)
		String id = mappedStatement.getId();
		// 正则表达式判断id如果是我们需要拦截的对象，且行为匹配。那么去拿到原始sql语句和配置参数
		if (id.matches(".+ByPage$")) {
			// 通过boundSql拿到原始sql语句，boundSql在PrepareStatement中
			BoundSql boundSql = statementHandler.getBoundSql();
			// 原始sql语句
			String sql = boundSql.getSql();
			// 查询总条数的sql语句(子查询)，自己写sql的执行语句(connection-statement-setParameters-ResultSet-setTotalNumber)
			String countSql = "select count(*) from (" + sql + ")a";
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();
			// 通过boundSql拿到配置参数
			Map<?, ?> parameter = (Map<?, ?>) boundSql.getParameterObject();
			Page page = (Page) parameter.get("page");
			// 设置总条数
			if (rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			// 改造后的带分页查询的sql语句
			String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
			// 将改造后的sql语句替换原来的sql语句
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		// 交回主权, proceed()方法就是一个反射
		return invocation.proceed();
	}

	@Override
	/**
	 * 参数target为拦截的对象
	 * 该方法实现的就是询问拦截的对象是否为真正需要被拦截的对象
	 * 通过注解判断被拦截的对象，如果不是，则返回原来的对象
	 * 如果是，则与代理类达成一种协议，返回的就是一个代理类
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {		
	}
	
}
