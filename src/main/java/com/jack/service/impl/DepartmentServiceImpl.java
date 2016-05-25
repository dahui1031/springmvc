/**     
 * @Title: DepartmentServiceImpl.java   
 * @Package com.jack.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年4月25日 下午1:33:29   
 * @version V1.0     
 */ 
package com.jack.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.dao.DepartmentDao;
import com.jack.dao.StudentDao;
import com.jack.model.Department;
import com.jack.model.Student;
import com.jack.service.DepartmentService;

/**   
 * @ClassName: DepartmentServiceImpl   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年4月25日 下午1:33:29   
 *      
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
    private DepartmentDao departmentDao;
	
	@Autowired
	private StudentDao studentDao;
	/** 
	 *  
	 * <p>Title: insertDepart</p>   
	 * <p>Description: </p>   
	 * @param department
	 * @return   
	 * @see com.jack.service.DepartmentService#insertDepart(com.jack.model.Department)   
	 */   
	public int insertDepart(Department department) {
		//测试事务
		departmentDao.insertDepart(department);
		Student stu=new Student();
		//stu.setDepartId(1);
		stu.setName("fuck2");
		stu.setSex("1");
		stu.setBirthday(new Date());
		studentDao.insertStudent(stu);
		return 1;
	}

}
