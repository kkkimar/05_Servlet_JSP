package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<String> nameList = new ArrayList<String>();
		
		nameList.add("김씨");
		nameList.add("최씨");
		nameList.add("박씨");
		nameList.add("손씨");
		nameList.add("우씨");
		nameList.add("정씨");
		
		
		//List에 입력 받은 이름(파라미터)가 존재하는지 확인
		// K:V 형태로 넘어옴
		String inputName = req.getParameter("inputName");
		
		
		
		if(nameList.contains(inputName)) {
			
		String searchMessage 
		= String.format("%s은/는 %d번째 인덱스에 존재합니다", inputName, nameList.indexOf(inputName));	
		
		//forward시 request 유지!
		
		req.setAttribute("searchMessage", searchMessage);
		
		String path = "WEB-INF/views/practice/search_result.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
			
		}else {
			
			String searchMessage = inputName + "은/는 존재하지 않습니다";
			
			HttpSession session = req.getSession(); // session 객체 얻어오기
			
			session.setAttribute("searchMessage", searchMessage);
			
			// "/error" redirect
			resp.sendRedirect("/error"); //redirect는 get방식!
			
		}
		
		
		
		
	}
	
	
	
	
}
