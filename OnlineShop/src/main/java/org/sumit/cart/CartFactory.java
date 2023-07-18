package org.sumit.cart;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import jakarta.servlet.ServletContext;

public class CartFactory {
    public static Cart getInstance(ServletContext application) {
        // Reflection API
        try {
            String cartClassName = application.getInitParameter("cart");
            Class<?> cartClass = Class.forName(cartClassName);
            Constructor<?> defaultCartConstructor = cartClass.getConstructor();
            Object objCart = defaultCartConstructor.newInstance();
            return (Cart) objCart;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
