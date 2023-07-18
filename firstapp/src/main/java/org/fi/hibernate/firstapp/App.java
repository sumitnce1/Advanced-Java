package org.fi.hibernate.firstapp;

import org.sumit.hibernate.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App 
{
	public static void main(String[] args) {
		// 1. Load the hibernate configuration(xml
		Configuration hibernateConfiguration= new Configuration();		
		hibernateConfiguration.configure("first-hibernate.cfg.xml");
		// 2. Build a sessionFactory		
		try(SessionFactory hibernateFactory= hibernateConfiguration.buildSessionFactory();		
		//3. Open Hibernate Session		
		Session hibernateSession= hibernateFactory.openSession();)
		{
		//4. Create my pojo object that has to be persisted
			Users objUsers = new Users("ashish2","12345","Ashish Kumar","ashish@cdac.com","6545213581","Kolkata");
			// 5. Persist the pojo object
			hibernateSession.beginTransaction();
			hibernateSession.save(objUsers);
			hibernateSession.getTransaction().commit();
			
			System.out.println("Record Saved.");
			// 6. Release any resources.
		}
		
	}
}