package com.mutistic.mybatis.java.other;

import java.util.Arrays;
/**
 * @program Blob/Clob数据类型
 * @description 
 * @author mutisitic
 * @date 2019年1月23日
 */
public class BizTest {
	/** ID */
	private Long id;
	/** byte[] 对应Mysql数据库 longblob */
	private byte[] longBlob;
	/** String 对应Mysql数据库 longclob */
	private String longClob;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getLongBlob() {
		return longBlob;
	}

	public void setLongBlob(byte[] longBlob2) {
		this.longBlob = longBlob2;
	}

	public String getLongClob() {
		return longClob;
	}

	public void setLongClob(String longClob) {
		this.longClob = longClob;
	}

	@Override
	public String toString() {
		return "BizTest [id=" + id + ", longBlob=" + Arrays.toString(longBlob) + ", longClob=" + longClob + "]";
	}
}
