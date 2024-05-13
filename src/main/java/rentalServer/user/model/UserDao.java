package rentalServer.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import rentalServer.util.DBManager;
import rentalServer.util.PasswordCrypto;

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
	
	public List<UserResponseDto> findUserAll(){
		List<UserResponseDto> list = new ArrayList<UserResponseDto>();
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT id, name, birth, phone, licenseDate FROM users";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String birth = rs.getString(3);
				String phone = rs.getString(4);
				String licenseDate = rs.getString(5);
				
				
				UserResponseDto user = new UserResponseDto(id, name, birth, phone, licenseDate);
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public UserResponseDto findUserByIdAndPassword(String id, String password) {
		UserResponseDto user = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT id, name, birth, phone, licenseDate, password FROM users WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString(2);
				String birth = rs.getString(3);
				String phone = rs.getString(4);
				String licenseDate = rs.getString(5);
				String encryptedPassword = rs.getString(6);
				
				if(PasswordCrypto.decrypt(password, encryptedPassword)) {
					user = new UserResponseDto(id, name, birth, phone, licenseDate);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return user;
	}
	
	public boolean userExists(UserRequestDto userDto) {
		return findUserByIdAndPassword(userDto.getId(), userDto.getPassword()) != null;
	}
	
	public boolean userExists(String id) {
		return findUserById(id) != null;
	}
	
	public UserResponseDto createUser(UserRequestDto userDto) {
		try {
			conn = DBManager.getConnection();
			
			String sql = "INSERT INTO users(id, password, name, birth, phone, licenseDate) VALUES(?, ?, ?, ?, ?, ?)";
			
			System.out.println("conn : " + conn);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, PasswordCrypto.encrypt(userDto.getPassword()));
			pstmt.setString(3, userDto.getName());
			pstmt.setString(4, userDto.getBirth());
			pstmt.setString(5, userDto.getPhone());
			pstmt.setString(6, userDto.getLicenseDate());
			
			pstmt.execute();
			
			return findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}
	
	public User findUserById(String id) {
		User user = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT id, name, birth, phone, licenseDate, reg_date, mod_date FROM users WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString(2);
				String birth = rs.getString(3);
				String phone = rs.getString(4);
				String licenseDate = rs.getString(5);
				Timestamp regDate = rs.getTimestamp(6);
				Timestamp modDate = rs.getTimestamp(7);
				
				user = new User(id, name, birth, phone, licenseDate, regDate, modDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}
}
