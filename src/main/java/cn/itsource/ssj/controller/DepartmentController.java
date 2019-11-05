package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Department;
import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.query.DepartmentQuery;
import cn.itsource.ssj.query.EmployeeQuery;
import cn.itsource.ssj.service.IDepartmentService;
import cn.itsource.ssj.service.IEmployeeService;
import cn.itsource.ssj.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/index.do")
    public String index(){
        return "department";
    }

    /**
     * @ResponseBody表示将当前方法的返回值转化为JSON格式的字符串
     *  如何转化呢？
     *      调用这个对象的所有属性的get方法，如果属性还是一个集合，则自动循环遍历这个集合，再次调用集合中保存的对象的属性的get方法
     * @param departmentQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("/page.do")
    public Page<Department> page(DepartmentQuery departmentQuery){
        return departmentService.findAll(departmentQuery);
    }

    @ResponseBody
    @RequestMapping("/findDepartments.do")
    public List<Department> findDepartments(DepartmentQuery departmentQuery){
        List<Department> list = departmentService.find(departmentQuery);
        list.add(new Department(-1L, "请选择部门"));
        return list;
    }


}
