/**     
 * @Title: StudentServiceImpl.java   
 * @Package com.jack.service.impl   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年5月24日 下午2:24:31   
 * @version V1.0     
 */ 
package com.jack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.dao.StudentDao;
import com.jack.model.Student;
import com.jack.service.StudentService;

/**   
 * @ClassName: StudentServiceImpl   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年5月24日 下午2:24:31   
 *      
 */
@Service
public class StudentServiceImpl implements StudentService {
   
	@Autowired
	private StudentDao studentDao;
	public List<Student> list(int id) {
		List<Student> stu=studentDao.queryStudentById(id);
		return stu;
	}
	/** 
	 *  
	 * <p>Title: insertStudent</p>   
	 * <p>Description: </p>   
	 * @param student
	 * @return   
	 * @see com.jack.service.StudentService#insertStudent(com.jack.model.Student)   
	 */   
	public int insertStudent(Student student) {
		return studentDao.insertStudent(student);
	}

}
