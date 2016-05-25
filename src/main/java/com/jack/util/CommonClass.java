/**     
 * @Title: CommonClass.java   
 * @Package com.jack.util   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年4月25日 下午5:56:35   
 * @version V1.0     
 */ 
package com.jack.util;

import org.mybatis.spring.SqlSessionTemplate;

import com.jack.model.Department;


/**   
 * @ClassName: CommonClass   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年4月25日 下午5:56:35   
 *      
 */
public class CommonClass {
	private SqlSessionTemplate sqlSessionTemplate;
	public Department getUserByUserId(int userId){
		Department department = (Department)sqlSessionTemplate.selectOne("com.jack.dao.DepartmentDao.getUserByUserId", 1);
		return department;
	}
}
