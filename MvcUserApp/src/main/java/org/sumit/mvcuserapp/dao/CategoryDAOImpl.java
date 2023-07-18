package org.sumit.mvcuserapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sumit.mvcuserapp.beans.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
    SessionFactory hibernateFactory;
	
	@Override
	public boolean newCategory(Category objCategory) {
		try (Session hibernateSession = hibernateFactory.openSession()) {
            hibernateSession.beginTransaction();
            hibernateSession.save(objCategory);
            hibernateSession.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return false;
    }
}
