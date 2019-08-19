package cn.com.star.onetomany;

import cn.com.star.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashSet;
import java.util.Set;

/**
 * 单相多对一 如果一方没有Set<Employee> 则为单向多对一
 */
public class EmployeeOneToManyTest {

    /**
     * 保存一个多方和两个一方
     */
    @Before
    public void saveEmployee() {
        Set<Employee> employees = new HashSet<>();
        Employee star = new Employee();
        star.setName("Star");

        Employee eason = new Employee();
        eason.setName("Eason");

        employees.add(star);
        employees.add(eason);

        Department department = new Department();
        department.setName("next.dev");

        department.setEmployees(employees);
        EntityManager entityManager = JPAUtil.getEntityManager();
        //得到事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();

        //持久状态
        //单向一对多不能减少insert 和 update语句,而单向多对一可以减少两条update语句
        /**
         * Hibernate: insert into t_dep (dep_name, id) values (?, ?)
         * Hibernate: insert into t_emp (age, name, id) values (?, ?, ?)
         * Hibernate: insert into t_emp (age, name, id) values (?, ?, ?)
         * Hibernate: update t_emp set dep_id=? where id=?
         * Hibernate: update t_emp set dep_id=? where id=?
         */
        entityManager.persist(department);
        entityManager.persist(star);
        entityManager.persist(eason);

        //提交事务
        transaction.commit();

        entityManager.close();
    }

    @Test
    public void find() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Department department = entityManager.find(Department.class, 1L);
        //JPA 多方都是默认延迟加载
        department.getEmployees().stream().forEach(System.out::println);
        //对于集合需要通过size判断，而不是null
        if (department.getEmployees().size() > 0) {
            System.out.println("该部门有员工");
        } else {
            System.out.println("该部门没有员工");
        }
        entityManager.close();
    }
}
