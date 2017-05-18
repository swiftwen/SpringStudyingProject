package com.wp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.wp.common.constants.SysConstants;

/**
 * @Author：LiaoDan
 * @CreatDate:2013-7-22 下午2:01:45
 * @Description：常用工具组件
 */
public class ToolUtil {

	public static final String[] zodiacArr = { "猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊" };

	public static final String[] constellationArr = { "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };

	public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
	final static char array[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	final static int SCALE = 62;

	/**
	 * @Title: isNullOrBlank
	 * @Description:判断对象是否为空或者字符串是否为空
	 * @return boolean
	 * @throws
	 */
	public static boolean isNullOrBlank(Object o) {
		if (o instanceof String) {
			return StringUtils.isBlank((String) o);
		} else {
			return o == null ? true : false;
		}
	}

	/**
	 * @Title: append86
	 * @Description:手机号前加86
	 * @return String
	 * @throws
	 */
	public static String append86(String number) {
		if (StringUtils.isBlank(number)) {
			return "";
		} else {
			if (!number.startsWith("86")) {
				return "86" + number;
			} else {
				return number;
			}
		}
	}

	/**
	 * @Title: sub86
	 * @Description:手机号移除86
	 * @return String
	 * @throws
	 */
	public static String sub86(String number) {
		if (StringUtils.isBlank(number)) {
			return "";
		} else {
			if (number.startsWith("86")) {
				return number.substring(2);
			} else {
				return number;
			}
		}
	}

	
	/**
	 * 根据日期获取生肖
	 * 
	 * @return
	 */
	public static String getZodica(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return zodiacArr[cal.get(Calendar.YEAR) % 12];
	}

	/**
	 * 根据日期获取星座
	 * 
	 * @return
	 */
	public static String getConstellation(Date date) {
		if (date == null) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day < constellationEdgeDay[month]) {
			month = month - 1;
		}
		if (month >= 0) {
			return constellationArr[month];
		}
		return constellationArr[11];
	}

	/**
	 * @Title: getAge
	 * @Description:根据生日获取年龄
	 * @return int
	 * @throws
	 */
	public static int getAge(Date birthDay) throws Exception {
		// 获取当前系统时间
		Calendar cal = Calendar.getInstance();
		// 如果出生日期大于当前时间，则抛出异常
		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		// 取出系统当前时间的年、月、日部分
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		// 将日期设置为出生日期
		cal.setTime(birthDay);
		// 取出出生日期的年、月、日部分
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		// 当前年份与出生年份相减，初步计算年龄
		int age = yearNow - yearBirth;
		// 当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
		if (monthNow <= monthBirth) {
			// 如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth)
					age--;
			} else {
				age--;
			}
		}
		return age;
	}

