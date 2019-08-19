package cn.com.star.manytoone;

import sun.security.util.Length;

import javax.persistence.*;

@Entity
@Table(name = "t_dep")
public class Department {
    @Id
    @GeneratedValue
    private long id;
    @Column(name="dep_name", length = 150)
    private String name;

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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
