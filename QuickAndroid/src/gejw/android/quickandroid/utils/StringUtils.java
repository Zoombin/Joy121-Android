package gejw.android.quickandroid.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * Email格式验证
	 * 
	 * @param eMAIL1
	 * @return
	 */
	public static boolean EmailFormat(String eMAIL1) {// 邮箱判断正则表达式
		Pattern pattern = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher mc = pattern.matcher(eMAIL1);
		return mc.matches();
	}
}
