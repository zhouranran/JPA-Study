package cn.com.star.cache;

import cn.com.star.util.JPAUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JPA 持久化的4种状态
 * 1: 瞬时状态(transient) 没有和EntityManager发生关系，没有被持久化。
 * 2: 持久化状态(persistent) 已经和EntityManager发生关系 加入到EntityManager的一级缓存中。
 * 3: 游离状态(detached) 已经被持久化，但不被EntityManager管理。
 * 4: 删除状态(removed) 计划被删除，entityManager.delete(Entity);
 */
public class EmployeeStatusTest {
    @Before
    public void saveEmployee() {
        Employee employee = new Employee();
        Date date = new Date();
        employee.setName("Star");
        employee.setPassword("123456");
        employee.setAge(30);
        employee.setBirthday(date);
        employee.setCreateTime(date);
        employee.setTime(date);
        employee.setSalary(new BigDecimal(5000));
        employee.setSex(true);
        employee.setText("this is a demo.");
        //以上为临时状态 transient

        EntityManager entityManager = JPAUtil.getEntityManager();
        //得到事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();

        //持久状态
        entityManager.persist(employee); //持久化状态 persistent
        //提交事务
        transaction.commit();

        entityManager.close(); // 游离状态
    }


    /**
     * JPA 的一级缓存的测试
     * 一级缓存命中条件：
     * 1: 同一个EntityManagerFactory
     * 2: 同一个EntityManager
     * 3: 对象Employee的全限定类名 + 主键叫做OID ， 需要同一个OID
     * OID = "cn.com.star.cache.Employee#1"
     */
    @Test
    public void findEmployeeUseSessionCache(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        Employee star =  entityManager.find(Employee.class,1L);
        Employee starSession = entityManager.find(Employee.class, 1L);
        //如果为true、只发一条sql、一级缓存命中
        Assert.assertTrue("内存地址不一致！没用使用一级缓存",star == starSession);
        //Employee starThree = entityManager.find(Employee.class, 2L);
        //Assert.assertTrue("内存地址不一致！没用使用一级缓存",star == starThree);
    }

    /**
     * 一级缓存没有命中, 将会发送两条sql.
     */
    @Test
    public void findEmployeeNotUseSessionCache(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        Employee star =  entityManager.find(Employee.class,1L);
        //清除一级缓存不关闭
        //entityManager.clear();

        //一级缓存移除对象
        entityManager.detach(star);
        Employee starSession = entityManager.find(Employee.class, 1L);
        Assert.assertFalse("内存地址一致！使用了一级缓存",star == starSession);
    }
}
