package org.sumit.mvcuserapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sumit.mvcuserapp.beans.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
    SessionFactory hibernateFactory;
	
	@Override
	public boolean newProduct(Product objProduct) {
		try (Session hibernateSession = hibernateFactory.openSession()) {
            hibernateSession.beginTransaction();
            hibernateSession.save(objProduct);
            hibernateSession.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return false;
    }
}