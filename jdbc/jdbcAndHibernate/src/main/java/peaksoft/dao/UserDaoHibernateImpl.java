package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.model.User;
import peaksoft.util.UtilHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = UtilHibernate.createSessionFactory();
    private final EntityManagerFactory entityManagerFactory = UtilHibernate.createEntityManagerFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.createNativeQuery("create table users(\n" +
                    "                id serial primary key,\n" +
                    "                name varchar(50),\n" +
                    "                lastName varchar(50),\n" +
                    "                age int );").executeUpdate();

            entityManager.getTransaction().commit();
            entityManager.close();


        } catch (Exception exception) {
            System.out.println("exception createTable");
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();


            entityManager.createNativeQuery("drop table users").executeUpdate();

           entityManager.getTransaction().commit();
            entityManager.close();
            System.out.println("true");
        } catch (Exception exception) {
            System.out.println("exception dropTable");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();

            session.persist(new User(name, lastName, age));

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("error saveUsers");
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            System.out.println("no such identifier");
        }

    }

    @Override
    public List<User> getAllUsers() {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            List users = entityManager.createQuery("select c from User c").getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();
            return users;
        } catch (Exception e) {
            System.out.println("get all users error");
        }

        return null;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.createSQLQuery("truncate  table users").executeUpdate();

            session.getTransaction().commit();
            session.close();

        } catch (Exception exception) {
            System.out.println("exception cleanUsersTable");
        }
    }
}
