/**     
 * @Title: DepartmentService.java   
 * @Package com.jack.service   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年3月21日 下午5:46:27   
 * @version V1.0     
 */ 
package com.jack.service;

import java.lang.annotation.Retention;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.dao.DepartmentDao;
import com.jack.model.Department;

/**   
 * @ClassName: DepartmentService   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年3月21日 下午5:46:27   
 *      
 */
public interface DepartmentService {
    public int insertDepart(Department department);

}
