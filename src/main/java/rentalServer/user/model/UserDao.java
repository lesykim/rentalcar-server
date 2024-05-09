package rentalServer.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private UserDao() {
		
	}
	
	private static UserDao instance = new UserDao();
	
	public static UserDao getInstance() {
		return instance;
	}
	
}
