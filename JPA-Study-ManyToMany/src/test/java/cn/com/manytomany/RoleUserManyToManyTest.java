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
public class RoleUserManyToManyTest {

    /**
     * 多对多
     */
    @Before
    public void saveRoleAndUser() {
        Role role1 = new Role();
        role1.setRoleName("1");
        Role role2 = new Role();
        role1.setRoleName("1");
        Role role3 = new Role();
        role1.setRoleName("1");

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

        entityManager.persist(role1);
        entityManager.persist(role2);
        entityManager.persist(role3);
        transaction.commit();
        entityManager.close();

    }

    @Test
    public void find() {
    }
}
