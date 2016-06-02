/**   
 * @Title: StringUtil.java 
 * @Package com.autoyong.platform.util.common 
 * @Description: TODO 
 * @author liqp   
 * @date Jan 19, 2015 1:21:20 PM 
 * @version V1.0   
 */
package com.jack.common;

/**
 * 字符类操作工具
 * 
 * @Description: TODO
 * @author liqp
 * @date Jan 19, 2015 1:21:20 PM
 * 
 */
public class StringUtil {

	/**
	 * 检测String是否为空
	 * 
	 * @param s
	 * @return boolean 判断target是否为null或者' ',不为空true; 为空false
	 */
	public static boolean isNotBlank(String target) {
		return (target != null && !target.trim().equals(""));
	}

	/**
	 * 
	 * stringToArray
	 * 
	 * @Description: TODO
	 * @param
	 * @param target
	 * @param
	 * @param split
	 * @param
	 * @return
	 * @return String[]
	 * @throws
	 */
	public static String[] stringToArray(String target, String split) {
		if (target == null) {
			return null;
		}
		if (split == null) {
			return new String[] { target };
		}
		int index = target.indexOf(split);
		if (index == -1) {
			return new String[] { target };
		}
		return target.split(split);
	}

	/**
	 * format String: "a,b,c"--->"'a','b','c'" or "a;b"--->"'a','b'"
	 * 
	 * @param target
	 * @param split
	 * @return
	 */
	public static String formatString(String target, String split) {
		if (target == null || target.length() == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		String[] temp = StringUtil.stringToArray(target, split);
		for (int i = 0; i < temp.length; i++) {
			if (i == 0) {
				sb.append("'" + temp[i] + "'");
			} else {
				sb.append(",'" + temp[i] + "'");
			}
		}
		return sb.toString();
	}

	/**
	 * 将object数组转换成字符串
	 * 
	 * @param target
	 *            object数组
	 * @param split
	 *            截取字符如：','
	 * @return String 返回接取字符中间分割的字符串
	 * @eg：Object[] aa = new Object[]{aa,bb,cc}
	 *              StringUtil.objectArrToString(aa,",")
	 *              <p>
	 *              转换后 aa,bb,cc
	 *              </p>
	 */
	public static String ArrayToString(Object[] target, String split) {
		if (target == null || target.length < 0) {
			return null;
		}
		if (split == null) {
			split = ",";
		}

		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < target.length - 1; i++) {
			temp.append(target[i] == null ? "" : target[i]).append(split);
		}

		temp.append(target[target.length - 1]);
		return temp.toString();
	}

	/**
	 * 根据key，获得KEY值并转为字符串返回 getParameters
	 * 
	 * @Description: TODO
	 * @param
	 * @param name
	 * @param
	 * @param map
	 * @param
	 * @return
	 * @return String
	 * @throws
	 */
	public static String getParameters(String name,
			java.util.Map<String, Object> map) {
		Object obj = map.get(name);
		String val = "";
		if (obj instanceof String[]) {
			String[] strs = (String[]) obj;
			val = java.util.Arrays.toString(strs);
			val = val.substring(1, val.length() - 1);
		} else {
			val = obj.toString();
		}
		return val;
	}
	
	
    public static void assertNotNull(final Object object, final String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