	/**
	 * @Title: timeStamp2Date
	 * @Description:时间戳转化成日期字符串
	 * @return String
	 * @throws
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty())
			format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * @Title: removeDuplicateWithOrder
	 * @Description:list去重
	 * @return List
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}

	/**
	 * @Description：判断字符串是否为空或空字符串
	 * @date：2013-7-22
	 * @param str
	 * @return 是：true 否：false
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || "".equals(str) ? true : false;
	}

	/**
	 * @Description：返回当前时间指定格式的字符串类型,如：yyyyMMddHHmmss，yyyy-MM-dd
	 * @date：2013-7-22
	 * @return
	 */
	public static String formatNowDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * @Description：时间戳格式转年月日
	 * @date：2014-5-19
	 * @param vtime
	 * @param format
	 * @return 如： 1400385669--->2014-05-18 12:01:09
	 */
	public static String formatDate(long vtime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(vtime * 1000));
	}

	public static String formatDate2(long vtime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(vtime));
	}

	/**
	 * @Description:时间转字符串
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * @Description：获取WEB-INFO下面的路径
	 * @date：2013-8-6
	 * @return
	 */
	public static String getAppRoot() {
		String classPath = ToolUtil.class.getResource("/").toString();
		classPath = escape(classPath);
		classPath = classPath.substring(0, classPath.length() - 1);
		classPath = classPath.substring(0, classPath.lastIndexOf("/"));
		classPath = classPath.substring(classPath.indexOf("/") + 1, classPath.length());
		return classPath;
	}

	public static String escape(String str) {
		if (str == null)
			return null;
		return str.replace("\\", "/").replace("\\\\", "/");
	}

	/**
	 * 去除手机号码前缀
	 * 
	 * @param number
	 * @return
	 */
	public static String getSimpleNumber(String number) {
		if (number == null || "".equals(number)) {
			return number;
		}
		if (number.startsWith("86")) {
			return number.substring(2);
		} else {
			return number;
		}
	}

	/**
	 * 使用MD5加密字符串
	 * 
	 * @param encodeString
	 * @return
	 */
	public static String MD5Encode(String encodeString) {
		return DigestUtils.md5Hex(encodeString);
	}

	/**
	 * 替换一个字符串中的某些指定字符
	 *
	 * @param strData
	 *            String 原始字符串
	 * @param regex
	 *            String 要替换的字符串
	 * @param replacement
	 *            String 替代字符串
	 * @return String 替换后的字符串
	 */
	public static String replaceString(String strData, String regex, String replacement) {
		if (strData == null) {
			return null;
		}
		int index;
		index = strData.indexOf(regex);
		String strNew = "";
		if (index >= 0) {
			while (index >= 0) {
				strNew += strData.substring(0, index) + replacement;
				strData = strData.substring(index + regex.length());
				index = strData.indexOf(regex);
			}
			strNew += strData;
			return strNew;
		}
		return strData;
	}

	/**
	 * 替换字符串中特殊字符
	 */
	public static String encodeString(String strData) {
		if (strData == null) {
			return "";
		}
		if (strData.indexOf("&amp;") > 0) {
			return strData;
		}
		strData = replaceString(strData, "&", "&amp;");
		// strData = replaceString(strData, "<", "&lt;");
		// strData = replaceString(strData, ">", "&gt;");
		// strData = replaceString(strData, "&apos;", "&apos;");
		// strData = replaceString(strData, "\"", "&quot;");
		return strData;
	}

	/**
	 * 编码html
	 * 
	 * @param html
	 * @return
	 */
	public static String htmlEncode(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}
		html = replaceString(html, "&", "&amp;");
		html = replaceString(html, "<", "&lt;");
		html = replaceString(html, ">", "&gt;");
		html = replaceString(html, "\"", "&quot;");
		html = replaceString(html, "\'", "&#39;");
		html = replaceString(html, " ", "&nbsp;");
		return html;
	}

	/**
	 * 还原字符串中特殊字符
	 */
	public static String decodeString(String strData) {
		strData = replaceString(strData, "&lt;", "<");
		strData = replaceString(strData, "&gt;", ">");
		strData = replaceString(strData, "&apos;", "&apos;");
		strData = replaceString(strData, "&quot;", "\"");
		strData = replaceString(strData, "&amp;", "&");
		return strData;
	}

	/**
	 * 用“CDATA"包裹xml中的常量属性，该属性遇到 '&'、'>'等等特殊符号， 可以不需要转义
	 * 
	 * @param attrValue
	 *            待包裹的常量值
	 * 
	 * @return 包裹之后的字符串 eg <![CDATA[xxxxxx]]>
	 */
	public static String createCDATA(String attrValue) {
		return String.format("<![CDATA[%s]]>", attrValue != null ? attrValue.trim() : "");
	}

	/**
	 * 获取cookie中值
	 * @param ck
	 * @param param
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getCookieValue(Cookie[] ck, String param) throws UnsupportedEncodingException {
		String value = null;
		if (ck == null) {
			return value;
		}
		StringBuffer myCookie = new StringBuffer();
		for (Cookie cookie : ck) {
			myCookie.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
			if (cookie.getName().equals(SysConstants.Cookie_SSO_Sid)) {
				value = cookie.getValue();
			}
		}
		return value;
	}

	/**
	 * 判断是否是手机号码
	 * 
	 * @param userNumber
	 * @return boolean
	 */
	public static boolean isMobilNumber(String userNumber) {
		String moblieRegEx = "^((13[0-9])|(15[0-9])|(18[0-9])|(14[0-9])|(17[0-9]))\\d{8}$";
		Pattern mobilePattern = Pattern.compile(moblieRegEx);
		return userNumber != null && mobilePattern.matcher(userNumber).matches();
	}

	/**
	 * 判断是否移动手机号(新增178、1705号段)
	 * <ul>
	 * <li>
	 * 移动：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188
	 * ,147,178,1705</li>
	 * <li>联通：130,131,132,155,156,185,186,145,176,1709</li>
	 * <li>电信：133,153,180,181,189,177,1700</li>
	 * </ul>
	 * 
	 * @date：2016-1-13
	 * @param number
	 *            手机号码
	 * @param has86
	 *            是否手机号前面带86
	 * @return
	 */
	public static boolean isMobileNumber(String number, boolean has86) {
		String numberRegEx;
		if (has86) {
			numberRegEx = "(^861(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";
			return !isNullOrEmpty(number) && Pattern.matches(numberRegEx, number);
		} else {
			numberRegEx = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";
			return !isNullOrEmpty(number) && Pattern.matches(numberRegEx, number);
		}

		// String numberRegEx;
		// if (has86) {
		// numberRegEx =
		// "^8613[4-9][0-9]{8}$|^8615[012789][0-9]{8}$|^8618[23478][0-9]{8}$|^8614[7][0-9]{8}$";
		// return !isNullOrEmpty(number) && Pattern.matches(numberRegEx,
		// number);
		// } else {
		// numberRegEx =
		// "^13[4-9][0-9]{8}$|^15[012789][0-9]{8}$|^18[23478][0-9]{8}$|^14[7][0-9]{8}$";
		// return !isNullOrEmpty(number) && Pattern.matches(numberRegEx,
		// number);
		// }
	}

	/**
	 * @Description：将非86手机号中间4位用*代替
	 * @date：2014-6-10
	 * @param number
	 * @return 例如：158****2076
	 */
	public static String replaceMobilNumber(String number) {
		if (ToolUtil.isNullOrEmpty(number)) {
			return null;
		}
		if (number.startsWith("86")) {
			number = number.substring(2);
		}
		String regex = "(\\d{3})(\\d{4})(\\d{4})";
		Pattern patt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher mat = patt.matcher(number);
		return mat.replaceAll("$1****$3");
	}

	/**
	 * 截取
	 * 
	 * @param value
	 * @return
	 */
	public static String getShortString(String value) {
		if (StringUtils.isBlank(value)) {
			return "";
		}
		if (value.length() > 30) {
			value = StringUtils.substring(value, 0, 30);
			value = StringUtils.join(new String[] { value, "..." });
		}
		return value;
	}

	/**
	 * @Description：获取指定时间到当前时间所相距的天数
	 * @date：2014-7-10
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static long getDayAgo(Date date) throws ParseException {
		long theTime = new Date().getTime() - date.getTime();
		return (theTime / (60 * 60 * 1000 * 24)) + 1;
	}

	/**
	 * @Title: isNumStr
	 * @Description: 判断字符串是否可以转化成整形
	 * @param @param str
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean isNumStr(String str) {
		Pattern pattern = Pattern.compile("[0-9]+");
		return pattern.matcher(str).matches();
	}

	/**
	 * 获得指定位数的随机数
	 * 
	 * @param length
	 * @return String
	 */
	public static String getRandNum(int length) {
		StringBuilder sBuilder = new StringBuilder(6);
		for (int i = 0; i < length; i++) {
			sBuilder.append(String.valueOf(randomInt(0, 10)));
		}
		return sBuilder.toString();
	}

	public static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	/**
	 * 
	 * @Title: date2Str
	 * @Description: 将日期类型转化成一定格式的字符串，例如：yyyy-MM-dd hh:mm:ss
	 * @param @param pattern
	 * @param @param aDate
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static final String date2Str(String pattern, Date aDate) {
		if (null == aDate) {
			return null;
		}
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 
	 * 描述 将日期字符串转化成一定格式的日期类型
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static final Date str2Date(String str, String pattern) throws ParseException {
		DateFormat dd = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = dd.parse(str);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

	/**
	 * 获取外网ip
	 * 
	 * @param strUrl
	 * @return
	 */
	public static String getWebIp(String strUrl) {
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			String webContent = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "rn");
			}
			br.close();
			webContent = sb.toString();
			int start = webContent.indexOf("[") + 1;
			int end = webContent.indexOf("]");
			if (start < 0 || end < 0) {
				return null;
			}
			webContent = webContent.substring(start, end);
			return webContent;
		} catch (Exception e) {
			e.printStackTrace();
			return "error open url:" + strUrl;
		}
	}

	/**
	 * 过滤xss
	 * 
	 * @param content
	 * @return
	 */
	public static String filterXss(String content) {
		if (StringUtils.isBlank(content)) {
			return null;
		} else {
			content = content.replaceAll("<", "").replaceAll("&lt;", "");
			content = content.replaceAll(">", "").replaceAll("&gt;", "");
			return content;
		}
	}

	/**
	 * @Description：屏蔽身份证号码中间***
	 * @date：2014-11-17
	 * @param number
	 * @return
	 */
	public static String replaceCertNumber(String number) {
		if (ToolUtil.isNullOrEmpty(number)) {
			return "";
		}
		String regex = "";
		if (number.length() == 15) {
			regex = "(\\d{2})(\\d{10})(\\d{3})";
			Pattern patt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher mat = patt.matcher(number);
			return mat.replaceAll("$1**********$3");
		} else if (number.length() == 18) {
			regex = "(\\d{2})(\\d{13})(\\d{3})";
			Pattern patt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher mat = patt.matcher(number);
			return mat.replaceAll("$1*************$3");
		} else {
			number = number.replace(number, "**********");
			return number;
		}
	}

	/**
	 * @Description：云邮局搜索将搜索关键字在搜索结果中高亮显示,忽略大小写 key--->&<{key}>&
	 * @date：2015-1-27
	 * @param key
	 *            ：搜索关键字
	 * @param content
	 *            ：搜索返回字符串内容
	 */
	public static String replaceHighlightString(String key, String content) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(content)) {
			return content;
		}
		String reg = "(?i)" + key;// 用(?i)来忽略大小写
		StringBuffer sb = new StringBuffer();
		Matcher matcher = Pattern.compile(reg).matcher(content);
		while (matcher.find()) {
			matcher.appendReplacement(sb, "&<{" + matcher.group() + "}>&");// 这样替换以后保证了原文的大小写没有发生变化
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * @Title: decodeUnicode
	 * @Description:Unicode转中文
	 * @return String
	 * @throws
	 */
	public static String decodeUnicode(String theString) {

		char aChar;

		int len = theString.length();

		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {

			aChar = theString.charAt(x++);

			if (aChar == '\\') {

				aChar = theString.charAt(x++);

				if (aChar == 'u') {

					// Read the xxxx

					int value = 0;

					for (int i = 0; i < 4; i++) {

						aChar = theString.charAt(x++);

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';

					else if (aChar == 'n')

						aChar = '\n';

					else if (aChar == 'f')

						aChar = '\f';

					outBuffer.append(aChar);

				}

			} else

				outBuffer.append(aChar);

		}

		return outBuffer.toString();

	}

	/**
	 * @Description：字符串转date类型
	 * @date：2015年9月14日
	 */
	public static Date stringToDate(String dateString) {
		Date date = null;
		if (StringUtils.isBlank(dateString)) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(dateString);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 无限数字位数的数字压缩
	 * 
	 * @param number
	 * @return
	 */
	public static String unlimitCompressNumber(String number) {
		BigDecimal bd = new BigDecimal(number);
		BigDecimal factor = new BigDecimal(String.valueOf(SCALE));
		Stack<Character> stack = new Stack<Character>();
		StringBuilder result = new StringBuilder(0);
		MathContext mc = new MathContext(60);
		while (bd.longValue() != 0) {
			stack.add(array[bd.remainder(factor).intValue()]);
			bd = bd.divide(factor, mc);
		}
		for (; !stack.isEmpty();) {
			result.append(stack.pop());
		}
		return result.toString();

	}

	/**
	 * 无限制的解压缩
	 * 
	 * @param sixty_str
	 * @return
	 */
	public static BigDecimal unlimitUnCompressNumber(String sixty_str) {
		BigDecimal multiple = new BigDecimal("1");
		BigDecimal result = new BigDecimal("0");
		BigDecimal factor = new BigDecimal(String.valueOf(SCALE));
		Character c;
		for (int i = 0; i < sixty_str.length(); i++) {
			c = sixty_str.charAt(sixty_str.length() - i - 1);
			result = result.add(new BigDecimal(_62_value(c)).multiply(multiple));
			multiple = multiple.multiply(factor);
		}
		return result;
	}

	private static int _62_value(Character c) {
		for (int i = 0; i < array.length; i++) {
			if (c == array[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 冒泡排序降序
	 */
	public static Integer[] arraySortByDesc(Integer[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] < array[j]) {
					int temp;
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	
	/**
	 * SHA-1 加密
	 * @param str
	 * @return
	 */
	public static String getSHAStr(String str)
	{
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(str.toString().getBytes());// 对接后的字符串进行sha1加密
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数 的 字符串
			for (int i = 0; i < digest.length; i++) {
				String shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			result = hexString.toString();
		}
		catch (Exception e) {
			
		}
		return result;
	}
	
	public static void main(String[] args)
    {
		Long time = System.currentTimeMillis();
		System.out.println(time);
		String timeStr = String.valueOf(time);
		timeStr = timeStr.substring(0,timeStr.length()-3);
		System.out.println(timeStr);
    }
}
