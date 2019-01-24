package com.mutistic.mybatis.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 验证工具类
 * 
 * @author YC.YIN
 */
public class ValidateUtil {

	// 正则表达式-begin
		/** 密码-正则表达式 ：6-18位，必须包含一位字母、必须包含一位数字，支持特殊符号~!@#$%^&*+=*/
	public final static String  REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)([0-9A-Za-z]|[~!@#$%^&*+=]){6,18}$";
	/** 手机号码-正则表达式 eg:18755409000 */
	public final static String  REGEX_PHONE = "^1\\d{10}$";
	/*非中国大陆手机号*/
	public final static String  REGEX_PHONE_INTERNATIONAL = "^\\d{6,10}$";
	/** 电话号码-正则表达式 eg:021-6646871 */
	public final static String  REGEX_TELEP = "^(\\d{3,4}-)?\\d{7,8}$";
	/** 客服电话-正则表达式 eg:400-800-5520*/
	public final static String  REGEX_CUSTOMER = "^(\\d{3}-)(\\d{3}-)\\d{4}$";
	/** 组织机构代码-正则表达式 eg: 46587412-9 */ 
	public final static  String BRANCH_CODE = "[a-zA-Z0-9]{8}-[a-zA-Z0-9]";
	/** 统一社会信用代码-正则表达式 eg:12330400068392995K */
	public final static  String SOCIAL_CREDIT_CODE = "[0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}";
	/** 十五位身份证号码-正则表达式  简化版：^\\d{15}$ */
	public final static String REGEX_IDCARD_15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";
	/** 十八位身份证号码-正则表达式  简化版：^\\d{17}[0-9Xx]$ */
	public final static String REGEX_IDCARD_18 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
	/*车牌号正则-表达式（包括新能源）*/
	public final static String REGEX_PLATE_NO = "^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z](([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z][A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳使领]))$";
	/*车辆载重-正则表达式（最多4位，小数点2位）*/
	public final static String REGEX_LOAD_WEIGHT = "^(([1-9]{1}\\d{0,3})|(0{1}))(\\.\\d{0,2})?$";
	// 正则表达式-end
	
	private final static String LEFT = "[";
	private final static String RIGHT = "]";
	private final static String NULL = "不能为空！";
	private final static String SIZE = "大小不能超过";
	private final static String LENGTH = "长度不能超过";
	private final static String CHAR = "字符！";
	private final static String ILLEGAL = "不合法！";
	private final static String NUMERIC = "必须为数值！";
	private final static String HAN = "必须为汉字！";
	private final static String IRREGULAR = "不规范,请重新填写";

	public static void exception(String message) {
		throw new IllegalArgumentException(message);
	}

	public static void exception(String message, Object key) {
		exception(key == null ? message : LEFT + key + RIGHT + message);
	}

	/**
	 * 验证Object非空
	 * 
	 * @param obj
	 * @param message
	 */
	public static void notNull(Object obj, String message) {
		if (ObjectUtils.isEmpty(obj)) {
			exception(message);
		}
	}

	/**
	 * 验证Long非空和大小
	 * 
	 * @param value
	 * @param max
	 * @param message
	 */
	public static void notMax(Long value, Long max, String message) {
		notNull(value, message + NULL);
		if (value.longValue() > max.longValue()) {
			exception(message + SIZE + max);
		}
	}

	/**
	 * 验证Long大小
	 * 
	 * @param value
	 * @param max
	 * @param message
	 */
	public static void max(Long value, Long max, String message) {
		if (null != value && value.longValue() > max.longValue()) {
			exception(message + SIZE + max);
		}
	}

	/**
	 * 验证字符串非空和长度
	 * 
	 * @param text
	 * @param message
	 */
	public static void notLength(String text, Integer max, String message) {
		notNull(text, message + NULL);
		if (text.trim().length() > max.intValue()) {
			exception(message + LENGTH + max + CHAR);
		}
	}

	/**
	 * 验证字符串长度
	 * 
	 * @param text
	 * @param message
	 */
	public static void length(String text, Integer max, String message) {
		if (!StringUtils.isEmpty(text) && text.trim().length() > max.intValue()) {
			exception(message + LENGTH + max + CHAR);
		}
	}

	/**
	 * 校验参数是否为数字
	 * @param text
	 * @param message
	 */
	public static void notNumber(String text,String message) {
		notNull(text, message + NULL);
        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(text).find();
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(text).find();
        if(!(isInt || isDouble)) {
        	exception(message + NUMERIC);
        }
	}

	/**
	 * 校验参数是否为汉字
	 * @param text
	 * @param message
	 */
	public static void notHan(String text,String message) {
		notNull(text, message + NULL);
		boolean isHan = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(text).find();
		if(!isHan) {
			exception(message + HAN);
		}
	}

	/**
	 * 验证非空且是否是手机号码或电话号码
	 * 
	 * @param text
	 * @param message
	 */
	public static void notMobile(String text, String message) {
		notNull(text, message + NULL);
		if (!text.matches(REGEX_PHONE) && !text.matches(REGEX_TELEP)
				&& !text.matches(REGEX_CUSTOMER)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证是否是手机号码或电话号码或客服号码
	 * 
	 * @param text
	 * @param message
	 */
	public static void mobile(String text, String message) {
		if (!StringUtils.isEmpty(text) && !text.matches(REGEX_PHONE)
				&& !text.matches(REGEX_TELEP) && !text.matches(REGEX_CUSTOMER)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证非空且是否是手机号码
	 * 
	 * @param text
	 * @param message
	 */
	public static void notPhone(String text, String message) {
		notNull(text, message + NULL);
		if (!text.matches(REGEX_PHONE)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证是否是手机号码
	 * 
	 * @param text
	 * @param message
	 */
	public static void phone(String text, String message) {
		if (!StringUtils.isEmpty(text) && !text.matches(REGEX_PHONE)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证是否是国际手机号码
	 *
	 * @param text
	 * @param message
	 */
	public static void phoneInternational(String text, String message) {
		if (!StringUtils.isEmpty(text) && !text.matches(REGEX_PHONE_INTERNATIONAL)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证非空且是否是电话号码
	 * 
	 * @param text
	 * @param message
	 */
	public static void notTelep(String text, String message) {
		notNull(text, message + NULL);
		if (!text.matches(REGEX_TELEP)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证是否是电话号码
	 * 
	 * @param text
	 * @param message
	 */
	public static void telep(String text, String message) {
		if (!StringUtils.isEmpty(text) && !text.matches(REGEX_TELEP)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证非空且是否身份证合法
	 * 
	 * @param text
	 * @param message
	 */
	public static void notIDCard(String text, String message) {
		notNull(text, message + NULL);
		if (!text.matches(REGEX_IDCARD_15) && !text.matches(REGEX_IDCARD_18)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证组织机构代码 或者社会信用代码
	 * 
	 * @param text
	 * @param message
	 */
	public static void notBandCode(String text, String message) {
		notNull(text, message + NULL);
		if (!text.matches(SOCIAL_CREDIT_CODE)) {
			exception(message + ILLEGAL);
		}
	}

	public static void notPassword(String text, String message) {
		notNull(text, message + NULL);
		if (!text.matches(REGEX_PASSWORD)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证符合正则表达式
	 * 
	 * @param text
	 * @param regex
	 * @param message
	 */
	public static void regex(String text, String regex, String message) {
		if (!StringUtils.isEmpty(text) && !text.matches(regex)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证非空且符合正则表达式
	 * 
	 * @param text
	 * @param regex
	 * @param message
	 */
	public static void notRegex(String text, String regex, String message) {
		notNull(text, message + NULL);
		if (!text.matches(regex)) {
			exception(message + ILLEGAL);
		}
	}

	/**
	 * 验证非空和数值格式
	 * @param decimal 需要验证的数值
	 * @param before 最多几位整数
	 * @param after 最多几位小数
	 * @param message 提示信息
	 */
	public static void notBigDecimal(BigDecimal decimal, Integer before, Integer after, String message) {
		notNull(decimal, message + NULL);

		String[] strArr = decimal.toString().split("\\.");
		if (strArr.length == 1) {
			if(strArr[0].length() > before) {
				exception(message + "数值格式为最多" + before + "位整数和最多" + after + "位小数.");
			}
		} else if (strArr.length == 2) {
			if(strArr[0].length() > before || strArr[1].length() > after) {
				exception(message + "数值格式为最多" + before + "位整数和最多" + after + "位小数.");
			}
		}
	}
	
	/**
	 * 验证数值格式
	 * @param decimal 需要验证的数值
	 * @param before 最多几位整数
	 * @param after 最多几位小数
	 * @param message 提示信息
	 */
	public static void bigDecimal(BigDecimal decimal, Integer before, Integer after, String message) {
		if (ObjectUtils.isEmpty(decimal)) {
			return;
		}

		String str = decimal.toString().replaceAll("-", "");
		String[] strArr = str.split("\\.");
		
		if (strArr.length == 1) {
			if(strArr[0].length() > before) {
				exception(message + "数值格式为最多" + before + "位整数和最多" + after + "位小数.");
			}
		} else if (strArr.length == 2) {
			if(strArr[0].length() > before || strArr[1].length() > after) {
				exception(message + "数值格式为最多" + before + "位整数和最多" + after + "位小数.");
			}
		}
	}

	/**
	 * 验证是否是车牌（包括新能源）
	 * @param text
	 * @param message
	 */
	public static void notPlateNo(String text, String message) {
		notNull(text, message + NULL);
		boolean isPlateNo = Pattern.compile(REGEX_PLATE_NO).matcher(text).find();
		if(!isPlateNo) {
			exception(message + IRREGULAR);
		}
	}

	/**
	 * 验证是否符合载重输入规范
	 * @param text
	 * @param message
	 */
	public static void notLoadWeight(String text, String message) {
		notNull(text, message + NULL);
		boolean isLoadWeight = Pattern.compile(REGEX_LOAD_WEIGHT).matcher(text).find();
		if(!isLoadWeight) {
			exception(message + IRREGULAR);
		}
	}
	
	/**
	 * 
	* @Title: isNumber 
	* @Description: 金额验证
	* @author biwb
	* @param @param str
	* @param @return 参数
	* @return boolean    返回类型 
	* @throws
	 */
   public static boolean isAmtNumber(String str){ 
        Pattern pattern=Pattern.compile("^(([1-9]{1,9}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str); 
        if(match.matches()==false){ 
           return false; 
        }else{ 
           return true; 
        } 
   }

}
