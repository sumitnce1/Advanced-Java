package org.fi.hibernate.firstapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sumit.hibernate.Category;

public class CategoryApp {
    public static void main(String[] args) {
        // 1. Load the hibernate configuration(xml)
        Configuration hibernateConfiguration = new Configuration();
        hibernateConfiguration.configure("first-hibernate.cfg.xml");

        // 2. Build a SessionFactory
        try (SessionFactory hibernateFactory = hibernateConfiguration.buildSessionFactory();
             // 3. Open Hibernate Session
             Session hibernateSession = hibernateFactory.openSession()) {

            // 4. Create my pojo object that has to be persisted
            Category objCategory = new Category("8", "Laptop", "Must Write Code", "laptop.jpg");

            // 5. Persist the pojo object
            hibernateSession.beginTransaction();
            hibernateSession.save(objCategory);
            hibernateSession.getTransaction().commit();

            System.out.println("Category Record Saved.");

            // 6. Release any resources.
        }
    }
}
