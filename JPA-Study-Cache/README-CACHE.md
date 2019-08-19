# JPA-Study-Cache
1：JPA一级缓存和二级缓存简介
    
    见图 resources/cache.png
     
2：JPA一级缓存的使用

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
      }
    
     /**
      * 一级缓存没有命中, 将会发送两条sql.
      */
      @Test
      public void findEmployeeNotUseSessionCache(){
          //清除一级缓存不关闭
          //entityManager.clear();
    
          //一级缓存移除对象
          entityManager.detach(star);
      }

3: JPA 持久对象的状态

      /**
       * JPA 持久化的4种状态
       * 1: 瞬时状态(transient) 没有和EntityManager发生关系，没有被持久化。
       * 2: 持久化状态(persistent) 已经和EntityManager发生关系 加入到EntityManager的一级缓存中。
       * 3: 游离状态(detached) 已经被持久化，但不被EntityManager管理。
       * 4: 删除状态(removed) 计划被删除，entityManager.delete(Entity)。
       */