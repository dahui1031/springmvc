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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jack.common.file.FileUp;
import com.jack.model.Department;
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
    
    @RequestMapping(value="/filepage")
    public String filepage(){
    	return "filePage";
    }
    
    @RequestMapping(value="/upload")
    public String upload(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	MultipartHttpServletRequest multiHSRequest=(MultipartHttpServletRequest) request;
    	//MultipartFile fpfiles =  multiHSRequest.getFile("file");
    	CommonsMultipartFile fpfiles =  (CommonsMultipartFile) multiHSRequest.getFile("file");
    	String filename=fpfiles.getOriginalFilename();
    	FileItem fileItem=fpfiles.getFileItem();
    	String filepath=fileItem.getName();
    	
    	//文件名字
		String fileName = filepath.substring(filepath.lastIndexOf("\\")+1);   
		System.out.println(fileName+"/////////////////////////");
    	
		String fileCode  ="";
		String ext = "";
		if(fileName.lastIndexOf(".")!=-1){
			ext = fileName.substring(fileName.lastIndexOf("."));
		}
		
		String uuid = "00000";
		fileCode = uuid + ext;
		
		FileUp fileUp = new FileUp("upload", fileCode, fileItem.getInputStream());
		fileUp.upload();
		System.out.println(fileCode+"_______________________________");
    	return "success";
    }
}
