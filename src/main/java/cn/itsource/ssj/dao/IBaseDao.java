package cn.itsource.ssj.dao;

import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.query.BaseQuery;
import cn.itsource.ssj.query.EmployeeQuery;

import java.util.List;

public interface IBaseDao<T> {

    void save(T t);

    void delete(Long id);

    T findOne(Long id);

    List<T> findAll(BaseQuery baseQuery);

    Long findCount(BaseQuery baseQuery);

    /**
     * 查询所有数据[有可能有查询条件] 不分页
     * @param baseQuery
     * @return
     */
    List<T> find(BaseQuery baseQuery);
}
