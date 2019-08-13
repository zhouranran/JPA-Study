package cn.com.star.crud;

import static org.junit.Assert.assertTrue;

import cn.com.star.crud.Employee;
import cn.com.star.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

/**
 * Unit test for employee entity test.
 */
public class EmployeeTest {
    @Before
    public void saveEmployee() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        //得到事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();

        Employee employee = new Employee();
        employee.setName("Star");
        employee.setPassword("123456");

        //持久状态
        entityManager.persist(employee);
        //提交事务
        transaction.commit();

        entityManager.close();
    }

    @Test
    public void updateEmployee() {
        //更新员工操作
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Employee employee = entityManager.find(Employee.class, 1L);
        if (employee != null) {
            employee.setName("star.zhong");
            entityManager.merge(employee);
            transaction.commit();
        } else {
            System.out.println("没有查询到id=1的employee.");
        }
        entityManager.close();
    }

    @Test
    public void deleteEmployee() {
        //删除员工信息
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Employee employee = entityManager.find(Employee.class, 1L);
        if (employee == null) {
            System.out.println("没有查询到员工数据。");
        } else {
            entityManager.remove(employee);
            transaction.commit();
        }
        entityManager.close();
    }

    @Test
    public void findAllEmployee() {
        //查询所有员工信息
        EntityManager entityManager = JPAUtil.getEntityManager();

        //jpql 查询所有employee信息 jpql 是JPA查询语句。java类名、属性名、别名大小写敏感。
        String jpql = "select o from Employee o";
        //jpql第二种写法 标准写法
        //String jpql = "select o from cn.com.star.crud.Employee o";
        //jpql 第三种写法
        //String jpql = "from Employee"
        Query query = entityManager.createQuery(jpql);
        List employeeList = query.getResultList();
        employeeList.forEach(e -> System.out.println(e));
        entityManager.close();
    }

    @Test
    public void deleteAllEmployee() {
        //删除所有员工信息
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //jpql 查询所有employee信息 jpql 是JPA查询语句。java类名、属性名、别名大小写敏感。
        String jpql = "select o from Employee o";
        //jpql第二种写法 标准写法
        //String jpql = "select o from cn.com.star.crud.Employee o";
        //jpql 第三种写法
        //String jpql = "from Employee"
        Query query = entityManager.createQuery(jpql);
        List<Employee> employeeList = query.getResultList();
        employeeList.forEach(e -> entityManager.remove(e));
        transaction.commit();
        entityManager.close();
    }
}
