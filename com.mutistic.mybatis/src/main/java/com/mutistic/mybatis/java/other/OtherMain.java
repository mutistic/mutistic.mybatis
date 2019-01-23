package com.mutistic.mybatis.java.other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import com.mutistic.mybatis.java.other.mapper.OtherMapper;
import com.mutistic.mybatis.java.utils.SqlSeesionUtil;
import com.mutistic.mybatis.utils.PrintUtil;

/**
 * @program MyBatis处理Blob/Clob数据类型
 * @description
 * @author mutisitic
 * @date 2019年1月23日
 */
public class OtherMain {
	public static void main(String[] args) {
		OtherMapper mapper = SqlSeesionUtil.getMapper(OtherMapper.class);
		PrintUtil.one("1、 MyBatis处理Blob/Clob数据类型");

		showByInsertEntity(mapper);
		showByQueryById(mapper);
		showByQueryParams(mapper);
		SqlSeesionUtil.close();
	}

	private final static String PNG_URL = "src/main/java/com/mutistic/mybatis/java/other/";
	private static void showByInsertEntity(OtherMapper mapper) {
		PrintUtil.one("2、blob/clob数据类型的新增：");
		BizTest entity = new BizTest();
		entity.setId(System.currentTimeMillis());
		try {
			// InputStream inputStream = BizTest.class.getResourceAsStream("longBlob.png");
			FileInputStream inputStream = new FileInputStream(new File(PNG_URL + "longBlob.png"));
			byte[] longBlob = new byte[inputStream.available()];
			inputStream.read(longBlob);
			inputStream.close();
			
			entity.setLongBlob(longBlob);
		} catch (Exception e) {
			PrintUtil.err("读取文件出现异常，打印堆栈异常信息：" + e.getMessage());
		}
		entity.setLongClob("test long clob ");
		Long result = mapper.insertEntity(entity);
		SqlSeesionUtil.commit();
		PrintUtil.two("2.1、新增结果：", result);
	}
	private static void showByQueryById(OtherMapper mapper) {
		PrintUtil.one("3、blob/clob数据类型的查询：");

		BizTest entity = mapper.queryById(1548210398664l);
		PrintUtil.two("3.1、查询结果：", entity);

		try {
			FileOutputStream outputStream = new FileOutputStream(new File(PNG_URL + "longBlob2.png"));
			outputStream.write(entity.getLongBlob());
			outputStream.close();
			PrintUtil.two("3.2、将读取到的文件成功写入到：", PNG_URL + "longBlob2.png");
		} catch (Exception e) {
			PrintUtil.err("写入文件出现异常，打印堆栈异常信息：" + e.getMessage());
		}
	}
	private static void showByQueryParams(OtherMapper mapper) {
		PrintUtil.one("4、使用多个参数查询数据（不建议使用）：");
		List<BizTest> entityList = mapper.queryByParams(1548210398664l, "clob");
		PrintUtil.two("4.1、查询结果：", entityList);
	}
}
