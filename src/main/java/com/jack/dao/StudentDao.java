/**     
 * @Title: StudentDao.java   
 * @Package com.jack.dao   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年5月24日 下午1:09:38   
 * @version V1.0     
 */ 
package com.jack.dao;

import java.util.List;

import com.jack.model.Student;

/**   
 * @ClassName: StudentDao   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年5月24日 下午1:09:38   
 *      
 */
public interface StudentDao {

	public List<Student> queryStudentById(int id);
	public int insertStudent(Student student);
}
