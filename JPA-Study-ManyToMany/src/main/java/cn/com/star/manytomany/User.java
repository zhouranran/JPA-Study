package cn.com.star.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(name ="user_name" ,length = 100)
    private String userName;
    @ManyToMany
    //joinTable中间表信息 name=“中间表表名”
    @JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "t_user_id"), inverseJoinColumns = @JoinColumn(name = "t_role_id"))
    Set<Role> roles = new HashSet<Role>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
