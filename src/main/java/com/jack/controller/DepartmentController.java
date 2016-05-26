/**     
 * @Title: DepartmentController.java   
 * @Package com.jack.controller   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author sunxh    
 * @date 2016年3月21日 下午6:51:07   
 * @version V1.0     
 */ 
package com.jack.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jack.model.Department;
import com.jack.model.Student;
import com.jack.service.DepartmentService;
import com.jack.service.StudentService;

/**   
 * @ClassName: DepartmentController   
 * @Description: TODO   
 * @author sunxh
 * @date 2016年3月21日 下午6:51:07   
 *      
 */
@Controller
public class DepartmentController {
    @Autowired
	private   DepartmentService departmentService;
    @Autowired
    private  StudentService studentService;
  
    @RequestMapping(value="/springmvc")
    public  String main(HttpServletRequest request) {
    	Department department=new Department();
		department.setDepartCode("A008");
		department.setDepartName("索隆部");
    	departmentService.insertDepart(department);
    	//request.setAttribute("hello","i am coming" );
    	return "hello";
	}
    
    @RequestMapping(value="hello")
    public String toLoginPage(){
    	//List<Student> stu=studentService.list(1);
    	//System.out.println(stu.get(0).getDepartment().getDepartCode()+".........................");
    	return "hello";
    }
    
    @RequestMapping("freeMarker/hi")
    public String sayHello(ModelMap map) {
        System.out.println("say Hello ……");
        map.addAttribute("name", " World!");
        ArrayList list=new ArrayList<Department>();
        Department department=new Department();
		department.setDepartCode("A008");
		department.setDepartName("索隆部");
		list.add(department);
		Department department1=new Department();
		department1.setDepartCode("A009");
		department1.setDepartName("海鲜部");
		list.add(department1);
		map.addAttribute("list", list);
        return "/hello.ftl";

    }
    
}
