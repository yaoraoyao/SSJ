package cn.itsource.ssj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    //@JsonIgnoreProperties 转化为JSON字符串的时候忽略这些属性，因为懒加载生成的子类中额外添加了这三个字段，但是并没有提供get方法
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Department department;

    private BigDecimal salary;

    private Date hireDate;

    @Column(length = 50)
    private String headImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", headImage='" + headImage + '\'' +
                '}';
    }
}
