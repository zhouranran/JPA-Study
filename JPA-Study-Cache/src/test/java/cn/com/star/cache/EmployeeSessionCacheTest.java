package cn.com.star.cache;

import cn.com.star.cache.Employee;
import cn.com.star.util.JPAUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * JPA 一级缓存命中条件
 */
public class EmployeeSessionCacheTest {
    @Before
    public void saveEmployee() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        //得到事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();

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

        //持久状态
        entityManager.persist(employee);
        //提交事务
        transaction.commit();

        entityManager.close();
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
