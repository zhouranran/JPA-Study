package cn.com.star;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;

/**
 * Unit test for employee entity test.
 */
public class EmployeeTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void saveEmployee(){
        Employee employee = new Employee();
        employee.setName("Star");
        employee.setPassword("123456");

        // <persistence-unit name="cn.com.star">
        String persistName = "cn.com.star";
        // 获取实例化管理工厂
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistName);
        //获取实例管理
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //得到事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //持久状态
        entityManager.persist(employee);
        //提交事务
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();

    }
}
