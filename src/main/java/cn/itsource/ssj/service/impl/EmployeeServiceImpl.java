package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.dao.IEmployeeDao;
import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.query.EmployeeQuery;
import cn.itsource.ssj.service.IEmployeeService;
import cn.itsource.ssj.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    @Autowired
    private IEmployeeDao employeeDao;


}
