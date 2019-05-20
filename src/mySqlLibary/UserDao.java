package mySqlLibary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import mySqlLibary.MySQLConnector;

public class UserDao {
	
	private MySQLConnector connect;

	public UserDao() {
		connect = MySQLConnector.getINSTANCE();
	}
	
	public void addUser(User user) {
		String sql= "INSERT INTO user VALUES (?,?,?,?)";
		PreparedStatement state = connect.getPreparedStatement(sql);
		try {
			state.setNull(1, 0);
			state.setString(2, user.getName());
			state.setString(3, user.getLastname());
			state.setInt(4, user.getTelephone());
			state.execute();
			state.closeOnCompletion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeUser(int id) {
		String sql = "DELETE FROM user WHERE id = ?";
		PreparedStatement state = connect.getPreparedStatement(sql);
		try {
			state.setInt(1, id);
			state.execute();
			state.closeOnCompletion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editUser(User user) {
		String sql = "UPDATE `user` SET `name` = ?, `lastname` = ?, `tephoneNumber` = ? WHERE `id` = ?";
		PreparedStatement state = connect.getPreparedStatement(sql);
		try {
			state.setString(1, user.getName());
			state.setString(2, user.getLastname());
			state.setInt(3, user.getTelephone());
			state.setInt(4, user.getId());
			state.execute();
			state.closeOnCompletion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showAllUsers() {
		try {
			ResultSet  resultSet = connect.getStatement().executeQuery("SELECT * FROM user");
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (resultSet.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        String columnValue = resultSet.getString(i);
			        System.out.print(columnValue);
			        if(i == 1)System.out.print(". ");
			        if(i > 1 && i < 4) System.out.print(", ");
			    }
			    System.out.println("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User getUser(int id) {
		User user = new User();
		try {
			String sql = "SELECT * FROM user WHERE id = ?";
			PreparedStatement st = connect.getPreparedStatement(sql);
			st.setInt(1, id);
			
			ResultSet resultSet = st.executeQuery();
			
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				user.setName(name);
				String lastname = resultSet.getString("lastname");
				user.setLastname(lastname);
				int telephoneNumber = resultSet.getInt("telephoneNumber");
				user.setTelephone(telephoneNumber);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
