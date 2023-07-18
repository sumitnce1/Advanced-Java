package org.sumithibernate.CategoryCardConsole;

import java.text.DecimalFormat;
import java.util.Scanner;
import org.sumitpojo.hibernate.Card;
import org.sumitpojo.hibernate.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		Configuration hibernateConfiguration = new Configuration();
		hibernateConfiguration.configure("first-hibernate.cfg.xml");

		try (SessionFactory hibernateFactory = hibernateConfiguration.buildSessionFactory();
				Session hibernateSession = hibernateFactory.openSession();
				Scanner scanner = new Scanner(System.in)) {

			int choice;
			do {
				System.out.println("Menu:");
				System.out.println("________________________");
				System.out.println("1. AddNewCategory");
				System.out.println("2. UpdateCategory");
				System.out.println("3. FindCategoryByID");
				System.out.println("4. AddCard");
				System.out.println("5. UpdateCard");
				System.out.println("6. FindCard");
				System.out.println("7. Exit");
				System.out.println("________________________");
				System.out.print("Please Enter Your Choice: ");

				choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					addNewCategory(hibernateSession, scanner);
					break;
				case 2:
					updateCategory(hibernateSession, scanner);
					break;
				case 3:
					findCategoryByID(hibernateSession, scanner);                        
					break;
				case 4:
					addNewCard(hibernateSession, scanner);
					break;
				case 5:
					updateCard(hibernateSession, scanner);
					break;
				case 6:
					findCardById(hibernateSession, scanner);
					break;
				case 7:
					System.out.println("Exiting the application...");
					break;
				default:
					System.out.println("Invalid choice. Try again...");
				}

				System.out.println();
			}while (choice != 7);
			scanner.close();
			hibernateSession.close();
		}
	}

	private static void addNewCategory(Session hibernateSession, Scanner scanner) {
		System.out.print("Enter New CategoryId: ");
		String categoryId = scanner.nextLine();		

		System.out.print("Enter Category Name: ");
		String categoryName = scanner.nextLine();

		System.out.print("Enter Category Description: ");
		String categoryDescription = scanner.nextLine();

		System.out.print("Enter Category URL: ");
		String categoryImageUrl = scanner.nextLine();

		Category objCategory = new Category(categoryId, categoryName, categoryDescription, categoryImageUrl);
		hibernateSession.beginTransaction();
		hibernateSession.saveOrUpdate(objCategory);
		hibernateSession.getTransaction().commit();

		System.out.println("This New" + categoryId +" Category Added in Database. :) \n ___________________________");
	}
	private static void updateCategory(Session hibernateSession, Scanner scanner) {
		System.out.print("Enter CategoryId to update: ");
		String categoryId = scanner.nextLine();

		Category category = hibernateSession.get(Category.class, categoryId);
		if (category != null) {
			System.out.print("Enter New Category Name: ");
			String categoryName = scanner.nextLine();

			System.out.print("Enter new Category Description: ");
			String categoryDescription = scanner.nextLine();

			System.out.print("Enter new Category URL: ");
			String categoryImageUrl = scanner.nextLine();

			category.setCategoryName(categoryName);
			category.setCategoryDescription(categoryDescription);
			category.setCategoryImageUrl(categoryImageUrl);

			hibernateSession.beginTransaction();
			hibernateSession.update(category);
			hibernateSession.getTransaction().commit();

			System.out.println("Your New Category Details Updated. :) \n _____________________________________");
		} else {
			System.out.println("Opps! -This " + categoryId + " CategoryId is not found! (: Try Again..... \n _____________________________");
		}

	}

	private static void findCategoryByID(Session hibernateSession, Scanner scanner) {
		System.out.print("Enter Category Id to find: ");
		String categoryId = scanner.nextLine();

		Category category = hibernateSession.get(Category.class, categoryId);
		if (category != null) {
			System.out.println("CategoryId: " + category.getCategoryId());
			System.out.println("CategoryName: " + category.getCategoryName());
			System.out.println("CategoryDescription: " + category.getCategoryDescription());
			System.out.println("CategoryImageURL: " + category.getCategoryImageUrl());
		} else {
			System.out.println("Opps! -This " + categoryId + "  CategoryId is not found! (: Try Again..... \n _____________________________");
		}

	}

	private static void addNewCard(Session hibernateSession, Scanner scanner) {
		System.out.print("Enter NewCard Number: ");
		long cardNo = scanner.nextLong();
		scanner.nextLine();

		System.out.print("Enter Card Expiry Date: ");
		String expiry = scanner.nextLine();

		System.out.print("Enter Card Balance: ");
		double balance = scanner.nextDouble();
		scanner.nextLine();

		System.out.print("Enter Card's Status(true/false): ");
		String status = scanner.nextLine();

		Card objCard = new Card(cardNo, expiry, balance, status);
		hibernateSession.beginTransaction();
		hibernateSession.saveOrUpdate(objCard);
		hibernateSession.getTransaction().commit();

		System.out.println("New " +cardNo + " Card Added in Database. :) \n _____________________________");
	}

	private static void updateCard(Session hibernateSession, Scanner scanner) {
		System.out.print("Enter Card Number to update: ");
		long cardNo = scanner.nextLong();
		scanner.nextLine();

		Card card = hibernateSession.get(Card.class, cardNo);
		if (card != null) {        	

			System.out.print("Enter New Card Expiry Date: ");
			String expiry = scanner.nextLine();

			System.out.print("Enter New Card Balance: ");
			double balance = scanner.nextDouble();
			scanner.nextLine();

			System.out.print("Enter New Card Status(true/false): ");
			String status = scanner.nextLine();

			card.setExpiry(expiry);
			card.setBalance(balance);
			card.setStatus(status);

			hibernateSession.beginTransaction();
			hibernateSession.update(card);
			hibernateSession.getTransaction().commit();

			System.out.println("Your New Card Details Updated. :) \n _____________________________________");
		} else {
			System.out.println("Opps! This CardNo." + cardNo + "-is not found! :( Try Again..... \n _______________________________");
		}

	}

	private static void findCardById(Session hibernateSession, Scanner scanner) {
	    System.out.print("Enter Card no. to find: ");
	    long cardNo = scanner.nextLong();
	    scanner.nextLine();

	    Card card = hibernateSession.get(Card.class, cardNo);
	    if (card != null) {
	        System.out.println("CardNo. : " + card.getCardNo());
	        System.out.println("CardExpiry : " + card.getExpiry());
	        
	        double balance = card.getBalance();
	        DecimalFormat decimalFormat = new DecimalFormat("#.#########");
	        String formattedBalance = decimalFormat.format(balance);
	        System.out.println("CardBalance: " + formattedBalance);

	        System.out.println("CardStatus : " + card.getStatus());
	    } else {
	        System.out.println("Oops! This CardNo." + cardNo + " is not found! :( Try Again..... \n _______________________________");
	    }
	}
}
