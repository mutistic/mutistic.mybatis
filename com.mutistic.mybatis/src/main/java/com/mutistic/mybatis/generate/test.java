package com.mutistic.mybatis.generate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.mutistic.mybatis.utils.PrintUtil;

public class test {
	/** 配置文件全路径 */
	private final static String XML_FILE_URL = Thread.class.getResource("/")
			+ "com/mutistic/mybatis/generate/config/simple.xml";

	public static void main(String[] args) {
		try {
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			File configFile = new File(XML_FILE_URL.replace("file:/", ""));
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			System.out.println("success");
		} catch (Exception e) {
			PrintUtil.err(e.getMessage());
		}
	}
}
