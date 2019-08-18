package cn.com.star.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 核心API的简介
 * Persistence   主要是创建EntityManagerFactory, 根据传入的   <persistence-unit name="cn.com.star">名称来创建相应的factory
 * EntityManagerFactory  EntityManger 工厂负责创建EntityManager
 * EntityManager    实体管理对象 调用具体的 persist merge remove find findAll
 * EntityTransaction 事务管理对象
 *
 */
public class JPAUtil {
        private static EntityManagerFactory entityManagerFactory;

        static {
            // <persistence-unit name="cn.com.star">
            String persistName = "cn.com.star";
            // 获取实例化管理工厂
            entityManagerFactory = Persistence.createEntityManagerFactory(persistName);
        }

        public static EntityManager getEntityManager(){
            //获取实例管理
            return entityManagerFactory.createEntityManager();
        }
}
