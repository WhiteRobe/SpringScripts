import lombok.Cleanup;
import lombok.val;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
import pers.model.domain.Student;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@SuppressWarnings("unchecked")
public class HibernateTest {
    @Test
    public void testSelect(){
        // 1.创建Configuration对象并加载hibernate.cfg.xml配置文件
        Configuration config = new Configuration().configure();
        // 2.获取SessionFactory
        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        // 3.得到一个Session
        @Cleanup Session session = sessionFactory.openSession();
        // 4.开启事务
        Transaction transaction = session.beginTransaction();
        // 5.执行持久化操作(查询)
        session.createQuery("FROM Student").list().forEach(System.out::println);
        session.save(new Student());
        System.out.println(session.get(Student.class, 1));
        // 6.提交事务
        transaction.commit();
        // 7.关闭资源
        // session.close();
        // sessionFactory.close();
    }

    @Test
    public void testHQLSelectById(){
        // 1.创建Configuration对象并加载hibernate.cfg.xml配置文件
        Configuration config = new Configuration().configure();
        // 2.获取SessionFactory
        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        // 3.得到一个Session
        @Cleanup Session session = sessionFactory.openSession();
        // 4.开启事务
        Transaction transaction = session.beginTransaction();
        // 5.执行持久化操作(查询)
        val hql = "SELECT s FROM Student s WHERE s.id = :id";
        session.createQuery(hql).setParameter("id", 2).list().forEach(System.out::println);
        // 6.提交事务
        transaction.commit();

    }


    @Test
    public void testQBCSelectById(){
        // 1.创建Configuration对象并加载hibernate.cfg.xml配置文件
        Configuration config = new Configuration().configure();
        // 2.获取SessionFactory
        @Cleanup SessionFactory sessionFactory = config.buildSessionFactory();
        // 3.得到一个Session
        @Cleanup Session session = sessionFactory.openSession();
        // 4.开启事务
        Transaction transaction = session.beginTransaction();

        // 5.执行持久化操作(查询)
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
        Root<Student> root = criteria.from(Student.class);
        criteria.where(builder.equal(root.get("id"), 1));

        System.out.println(session.createQuery(criteria).getSingleResult());

        // 6.提交事务
        transaction.commit();
    }
}
