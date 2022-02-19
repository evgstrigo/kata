package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private static Session currentSession;


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";
        currentSession = Util.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createSQLQuery(sql).executeUpdate();
        currentSession.getTransaction().commit();
        System.out.println("Table is created or exists");
    }

    @Override
    public void dropUsersTable() {

        String sql = "drop TABLE IF EXISTS users";
        currentSession = Util.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();
        currentSession.createSQLQuery(sql).executeUpdate();
        currentSession.getTransaction().commit();
        System.out.println("Table is dropped or doesn't exist");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        currentSession = Util.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();
        currentSession.save(user);
        currentSession.getTransaction().commit();
        System.out.println("Пользователь с именем " + user.getName() + " сохранён.");
    }

    @Override
    public void removeUserById(long id) {
        currentSession = Util.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();
        User user = currentSession.get(User.class, id);
        currentSession.remove(user);
        currentSession.getTransaction().commit();
        System.out.println("Пользователь с именем " + user.getName() + " удалён.");
    }

    @Override
    public List<User> getAllUsers() {
        currentSession = Util.getSessionFactory().getCurrentSession();
        List<User> usersList = new ArrayList<>();
        currentSession.beginTransaction();
        usersList = currentSession.createQuery("from User").getResultList();
        currentSession.getTransaction().commit();
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        currentSession = Util.getSessionFactory().getCurrentSession();
        String sql = "truncate TABLE users";
        Transaction t = currentSession.beginTransaction();
        currentSession.createSQLQuery(sql).executeUpdate();
        t.commit();
        System.out.println("Table is truncated or doesn't exist");
    }

//    @Override
//    public void randomQueryTest() {
//        currentSession = Util.getSessionFactory().getCurrentSession();
//        currentSession.beginTransaction();
//        List<User> userList = currentSession
//                .createQuery("from User U where U.age > :ageParam AND U.id > :idParam")
//                .setParameter("ageParam", (byte)20)
//                .setParameter("idParam", Long.valueOf(2))
//                .getResultList();
//        currentSession.getTransaction().commit();
//        System.out.println(userList);
//    }
}
