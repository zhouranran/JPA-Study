package cn.com.star.onetomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_dep")
public class Department {
    @Id
    @GeneratedValue
    private long id;
    @Column(name="dep_name", length = 150)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    //如果没有设置joinColumn则会自动生成第三张表 t_dep_t_emp
    @JoinColumn(name = "dep_id")
    private Set<Employee> employees;

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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
