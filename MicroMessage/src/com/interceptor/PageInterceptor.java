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
 * ��ҳ������
 * @author Kexin_Li
 *
 */
// ȷ������ʱ����������StatementHandler�µĴ�Connection, Integer������prepare������
@Intercepts(value = { @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}) })
public class PageInterceptor implements Interceptor{

	@Override
	/**
	 * �������������Ҫ�����£�����sql����Ϊ���з�ҳ��sql
	 * ���������Ķ�����Ҫ�ṩ��ԭʼsql�������ò�����invocation��������о��б����������Ķ���(��ʵ����StatementHandler�ӿڵĶ���)
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		// ��ȡ���ض���
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// ���Կ�����statementHandlerһ����ֻ����metaObject�ṩ�˷���ʹ�����Ǻܷ���ط�������
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		// ͨ��metaObject�õ�����
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		// �����ļ���sql����id(��queryMessageListByPage)
		String id = mappedStatement.getId();
		// ������ʽ�ж�id�����������Ҫ���صĶ�������Ϊƥ�䡣��ôȥ�õ�ԭʼsql�������ò���
		if (id.matches(".+ByPage$")) {
			// ͨ��boundSql�õ�ԭʼsql��䣬boundSql��PrepareStatement��
			BoundSql boundSql = statementHandler.getBoundSql();
			// ԭʼsql���
			String sql = boundSql.getSql();
			// ��ѯ��������sql���(�Ӳ�ѯ)���Լ�дsql��ִ�����(connection-statement-setParameters-ResultSet-setTotalNumber)
			String countSql = "select count(*) from (" + sql + ")a";
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();
			// ͨ��boundSql�õ����ò���
			Map<?, ?> parameter = (Map<?, ?>) boundSql.getParameterObject();
			Page page = (Page) parameter.get("page");
			// ����������
			if (rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			// �����Ĵ���ҳ��ѯ��sql���
			String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
			// ��������sql����滻ԭ����sql���
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		// ������Ȩ, proceed()��������һ������
		return invocation.proceed();
	}

	@Override
	/**
	 * ����targetΪ���صĶ���
	 * �÷���ʵ�ֵľ���ѯ�����صĶ����Ƿ�Ϊ������Ҫ�����صĶ���
	 * ͨ��ע���жϱ����صĶ���������ǣ��򷵻�ԭ���Ķ���
	 * ����ǣ������������һ��Э�飬���صľ���һ��������
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {		
	}
	
}
