package cn.com.manytomany;

import cn.com.star.manytomany.Role;
import cn.com.star.manytomany.User;
import cn.com.star.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 */
public class RoleUserManyToManyCascadeTest {

    /**
     * 多对多级联保存
     */
    @Before
    public void saveRoleAndUser() {
        Role role1 = new Role();
        role1.setRoleName("1");
        Role role2 = new Role();
        role2.setRoleName("2");
        Role role3 = new Role();
        role3.setRoleName("3");

        User eason = new User();
        eason.setUserName("eason");
        eason.getRoles().add(role1);
        eason.getRoles().add(role2);

        User star = new User();
        star.setUserName("star");
        star.getRoles().add(role1);
        star.getRoles().add(role2);
        star.getRoles().add(role3);

        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(star);
        entityManager.persist(eason);
        transaction.commit();
        entityManager.close();

    }

    /**
     * 多对多默认延迟加载
     */
    @Test
    public void find() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        User user = entityManager.find(User.class, 1L);
        System.out.println(user);
        user.getRoles().stream().forEach(t->System.out.println(t));
    }
}
