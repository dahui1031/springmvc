/**     
 * @Title: Student.java   
 * @Package com.jack.model   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年5月24日 下午1:04:04   
 * @version V1.0     
 */ 
package com.jack.model;

import java.util.Date;

/**   
 * @ClassName: Student   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年5月24日 下午1:04:04   
 *      
 */
public class Student {

	private int id;
	private String name;
	private String sex;
	private Date birthday;
	private int departId;
	
	private Department department;

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return departId
	 */
	public int getDepartId() {
		return departId;
	}

	/**
	 * @param departId the departId to set
	 */
	public void setDepartId(int departId) {
		this.departId = departId;
	}

	

}
