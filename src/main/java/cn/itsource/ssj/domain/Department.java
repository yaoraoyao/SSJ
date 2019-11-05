package cn.itsource.ssj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private String province;
    private String city;
    private String street;


    //多个部门被同一个部门经理管理[单向多对一]
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonIgnore  //@JsonIgnore 表示转化为JSON字符串的时候忽略当前字段
    private Employee manager;

    private String sn;

//    //一个部门有多个员工[单向一对多] mappedBy的值是对方关联本方的字段名称
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "department")
//    private List<Employee> employees = new ArrayList<>();
//
//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department() {
    }

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}
