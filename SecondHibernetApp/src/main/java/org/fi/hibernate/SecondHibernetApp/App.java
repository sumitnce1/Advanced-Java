package org.fi.hibernate.SecondHibernetApp;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sumit.hibernate.entity.Users;

public class App 
{
	public static void main(String[] args) 
	{

		Configuration hibernateConfiguration= new Configuration();		
		hibernateConfiguration.configure("first-hibernate.cfg.xml");
		try (SessionFactory hibernateFactory = hibernateConfiguration.buildSessionFactory();
				Session hibernateSession = hibernateFactory.openSession();
				Scanner scanner = new Scanner(System.in)) 
		{
			/*
			 * System.out.println("Enter the userName"); 
			 * String userName = scanner.next();
			 * Users objUser = (Users) hibernateSession.load(Users.class, userName);
			 * 
			 * System.out.println(objUser.getUserName());
			 * System.out.println(objUser.getName());
			 * System.out.println(objUser.getEmail());
			 * System.out.println(objUser.getPhone());
			 * System.out.println(objUser.getCity());
			 */


			/*
			 * Users objUsers = new
			 * Users("ashish2","123456","Ashish Kumar","ashish@cdac.com","6545213581",
			 * "Kolkata"); hibernateSession.beginTransaction();
			 * hibernateSession.saveOrUpdate(objUsers);
			 * hibernateSession.getTransaction().commit();
			 * 
			 * System.out.println("Record saved");
			 */

			// Hibernate Query Language(HQL)

			//Query queryAllUsers = hibernateSession.createQuery("from Users");

			/*
			 * Query queryAllUsers =
			 * hibernateSession.createQuery("select OBJECT(objUser) from Users objUser");
			 * Iterator<Users> iter = queryAllUsers.getResultList().iterator();
			 * while(iter.hasNext()) { Users objUser = iter.next();
			 * System.out.println(objUser); }
			 * 
			 * System.out.println("2. ________");
			 * 
			 * Query queryNameCity = hibernateSession.
			 * createQuery("select objUser.name, objUser.city from Users objUser");
			 * List<Object []> list = queryNameCity.getResultList();
			 * 
			 * for(Object [] data: list) { System.out.println("Username : " + data[0]);
			 * System.out.println("City " + data[1]);
			 * System.out.println("-----------------------"); }
			 * 
			 * //----------------------------------------------------
			 * 
			 * System.out.println("3. ________");
			 * 
			 * Query queryNameCity2 = hibernateSession.
			 * createQuery("select objUser.name, objUser.city from Users objUser where objUser.userName='sumit'"
			 * ); Object[] data= (Object[])queryNameCity2.getSingleResult();
			 * System.out.println("Username : " + data[0]); System.out.println("City : " +
			 * data[1]);
			 * 
			 * //------------------------------------------------------------------
			 * 
			 * System.out.println("4. ________");
			 * 
			 * System.out.println("Enter the Username to search"); String userNames =
			 * scanner.next();
			 * 
			 * Query queryNameCity3 = hibernateSession.
			 * createQuery("select OBJECT(objUser) from Users objUser where objUser.userName=?1"
			 * ); queryNameCity3.setParameter(1, userNames); Object[] data2=
			 * (Object[])queryNameCity2.getSingleResult(); System.out.println("Name : " +
			 * data[0]); System.out.println("City : " + data[1]);
			 * System.out.println("-----------------------");
			 * 
			 * //----------------------------------------------------------
			 * 
			 * // Native Queries... System.out.println("5. ________");
			 * 
			 * Query query = hibernateSession.createNativeQuery("select * from Users_0016");
			 * List<Object[]> lists = query.getResultList(); for(Object[] datas : lists) {
			 * System.out.println("Username : " + datas[0]);
			 * System.out.println("Password : " + datas[1]); System.out.println("Name : " +
			 * datas[2]); System.out.println("Email : " + datas[3]);
			 * System.out.println("Phone : " + datas[4]); System.out.println("City : " +
			 * datas[5]); System.out.println("*********************"); }
			 */

			//--------------------------------------------------------------------


			//Native Query-1------
			/*
			 * Query queryAllUsers = hibernateSession.getNamedQuery("allUsers"); List<Users>
			 * list = queryAllUsers.getResultList();
			 * 
			 * for (Users objUser : list) System.out.println(objUser);
			 */	

			//Native Query-2/3
			Query city = hibernateSession.getNamedNativeQuery("usersByCity");
			city.setParameter("cityname", "Patna");
			List<Object[]> list = city.getResultList();
			for (Object[] data: list)
			{
				System.out.println(data[0]);
				System.out.println(data[1]);
				System.out.println(data[2]);
				System.out.println(data[3]);
				System.out.println(data[4]);
				System.out.println("---------------------------------");

			}


		}
	}
}