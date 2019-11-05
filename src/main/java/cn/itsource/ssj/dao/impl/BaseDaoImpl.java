package cn.itsource.ssj.dao.impl;

import cn.itsource.ssj.dao.IBaseDao;
import cn.itsource.ssj.dao.IEmployeeDao;
import cn.itsource.ssj.domain.Employee;
import cn.itsource.ssj.query.BaseQuery;
import cn.itsource.ssj.query.EmployeeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> implements IBaseDao<T> {

    /**
     * @Autowired表示优先通过类型注入，如果该类型的bean有多个则再按名称注入，如果都没找到就抛异常NoSuchBeanDefinitionException
     * @PersistenceContext表示持久上下文，优先通过类型注入，如果该类型的bean有多个则再按名称注入，如果都没找到就创建一个目标对象来注入
     */
    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clz;

    public BaseDaoImpl(){
        //获得带有泛型的父类类型
        Type t = this.getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType) t;
            //p.getActualTypeArguments() 表示取得所有泛型 返回类型数组
            this.clz = (Class<T>) p.getActualTypeArguments()[0];
        }
    }


    @Override
    public void save(T t) {
        entityManager.merge(t);
    }

    @Override
    public void delete(Long id) {
        T t = entityManager.find(clz, id);
        entityManager.remove(t);
    }

    @Override
    public T findOne(Long id) {
        T t = entityManager.find(clz, id);
        return t;
    }

    @Override
    public List<T> findAll(BaseQuery baseQuery) {
        List<T> list = entityManager
                .createQuery("select e from " + clz.getSimpleName() + " e order by e.id desc")
                .setFirstResult(baseQuery.getBeginIndex())
                .setMaxResults(baseQuery.getPageSize())
                .getResultList();
        return list;
    }

    @Override
    public Long findCount(BaseQuery baseQuery) {
        return (Long)entityManager.createQuery("select count(e) from " + clz.getSimpleName() + " e").getSingleResult();
    }

    /**
     * 查询所有数据[有可能有查询条件] 不分页
     * @param baseQuery
     * @return
     */
    @Override
    public List<T> find(BaseQuery baseQuery) {
        List<T> list = entityManager
                .createQuery("select e from " + clz.getSimpleName() + " e")
                .getResultList();
        return list;
    }
}
