package mySqlLibary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class BookDao {

	private MySQLConnector connect;

	public BookDao() {
		connect = MySQLConnector.getINSTANCE();
	}
	
	public Book getBook(int id) {
		Book book = new Book();
		try {
			String sql = "SELECT * FROM book WHERE id = ?";
			PreparedStatement st = connect.getPreparedStatement(sql);
			st.setInt(1, id);

			ResultSet resultSet = st.executeQuery();

			while (resultSet.next()) {
				String author = resultSet.getString("author");
				book.setAuthor(author);
				String title = resultSet.getString("title");
				book.setTitle(title);
				int publishedYear = resultSet.getInt("publishedYear");
				book.setPublishedYear(publishedYear);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public void addBook(Book book) {
		String sql = "INSERT INTO book VALUES (?,?,?,?)";
		PreparedStatement state = connect.getPreparedStatement(sql);
		try {
			state.setNull(1, 0);
			state.setString(2, book.getTitle());
			state.setString(3, book.getAuthor());
			state.setInt(4, book.getPublishedYear());
			state.execute();
			state.closeOnCompletion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeBook(int id) {
		String sql = "DELETE FROM book WHERE id = ?";
		PreparedStatement state = connect.getPreparedStatement(sql);
		try {
			state.setInt(1, id);
			state.execute();
			state.closeOnCompletion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showAllBooks() {
		try {
			ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM book");
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String columnValue = resultSet.getString(i);
					System.out.print(columnValue);
					if (i == 1)
						System.out.print(". ");
					if (i > 1 && i < 4)
						System.out.print(", ");
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void findBookByAuthor(String bAuthor) {
		Book book = new Book();
		int counter = 0;
		try {
			String sql = "SELECT * FROM book WHERE author = ?";
			PreparedStatement st = connect.getPreparedStatement(sql);
			st.setString(1, bAuthor);
			st.execute();
			st.closeOnCompletion();
			ResultSet resultSet = st.executeQuery();
			if (!resultSet.isBeforeFirst() ) {    
			    System.out.println("Nie ma takiego autora"); 
			} 
			while (resultSet.next()) {
				int bookId = resultSet.getInt("id");
				book = getBook(bookId);
				if (counter == 0) {
					System.out.println("Ksiazki wydane przez wyszukiwanego autora: ");
				}
				System.out.println(book.toString());
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Book findBookByTitle(String bTitle) {
		Book book = new Book();
		int counter = 0;
		try {
			String sql = "SELECT * FROM book WHERE title = ?";
			PreparedStatement st = connect.getPreparedStatement(sql);
			st.setString(1, bTitle);
			st.execute();
			st.closeOnCompletion();
			ResultSet resultSet = st.executeQuery();
			if (!resultSet.isBeforeFirst() ) {    
			    System.out.println("Nie ma ksiazki o takim tytule"); 
			} 
			while (resultSet.next()) {
				int bookId = resultSet.getInt("id");
				if (counter == 0) {
					System.out.println("Ksiazki o takim tytule:: ");
				}
				book = getBook(bookId);
				System.out.println(book.toString());
				counter++;
			}
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public void rentBook(int idBook, int idUser) {
		UserDao udao = new UserDao();
		String sql = "INSERT INTO rent(user, book) VALUES(?, ?)";
		PreparedStatement state = connect.getPreparedStatement(sql);
		try {
			state.setInt(1, idUser);
			state.setInt(2, idBook);
			state.execute();
			state.closeOnCompletion();
			System.out.println(
					"Wypozyczono ksiazke: " + getBook(idBook).toString() + " uzytkownikowi " + udao.getUser(idUser));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void giveBackBook(int idUser, int idBook) {
		UserDao udao = new UserDao();
		String sql = "DELETE FROM rent WHERE user = ? AND book = ?";
		PreparedStatement state = connect.getPreparedStatement(sql);
		try {
			state.setInt(1, idUser);
			state.setInt(2, idBook);
			state.execute();
			state.closeOnCompletion();
			System.out.println("Ksiazka " + getBook(idBook).getTitle() +  " wypozyczona przez uzytkownika " + udao.getUser(idUser).getName() + " " + udao.getUser(idUser).getLastname() + " zostala zwrocona");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showRents(int idUser) {
		Book book = new Book();
		User user = new User();
		UserDao udao = new UserDao();
		int counter = 1;
		String sql = "SELECT * FROM rent WHERE user = ?";
		PreparedStatement state = connect.getPreparedStatement(sql);
		try {
			state.setInt(1, idUser);
			state.execute();
			state.closeOnCompletion();
			ResultSet resultSet = state.executeQuery();
			while(resultSet.next()) {
				int bookId = resultSet.getInt("book");
				int userId = resultSet.getInt("user");
				book = getBook(bookId);
				user = udao.getUser(userId);
				if(counter == 1) {
					System.out.println("Ksiazki wypozyczone przez uzytkownika " + user.getName() + ':');
				}
				System.out.println(counter + ". " + book.toString() + " numer ksiazki: " + bookId);
				counter++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAllRents() {
		User user = new User();
		UserDao udao = new UserDao();
		Book book = new Book();
		int counter = 0;
		try {
			ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM rent");
			ResultSetMetaData rsmd = resultSet.getMetaData();
			while (resultSet.next()) {
				int userId = resultSet.getInt("user");
				int bookId = resultSet.getInt("book");

				book = getBook(bookId);
				user = udao.getUser(userId);
				if (counter == 0) {
					System.out.println("Wypozyczone ksiazki: ");
				}				
				System.out.println(book.toString() + " -- " + user.toString());
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
