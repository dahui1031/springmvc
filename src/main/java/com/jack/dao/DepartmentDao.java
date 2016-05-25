/**     
 * @Title: departmentDao.java   
 * @Package com.jack.dao   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年3月21日 下午5:44:22   
 * @version V1.0     
 */ 
package com.jack.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.jack.model.Department;

/**   
 * @ClassName: departmentDao   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年3月21日 下午5:44:22   
 *      
 */
public interface DepartmentDao {
	
	 public int insertDepart(Department department);  
}
