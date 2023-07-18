package org.fi.hibernate.firstapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sumit.hibernate.Card;

public class CardApp {
    public static void main(String[] args) {
        // 1. Load the hibernate configuration(xml)
        Configuration hibernateConfiguration = new Configuration();
        hibernateConfiguration.configure("first-hibernate.cfg.xml");

        // 2. Build a SessionFactory
        try (SessionFactory hibernateFactory = hibernateConfiguration.buildSessionFactory();
             // 3. Open Hibernate Session
             Session hibernateSession = hibernateFactory.openSession()) {

            // 4. Create my pojo object that has to be persisted
            Card objCard = new Card("84222", "12/2025", "65000", "true");

            // 5. Persist the pojo object
            hibernateSession.beginTransaction();
            hibernateSession.save(objCard);
            hibernateSession.getTransaction().commit();

            System.out.println("Card Record Saved.");

            // 6. Release any resources.
        }
    }
}
