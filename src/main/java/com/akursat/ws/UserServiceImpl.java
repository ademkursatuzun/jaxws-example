/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.ws;

import com.akursat.model.Users;
import com.akursat.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author akursat
 */
@WebService(endpointInterface = "com.akursat.ws.UserService")
public class UserServiceImpl implements UserService {

    @Override
    public String find(String username) {
        Transaction trns = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("from Users where username=:username_param")
                    .setParameter("username_param", username);
            List<Users> users = query.list();
            if (users.isEmpty()) {
                return "Empty";
            } else if (users.size() == 1) {
                return users.get(0).getEmail() + users.get(0).getBirthday() + users.get(0).getSex();
            }
            session.getTransaction().commit();
            sessionFactory.close();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return "";
    }

    @Override
    public void createUser(String username, String email, String password, Date birthday, Short sex) {
        Transaction trns = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            if (find(username) != null) {
                System.out.println("User already exist!");
            } else {
                Users u = new Users();
                u.setUsername(username);
                u.setEmail(email);
                u.setBirthday(birthday);
                u.setPassword(password);
                u.setSex(sex);
                session.save(u);
                session.getTransaction().commit();
            }

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteUser(String username) {
        Transaction trns = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("delete Users where username = :username_param");
            query.setParameter("username_param", username);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println((result == 1 ? "User deleted." : "Fail!"));
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public void updateUser(String username, String email) {
        Transaction trns = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.createQuery("update Users set email = :email_param where username = :username_param ");
            query.setParameter("email_param", email);
            query.setParameter("username_param", username);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println((result == 1 ? "User updated." : "Fail!"));
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

}
