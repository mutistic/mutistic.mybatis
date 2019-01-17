package com.mutistic.mybatis.utils;

import com.mutistic.mybatis.java.insert.mapper.InsertMapper;

/**
 * @program 文件工具类 
 * @description 
 * @author mutisitic
 * @date 2019年1月17日
 */
public class FileUtil {
	public static void main(String[] args) {
		showURLPath("InsertMapper.xml", InsertMapper.class);
	}
	
	/**
	 * @description 打印当前资源的URL路径 
	 * @param fileName 文件名
	 * @author mutisitic
	 * @date 2019年1月17日
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
