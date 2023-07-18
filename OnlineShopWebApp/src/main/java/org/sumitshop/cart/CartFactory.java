package org.sumitshop.cart;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.hibernate.SessionFactory;

import jakarta.servlet.ServletContext;

public class CartFactory {
    public static Cart getInstance(ServletContext application) {
        try {
            String cartClassName = application.getInitParameter("cart");
            Class<?> cartClass = Class.forName(cartClassName);
            Constructor<?> cartConstructor = cartClass.getConstructor(SessionFactory.class);
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Object objCart = cartConstructor.newInstance(sessionFactory);
            return (Cart) objCart;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
