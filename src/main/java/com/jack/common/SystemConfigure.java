/**     
 * @Title: SystemConfigure.java   
 * @Package com.jack.common   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年6月2日 下午4:38:45   
 * @version V1.0     
 */ 
package com.jack.common;

import java.util.Locale;
import java.util.ResourceBundle;

/**   
 * @ClassName: SystemConfigure   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年6月2日 下午4:38:45   
 *      
 */
public class SystemConfigure {
	
	private static Locale locale=Locale.getDefault();
//	ResourceBundle会查找包languages下的systemConfigure.properties的文件   
//    languages是资源的包名，它跟普通java类的命名规则完全一样：   
//    - 区分大小写   
//    - 扩展名 .properties 省略。就像对于类可以省略掉 .class扩展名一样   
//    - 资源文件必须位于指定包的路径之下（位于所指定的classpath中）   
	private static ResourceBundle rb=ResourceBundle.getBundle("languages.systemConfigure", locale);

	public static String getValue(String key){
		return rb.getString(key).trim();
	}
}
