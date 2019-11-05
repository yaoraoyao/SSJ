package cn.itsource.ssj.service;

import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.query.BaseQuery;
import cn.itsource.ssj.query.EmployeeQuery;
import cn.itsource.ssj.util.Page;

import java.util.List;

public interface IBaseService<T> {

    void save(T t);

    void delete(Long id);

    T findOne(Long id);

    Page<T> findAll(BaseQuery baseQuery);

    /**
     * 查询所有数据[有可能有查询条件] 不分页
     * @param baseQuery
     * @return
     */
    List<T> find(BaseQuery baseQuery);
}
