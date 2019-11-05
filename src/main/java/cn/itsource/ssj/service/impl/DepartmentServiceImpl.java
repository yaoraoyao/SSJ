package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.dao.IDepartmentDao;
import cn.itsource.ssj.domain.Department;
import cn.itsource.ssj.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

    @Autowired
    private IDepartmentDao departmentDao;

    
}
