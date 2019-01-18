package com.mutistic.mybatis.java.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mutistic.mybatis.java.resources.ResourcesTest;
import com.mutistic.mybatis.java.select.mapper.SelectMapper;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program SqlSession 数据库会话工具类
 * @description
 * @author mutisitic
 * @date 2019年1月16日
 */
public class SqlSeesionUtil {

	/** Mybatis Config 配置文件名称 */
	private final static String MYBATIS_CONFIG_XML = "mybatis-config.xml";
	/** SqlSessionFactory工厂类 */
	private static SqlSessionFactory sqlSessionFactory;
	/** SqlSession数据库会话 */
	private static SqlSession sqlSession;

	/**
	 * @description 创建SqlSessionFactory工厂类
	 * @author mutisitic
	 * @date 2019年1月17日
	 * @return
	 */
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

	/**
	 * @description 创建SqlSession数据库会话
	 * @author mutisitic
	 * @date 2019年1月17日
	 * @return
	 */
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

	/**
	 * @description 获取MapperClass实例对象
	 * @author mutisitic
	 * @date 2019年1月17日
	 * @param mapperClass
	 * @return
	 */
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

	/**
	 * @description 提交SqlSeession 
	 * @author mutisitic
	 * @date 2019年1月17日
	 */
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
	
	/**
	 * @description 关闭SqlSeession 
	 * @author mutisitic
	 * @date 2019年1月17日
	 */
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
