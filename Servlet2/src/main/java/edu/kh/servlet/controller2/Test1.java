package edu.kh.servlet.controller2;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
 * Servlet 작성 순서
 * 1) HttpServlet 상속 받기
 * 
 * 2) @WebServlet() 어노테이션 작성하기
 * 
 * 3) doGet() 또는 doPost() 오버라이딩 -> 요청 method에 따라서 수행
 * 
 * 4) 필요한 로직 처리
 * 		- 파라미터 얻어오기
 * 		- 필요한 요청 처리 구문 작성
 * 
 * 5) 응답 형태 지정 + 응답 스트림 얻어오기
 * 
 * 6) 스트림을 통해 응답 데이터(html) 출력하기
 * 
 * */

@WebServlet("/ex3")
public class Test1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pizza = req.getParameter("pizza");
		String type = req.getParameter("type");
		String ordererName = req.getParameter("ordererName");
		String ordererAddress = req.getParameter("ordererAddress");
		
		String[] options = req.getParameterValues("opt");
		
		System.out.println("[서버] / ex3 요청됨");
		
		//-------------------------------------------------------------------------------
		
		int price = 0;
		
		switch(pizza) {
		
		case "페페로니" : price += 20000; break;
		case "불고기" : price += 22000; break;
		case "베이컨" : 
		case "시카고" : price += 23000; break;
		
	
		}
		
		if(type.equals("cheez")) price += 2000;
		
		if(options != null) {
			
			for(String opt : options) {
				
				switch(opt) {
				case "파마산치즈": price+=1000; break; 
				case "콜라": price+=2000; break; 
				case "피클": price+=500; break; 
				}
			}
		}
		
		
		//------------------------------------------------------------
		
		StringBuilder sb = new StringBuilder();
		
		//응답형태
		
		resp.setContentType("text/html; charset:utf-8");
		
		PrintWriter out = resp.getWriter();
		
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		
		sb.append("<head>");
		sb.append("<title>");
		sb.append(String.format("%s님 주문 영수증",ordererName ));
		sb.append("</title>");
		sb.append("</head>");
		
		sb.append("<body>");
		
		sb.append("<h3>");
		sb.append("주문자명 : ");
		sb.append(ordererName);
		sb.append("</h3>");
		
		sb.append(String.format("<h3>주소 : %s</h3>", ordererAddress));
		
		sb.append("<ul>");
		
		sb.append(String.format("<li>치킨 : %s</li>", pizza));
		
		String temp = type.equals("basic")? "기본" : "치즈" ;
		
		sb.append(String.format("<li>기본/치즈 : %s</li>", temp));
		
		if(options != null) {
			
			sb.append("<li>");
			
			sb.append("선택한 옵션 : ");
			for(String opt : options) {
				sb.append(opt + " ");
			}
			
			sb.append("</li>");
		}
		
		sb.append("</ul>");
		
		sb.append(String.format("<h3>금액 : %d 원</h3>", price));
		
		sb.append("</body>");
		
		
		sb.append("</html>");
		
		out.print(sb.toString());
		
	}
	
	
	
}
