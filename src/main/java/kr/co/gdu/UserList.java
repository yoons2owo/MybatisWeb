package kr.co.gdu;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBCon con = new DBCon();
		SqlSessionFactory sqlSessionFactory = con.getCon();
		SqlSession session = sqlSessionFactory.openSession(true);
		
		List<HashMap<String, Object>> list = session.selectList("user.selectUser");
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		request.setAttribute("list", list);
		RequestDispatcher dis = request.getRequestDispatcher("userList.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}