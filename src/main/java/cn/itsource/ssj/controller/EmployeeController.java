package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.query.EmployeeQuery;
import cn.itsource.ssj.service.IEmployeeService;
import cn.itsource.ssj.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/index.do")
    public String index(){
        return "employee";
    }

    /**
     * @ResponseBody表示将当前方法的返回值转化为JSON格式的字符串
     *  如何转化呢？
     *      调用这个对象的所有属性的get方法，如果属性还是一个集合，则自动循环遍历这个集合，再次调用集合中保存的对象的属性的get方法
     * @param employeeQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("/page.do")
    public Page<Employee> page(EmployeeQuery employeeQuery){
        return employeeService.findAll(employeeQuery);
    }


}
