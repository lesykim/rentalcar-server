package rentalServer.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rentalServer.user.model.UserDao;
import rentalServer.user.model.UserRequestDto;
import rentalServer.user.model.UserResponseDto;

public class JoinFormAction {
	private static final long serialVersionUID = 1L;
	
	public JoinFormAction() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String licenseDate = request.getParameter("licenseDate");
		
		boolean isValid = true;
		
		if(id == null || id.equals("")) {
			isValid = false;
		}else if(password == null || password.equals("")) {
			isValid = false;
		}else if(name == null || name.equals("")) {
			isValid = false;
		}else if(birth == null || birth.equals("")) {
			isValid = false;
		}else if(phone == null || phone.equals("")) {
			isValid = false;
		}else if(licenseDate == null || licenseDate.equals("")) {
			isValid = false;
		}
		
		if(isValid) {
			UserRequestDto userDto = new UserRequestDto(id,password,name,birth,phone,licenseDate);
			
			UserDao userDao = UserDao.getInstance();
			UserResponseDto user = userDao.createUser(userDto);
			
			if(user == null) {
				response.sendRedirect("/join");
			} else {
				response.sendRedirect("/login");
			}
		} else {
			response.sendRedirect("/join");
		}
	}
}
