/**     
 * @Title: department.java   
 * @Package com.jack.model   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年3月21日 下午5:42:00   
 * @version V1.0     
 */ 
package com.jack.model;

/**   
 * @ClassName: department   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年3月21日 下午5:42:00   
 *      
 */
public class Department {
  private String id;
  private String departCode;
  private String departName;
  
/**   
 * <p>Title: </p>   
 * <p>Description: </p>   
 * @param id
 * @param departCode
 * @param departName   
 */ 
  public Department(){}
public Department(String id, String departCode, String departName) {
	super();
	this.id = id;
	this.departCode = departCode;
	this.departName = departName;
}
/**
 * @return id
 */
public String getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(String id) {
	this.id = id;
}
/**
 * @return departCode
 */
public String getDepartCode() {
	return departCode;
}
/**
 * @param departCode the departCode to set
 */
public void setDepartCode(String departCode) {
	this.departCode = departCode;
}
/**
 * @return departName
 */
public String getDepartName() {
	return departName;
}
/**
 * @param departName the departName to set
 */
public void setDepartName(String departName) {
	this.departName = departName;
}
  
}
