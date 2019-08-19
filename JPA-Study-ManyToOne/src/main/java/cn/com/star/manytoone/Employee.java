package cn.com.star.manytoone;

import org.hibernate.FetchMode;

import javax.naming.Name;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 多对一
 */
@Entity
@Table(name = "t_emp")
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    @Column(length = 100)
    private String name;
    @Column(length = 20)
    private Integer age;
    //多方配置ManyToOne 如果一方没有Set<Employee> 则为单向多对一
    @ManyToOne(fetch = FetchType.LAZY)
    //默认设置ManyToOne会自动建立外键, 外键名称为 属性名_id
    //不能使用@Column(name = "dep_id")
    @JoinColumn(name = "dep_id")
    private Department department;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
