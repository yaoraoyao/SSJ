package cn.itsource.ssj.dao.impl;

import cn.itsource.ssj.dao.IEmployeeDao;
import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.query.EmployeeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements IEmployeeDao {

    /**
     * @Autowired表示优先通过类型注入，如果该类型的bean有多个则再按名称注入，如果都没找到就抛异常NoSuchBeanDefinitionException
     * @PersistenceContext表示持久上下文，优先通过类型注入，如果该类型的bean有多个则再按名称注入，如果都没找到就创建一个目标对象来注入
     */
    @PersistenceContext
    private EntityManager entityManager;


}
