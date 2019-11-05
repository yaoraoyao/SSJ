package cn.itsource.ssj.service.impl;

import cn.itsource.ssj.dao.IBaseDao;
import cn.itsource.ssj.dao.IEmployeeDao;
import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.query.BaseQuery;
import cn.itsource.ssj.query.EmployeeQuery;
import cn.itsource.ssj.service.IBaseService;
import cn.itsource.ssj.service.IEmployeeService;
import cn.itsource.ssj.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    private IBaseDao<T> baseDao;

    @Override
    @Transactional
    public void save(T t) {
        baseDao.save(t);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        baseDao.delete(id);
    }

    @Override
    public T findOne(Long id) {
        return (T)baseDao.findOne(id);
    }

    @Override
    public Page<T> findAll(BaseQuery baseQuery) {
        List<T> list = baseDao.findAll(baseQuery);
        Long total = baseDao.findCount(baseQuery);
        return new Page<T>(baseQuery.getPageNo(),baseQuery.getPageSize(),total,list);
    }

    /**
     * 查询所有数据[有可能有查询条件] 不分页
     * @param baseQuery
     * @return
     */
    @Override
    public List<T> find(BaseQuery baseQuery) {
        return baseDao.find(baseQuery);
    }
}
