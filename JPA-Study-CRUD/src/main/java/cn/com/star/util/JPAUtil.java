package cn.com.star.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
