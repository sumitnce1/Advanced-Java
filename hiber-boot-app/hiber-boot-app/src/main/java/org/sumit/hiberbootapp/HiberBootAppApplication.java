package org.sumit.hiberbootapp;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.sumit.hiberbootapp.entity.Category;

@SpringBootApplication
@EntityScan(basePackages = {"org.sumit.hiberbootapp.entity"})
public class HiberBootAppApplication implements CommandLineRunner 
{
	
	@Autowired
	SessionFactory hibernateFactory;
	public static void main(String[] args) {
		SpringApplication.run(HiberBootAppApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try (Scanner scanner = new Scanner(System.in);
				Session hibernateSession = hibernateFactory.openSession())
		{
			System.out.println("Enter the CategoryName: ");
			String CategoryName = scanner.nextLine();
			
			System.out.println("Enter the categoryDescription: ");
			String categoryDescription = scanner.nextLine();
			
			System.out.println("Enter the categoryImageUrl: ");
			String categoryImageUrl = scanner.nextLine();
			
			Category objCategory = new Category(CategoryName, categoryDescription,categoryImageUrl );
			
			hibernateSession.beginTransaction();
			hibernateSession.save(objCategory);
			
			hibernateSession.getTransaction().commit();
			System.out.println("Category Added in Database.");
			
		}
	}
}
