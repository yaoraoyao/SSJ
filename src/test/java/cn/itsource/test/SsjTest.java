package cn.itsource.test;

import cn.itsource.ssj.dao.IEmployeeDao;
import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class SsjTest {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void test() throws Exception{
        System.out.println(employeeService.getClass()); //com.sun.proxy.$Proxy26
//        List<Employee> list = employeeService.findAll().getData();
//        list.forEach(e -> System.out.println(e));

    }

}
