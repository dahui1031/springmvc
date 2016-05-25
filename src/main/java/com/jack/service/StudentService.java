/**     
 * @Title: StudentService.java   
 * @Package com.jack.service   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年5月24日 下午1:19:29   
 * @version V1.0     
 */ 
package com.jack.service;

import java.util.List;

import com.jack.model.Student;

/**   
 * @ClassName: StudentService   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年5月24日 下午1:19:29   
 *      
 */
public interface StudentService {
  public List<Student> list(int id);
  public int insertStudent(Student student);
}
