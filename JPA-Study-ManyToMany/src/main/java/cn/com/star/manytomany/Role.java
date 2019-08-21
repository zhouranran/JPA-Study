package cn.com.star.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue
    private long id;
    @Column(name ="role_name" ,length = 100)
    private String roleName;
    //双向多对多需要双方都配置ManyToMany
    @ManyToMany(cascade = CascadeType.ALL)
    //特别注意:双向多对多的时候，需要有关联表，双向关系一定让他们在外表匹配，
    //joinColumn and inverseJoinColumn需要互换
    @JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "t_role_id"), inverseJoinColumns = @JoinColumn(name = "t_user_id"))
    Set<User> users = new HashSet<User>();
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
