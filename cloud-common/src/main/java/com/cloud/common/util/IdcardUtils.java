package com.cloud.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class IdcardUtils {

	/**
	 * 中国公民身份证号码最小长度。
	 */
	public static final int CHINA_ID_MIN_LENGTH = 15;

	/**
	 * 中国公民身份证号码最大长度。
	 */
	public static final int CHINA_ID_MAX_LENGTH = 18;

	/**
	 * 每位加权因子
	 */
	public static final int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9,
			10, 5, 8, 4, 2 };

	/**
	 * 最低年限
	 */
	public static final int MIN = 1930;

	/**
	 * 第18位校检码
	 */
	public static final String verifyCode[] = { "1", "0", "X", "9", "8", "7",
			"6", "5", "4", "3", "2" };

	/**
	 * 身份证获取生日
	 *
	 * @param idcard
	 * @return 出生日期
	 */
	public static Date birthday(String idcard) {

		String year = idcard.substring(6, 10);
		String month = idcard.substring(10, 12);
		String day = idcard.substring(12, 14);

		DateFormat format = new SimpleDateFormat("yyyyMMdd");

		try {
			return format.parse(year + month + day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date birthdayStr(String idcard) {

		String year = idcard.substring(6, 10);
		String month = idcard.substring(10, 12);
		String day = idcard.substring(12, 14);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(year.concat("-").concat(month).concat("-")
					.concat(day));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String birthdayStrToStr(String idcard) {

		String year = idcard.substring(6, 10);
		String month = idcard.substring(10, 12);
		String day = idcard.substring(12, 14);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//检验用
			format.parse(year.concat("-").concat(month).concat("-").concat(day));
			return year.concat("-").concat(month).concat("-").concat(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据身份证判断性别
	 * 
	 * @return 0:男;1:女
	 */
	public static String gender(String idcard) {
		int code = Integer.parseInt(idcard.substring(16, 17));
		if ((code + 1) % 2 == 0) {
			return "男";
		}
		return "女";
	}

	/**
	 * 根据身份证判断性别
	 *
	 * @return 0:男;1:女
	 */
	public static int genderByCard(String idcard) {
		int code = Integer.parseInt(idcard.substring(16, 17));
		return (code + 1) % 2;
	}

	/**
	 * 校验身份证号是否合法
	 * 
	 * @param idCardNo
	 * @return
	 */
	public static boolean verifyIdCardNo(String idCardNo) {
		String pattern = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$";
		if (Pattern.matches(pattern, idCardNo)) {
			return true;
		}
		return false;
	}

	/**
	 * 身份证加密
	 * 
	 * @param idCard
	 * @return
	 */
	public static String checkIdCard(String idCard, String type) {
		if (idCard != null) {
			if ("01".equals(type) && idCard.length() >= 18) {
				return idCard.substring(0, 4).concat("**********")
						.concat(idCard.substring(14));
			} else {
				int leng = idCard.length() / 2;
				if (leng > 0) {
					int lengs = leng / 2;
					if (lengs > 0) {
						StringBuffer sb = new StringBuffer(idCard.substring(0,
								lengs / 2 + 1));
						for (int le = 0; le < leng; le++) {
							sb.append("*");
						}
						sb.append(idCard.substring(idCard.length() - lengs));
						return sb.toString();
					} else {
						return idCard.substring(0, 1).concat("*");
					}
				} else {
					return idCard;
				}
			}
		}
		return null;
	}

	/**
	 * 验证身份证是否合法
	 */
	public static boolean validateCard(String idCard) {
		String card = idCard.trim();
		if (validateIdCard18(card)) {
			return true;
		}
		if (validateIdCard15(card)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证18位身份编码是否合法
	 *
	 * @param idCard
	 *            身份编码
	 * @return 是否合法
	 */
	public static boolean validateIdCard18(String idCard) {
		boolean bTrue = false;
		if (idCard.length() == CHINA_ID_MAX_LENGTH) {
			// 前17位
			String code17 = idCard.substring(0, 17);
			// 第18位
			String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
			if (isNum(code17)) {
				char[] cArr = code17.toCharArray();
				int[] iCard = converCharToInt(cArr);
				int iSum17 = getPowerSum(iCard);
				// 获取校验位
				String val = getCheckCode18(iSum17);
				if (val.length() > 0) {
					if (val.equalsIgnoreCase(code18)) {
						bTrue = true;
					}
				}
			}
		}
		return bTrue;
	}

	/**
	 * 验证15位身份编码是否合法
	 *
	 * @param idCard
	 *            身份编码
	 * @return 是否合法
	 */
	public static boolean validateIdCard15(String idCard) {
		if (idCard.length() != CHINA_ID_MIN_LENGTH) {
			return false;
		}
		if (isNum(idCard)) {
			String proCode = idCard.substring(0, 2);
			// if (cityCodes.get(proCode) == null) {
			// return false;
			// }
			String birthCode = idCard.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yy").parse(birthCode
						.substring(0, 2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			if (!valiDate(cal.get(Calendar.YEAR),
					Integer.valueOf(birthCode.substring(2, 4)),
					Integer.valueOf(birthCode.substring(4, 6)))) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 数字验证
	 *
	 * @param val
	 * @return 提取的数字。
	 */
	public static boolean isNum(String val) {
		return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
	}

	/**
	 * 验证小于当前日期 是否有效
	 *
	 * @param iYear
	 *            待验证日期(年)
	 * @param iMonth
	 *            待验证日期(月 1-12)
	 * @param iDate
	 *            待验证日期(日)
	 * @return 是否有效
	 */
	public static boolean valiDate(int iYear, int iMonth, int iDate) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int datePerMonth;
		if (iYear < MIN || iYear >= year) {
			return false;
		}
		if (iMonth < 1 || iMonth > 12) {
			return false;
		}
		switch (iMonth) {
		case 4:
		case 6:
		case 9:
		case 11:
			datePerMonth = 30;
			break;
		case 2:
			boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0))
					&& (iYear > MIN && iYear < year);
			datePerMonth = dm ? 29 : 28;
			break;
		default:
			datePerMonth = 31;
		}
		return (iDate >= 1) && (iDate <= datePerMonth);
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 *
	 * @param iArr
	 * @return 身份证编码。
	 */
	public static int getPowerSum(int[] iArr) {
		int iSum = 0;
		if (power.length == iArr.length) {
			for (int i = 0; i < iArr.length; i++) {
				for (int j = 0; j < power.length; j++) {
					if (i == j) {
						iSum = iSum + iArr[i] * power[j];
					}
				}
			}
		}
		return iSum;
	}

	/**
	 * 将字符数组转换成数字数组
	 *
	 * @param ca
	 *            字符数组
	 * @return 数字数组
	 */
	public static int[] converCharToInt(char[] ca) {
		int len = ca.length;
		int[] iArr = new int[len];
		try {
			for (int i = 0; i < len; i++) {
				iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return iArr;
	}

	/**
	 * 将power和值与11取模获得余数进行校验码判断
	 *
	 * @param iSum
	 * @return 校验位
	 */
	public static String getCheckCode18(int iSum) {
		String sCode = "";
		switch (iSum % 11) {
		case 10:
			sCode = "2";
			break;
		case 9:
			sCode = "3";
			break;
		case 8:
			sCode = "4";
			break;
		case 7:
			sCode = "5";
			break;
		case 6:
			sCode = "6";
			break;
		case 5:
			sCode = "7";
			break;
		case 4:
			sCode = "8";
			break;
		case 3:
			sCode = "9";
			break;
		case 2:
			sCode = "x";
			break;
		case 1:
			sCode = "0";
			break;
		case 0:
			sCode = "1";
			break;
		}
		return sCode;
	}

	/**
	 * 根据身份证计算年龄
	 * @param idCard 身份证号
	 * @return age 年龄
	 */
	public static int getAgeByIdCard(String idCard) {
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String birthday = idCard.substring(6, 14);
		try {
			DateFormat idCardDateFormat = new SimpleDateFormat("yyyyMMdd");
			return DateUtils.getAgeByBirthdayForJoda(idCardDateFormat.parse(birthday));
		} catch (ParseException ex) {
			return 0;
		}
	}

	/**
	 * 将15位身份证号码转换为18位
	 * @param idCard
	 * @return
	 */
	public static String conver15CardTo18(String idCard) {
		String idCard18 = "";
		if (idCard.length() != CHINA_ID_MIN_LENGTH) {
			return null;
		}
		if (isNum(idCard)) {
			// 获取出生月日
			String birthday = idCard.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			// 获取出生年
			String sYear = String.valueOf(cal.get(Calendar.YEAR));
			idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
			// 转换字符数组
			char[] cArr = idCard18.toCharArray();
			if (cArr != null) {
				int[] iCard = converCharToInt(cArr);
				int iSum17 = getPowerSum(iCard);
				// 获取校验位
				String sVal = getCheckCode18(iSum17);
				if (sVal.length() > 0) {
					idCard18 += sVal;
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		return idCard18;
	}

	public static void main(String[] args){
		System.out.println(verifyIdCardNo("110114201201015437"));
	}
}
