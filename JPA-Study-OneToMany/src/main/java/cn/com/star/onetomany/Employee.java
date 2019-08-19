package cn.com.star.onetomany;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
