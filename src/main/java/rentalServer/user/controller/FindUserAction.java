package rentalServer.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import rentalServer.user.model.UserDao;
import rentalServer.user.model.UserResponseDto;

public class FindUserAction {
	public FindUserAction() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = UserDao.getInstance();
		
		List<UserResponseDto> list = userDao.findUserAll();
		
		JSONArray array = new JSONArray(list);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.append(array.toString());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
	
		UserDao userDao = UserDao.getInstance();
		boolean isValid = !userDao.userExists(id);
		
		JSONObject object = new JSONObject();
		
		object.put("id", id);
		object.put("isValid", isValid);
		
		// response type 설정 (MIME-Type)
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.append(object.toString());
	}
}
