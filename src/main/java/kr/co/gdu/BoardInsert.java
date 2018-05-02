package kr.co.gdu;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class BoardInsert extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String regUser = request.getParameter("regUser");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("content", content);
		map.put("regUser", regUser);
		System.out.println(map);
		
		DBCon con = new DBCon();
		SqlSessionFactory sqlSessionFactory = con.getCon();
		SqlSession session = sqlSessionFactory.openSession(true);
		int cnt = session.insert("user.insertBoard", map);
		System.out.println(cnt);
		if(cnt == 1) {
			List<HashMap<String, Object>> list 
				= session.selectList("user.selectBoard", map);
			request.setAttribute("list", list);
			RequestDispatcher dis = request.getRequestDispatcher("boardList.jsp");
			dis.forward(request, response);
		} else {
			
		}
	}

}