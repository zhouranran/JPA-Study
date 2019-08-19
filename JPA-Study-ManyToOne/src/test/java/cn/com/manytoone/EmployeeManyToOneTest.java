package cn.com.manytoone;

import cn.com.star.manytoone.Department;
import cn.com.star.manytoone.Employee;
import cn.com.star.util.JPAUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 单相多对一 如果一方没有Set<Employee> 则为单向多对一
 */
public class EmployeeManyToOneTest {

    /**
     * 保存一个多方和两个一方
     */
    @Before
    public void saveEmployee() {
        Employee star = new Employee();
        star.setName("Star");

        Employee eason = new Employee();
        eason.setName("Eason");

        Department department = new Department();
        department.setName("next.dev");

        star.setDepartment(department);
        eason.setDepartment(department);
        EntityManager entityManager = JPAUtil.getEntityManager();
        //得到事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();

        //持久状态
        //先存一方，再存多方可以减少两条update语句
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
        // 默认抓取策略时eager 饥渴 @ManyToOne(fetch = FetchType.EAGER)
        // 当查询多方时 会左外连接发送一条 left join sql语句
        Employee employee = entityManager.find(Employee.class,2L);
        //当运用lazy 策略后只有调用一方时才会再次发送另一条sql
        System.out.println(employee.getDepartment());
        entityManager.close();
    }
}
