package kr.co.gdu;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBTest extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		
		String resource = "kr/co/gdu/mybatis-config.xml";
		// File Load (물리적 파일를 Byte 배열에 담아 주는 작업)
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// Mybatis에서 JDBC 연결해 주는 작업
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		System.out.println(sqlSessionFactory);
		// JDBC 연결 되어 있는 것 중에 하나의 연결 통로를 생성한다.
		SqlSession session = sqlSessionFactory.openSession(true);
		/*  만들어진 연결 통로를 이용하여 SQL를 실행한다.
	 	session.selectOne("");   -> 하나 행 가져오기
		session.selectList("");  -> 여러개 행 가져오기 
		session.insert("");      -> 데이터 입력
		session.update("");      -> 데이터 수정
		session.delete("");      -> 데이터 삭제
		*/
		HashMap<String, Object> result = session.selectOne("test.selectTest");
		System.out.println(result);
		System.out.println("End");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}