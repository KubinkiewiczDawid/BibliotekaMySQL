package mySqlLibary;

import java.io.IOException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		
		//menu
		int status = 1;	
		
		while(status != 0) {
			System.out.println("1. Uzytkownicy");
			System.out.println("2. Ksiazki");
			System.out.println("3. Wypozyczenia");
			Scanner scan = new Scanner(System.in);
			int number = scan.nextInt();
			
			switch(number) {
			case 1:
				System.out.println("1. Dodaj uzytkownika");
				System.out.println("2. Edytuj uzytkownika");
				System.out.println("3. Usun uzytkownika");
				System.out.println("4. Pokaz wszystkich uzytkownikow");
				System.out.println("5. Cofnij");
				int number1 = scan.nextInt();
				if(number1 == 1) {
					System.out.println("Wpisz dane uzytkownika: ");
					addUser();
					break;
				}else if(number1 == 2) {
					editUser();
					break;
				}else if(number1 == 3) {
					removeUser();
					break;
				}else if(number1 == 4) {
					UserDao udao = new UserDao();
					udao.showAllUsers();
					break;
				}else if(number1 == 5) {
					break;
				}
				
			case 2:
				System.out.println("1. Dodaj ksiazke");
				System.out.println("2. Edytuj ksiazke");
				System.out.println("3. Usun ksiazke");
				System.out.println("4. Pokaz wszystkie ksiazki");
				System.out.println("5. Znajdz ksiazke");
				System.out.println("6. Cofnij");
				int number2 = scan.nextInt();
				if(number2 == 1) {
					addBook();
					break;
				}else if(number2 == 2) {
					//editBook();
				}else if(number2 == 3) {
					removeBook();
					break;
				}else if(number2 == 4) {
					BookDao bdao = new BookDao();
					bdao.showAllBooks();
					break;
				}else if(number2 == 5) {
					findBook();
					break;
				}else if(number2 == 6) {
					break;
				}
			case 3:
				System.out.println("1. Wypozycz ksiazke");
				System.out.println("2. Pokaz wypozyczone ksiazki");
				System.out.println("3. Cofnij");
				int number3 = scan.nextInt();
				if(number3 == 1) {
					rentBook();
				}else if(number3 == 2) {
					BookDao bdao = new BookDao();
					bdao.showAllRents();
				}else if(number3 == 3) {
					break;
				}
				break;
			}
		}
	}
	
	//User
	public static void addUser() {
		Scanner scan = new Scanner(System.in);
		System.out.println("imie");
		String name = scan.nextLine();
		System.out.println("nazwisko");
		String lastname = scan.nextLine();
		System.out.println("numer telefonu");
		int telephone = scan.nextInt();
		User u = new User(name, lastname,telephone);
		UserDao udao = new UserDao();
		udao.addUser(u);
		System.out.println("Pomyslnie dodano uzytkownika " + name + " " + lastname);
		System.out.flush(); 
	}
	
	public static void removeUser() {
		Scanner scan = new Scanner(System.in);
		UserDao udao = new UserDao();
		System.out.println("Podaj numer uzytkownika ktorego chcesz usunac");
		udao.showAllUsers();
		int id = scan.nextInt();
		udao.removeUser(id);
		System.out.println("Pomyslnie usunieto uzytkownika o numerze " + id);
		System.out.flush(); 
	}
	
	public static void editUser() { // nie dokonczone
		Scanner scan = new Scanner(System.in);
		UserDao udao = new UserDao();
		udao.showAllUsers();
		User user = new User();
		System.out.println("Podaj numer uzytkownika ktorego chcesz edytowac");
		int id = scan.nextInt();
		user.setId(id);
		System.out.println("Podaj nowe imie");
		scan.nextLine();
		String name = scan.nextLine();
		user.setName(name);
		System.out.println("Podaj nowe nazwisko");
		String lastname = scan.nextLine();
		user.setLastname(lastname);
		System.out.println("Podaj nowy numer telefonu");
		int telephoneNumber = scan.nextInt();
		user.setTelephone(telephoneNumber);
		udao.editUser(user);

	}
	
	//book
	public static void addBook() {
		System.out.println("Wpisz dane uzytkownika: ");
		Scanner scan = new Scanner(System.in);
		System.out.println("Tytul");
		String title = scan.nextLine();
		System.out.println("autor");
		String author = scan.nextLine();
		System.out.println("rok wydania");
		int publishedYear = scan.nextInt();
		Book book = new Book(title, author, publishedYear);
		BookDao bdao = new BookDao();
		bdao.addBook(book);
		System.out.println("Pomyslnie dodano ksiazke " + title);
		System.out.flush(); 
	}
	
	public static void removeBook() {
		Scanner scan = new Scanner(System.in);
		BookDao bdao = new BookDao();
		System.out.println("Wybierz numer ksiazki ktora chcesz usunac");
		bdao.showAllBooks();
		int id = scan.nextInt();
		bdao.removeBook(id);
		System.out.println("Pomyslnie usunieto ksiazke o numerze " + id);
		System.out.flush(); 
	}
	
	public static void findBook() {
		BookDao bdao = new BookDao();
		Scanner scan = new Scanner(System.in);
		System.out.println("Znajdz ksiazke po");
		System.out.println("1. Tytule");
		System.out.println("2. Autorze");
		
		int number = scan.nextInt();
		if(number == 1) {
			Scanner scan1 = new Scanner(System.in);
			System.out.println("Podaj tytul ksiazki");
			scan.nextLine();
			String title = scan.nextLine();
			bdao.findBookByTitle(title);
		}else if(number == 2) {
			Scanner scan2 = new Scanner(System.in);
			System.out.println("Podaj autora ksiazki");
			scan.nextLine();
			String author = scan.nextLine();
			bdao.findBookByAuthor(author);
		}
	}
	
	public static void rentBook() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Kto wypozycza ksiazke?");
		UserDao udao = new UserDao();
		udao.showAllUsers();
		int idUser = scan.nextInt();
		BookDao bdao = new BookDao();
		System.out.println("Jaka ksiazke wypozycza?");
		bdao.showAllBooks();
		int idBook = scan.nextInt();
		bdao.rentBook(idBook, idUser);
	}
	
}
