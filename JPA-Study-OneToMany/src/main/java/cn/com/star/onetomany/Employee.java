package cn.com.star.onetomany;

import javax.persistence.*;

/**
 * 一对多
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
    @ManyToOne(fetch = FetchType.LAZY)
    //双向外键必须一致
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
