package edu.kh.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("주소")
//1) 현재 클래스를 Servlet으로 등록(서버 실행 시 자동으로 객체화 됨)
//2)("주소")와 Servlet을 매핑해서 요청/응답 처리할 수 있게 함

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

   //method="GET"   == doGet() 오버라이딩
	// method="POST"  == doPost() 오버라이딩

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 파라미터(== 전달 인자, 전달된 값) (항상 String // html에서 얻어온 문서는 String이기때문)
		
		String inputId = req.getParameter("inputId");
		String inputPw = req.getParameter("inputPw");
		String inputIdCheck = req.getParameter("inputIdCheck");
		String inputName = req.getParameter("inputName");
		String inputAge = req.getParameter("inputAge");
		

		System.out.println("inputId : " + inputId);
		System.out.println("inputPw : " + inputPw);
		System.out.println("inputIdCheck : " + inputIdCheck);
		System.out.println("inputName : " + inputName);
		System.out.println("inputAge : " + inputAge);
		
		
		// 비밀번호, 비밀번호 확인 불일치 하면 
		// <h3>비밀번호가 일치하지 않습니다</h3>
		
		// 나이가 14세 미만인 경우
		// <h3>14세 이상만 가입 가능 합니다</h3>
		
		//조건을 모두 충족 시 
		//<h3>가입 성공</h3>
		//아이디, 비밀번호, 이름 , 나이 (ul,li 이용해서 출력하기)
		//======================================================================================================
		
		/*JSP를 이용해서 응답하기*/
		
		// 1) JSP 파일 작성
		// -> src/main/webapp/WEB-INF/views 폴더에 작성
		
		// 2) Servlet이 요청될 때 생성된
		//    HttpServletRequest, HttpServletResponse 객체를
		//    응답 화면을 대신 만들어서 응답해줄 JSP에서 전달
		//    == 요청 위임(forward)
		
		// * RequestDisptcher 객체 필요 *
		// - 어떤 JSP로 요청을 위임할지 지정하고 발송해주는 객체
		//  -> HttpServletRequest에 내장되어 있음
		
		
		//요청 위임할 JSP경로 작성
		//-> src/main/webapp 폴더를 기준으로 파일 경로 작성
		String path = "/WEB-INF/views/signup_result.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		
		dispatcher.forward(req, resp); //지정된 JSP로 요청 위임
		
		
		
		
		
		//======================================================================================================
		
	
		
//		/*응답 형식 지정*/
//		resp.setContentType("text/html; charset=utf-8"); //text로 되어있는 html형식
//		
//		/*출력용 스트림 얻어오기*/
//		PrintWriter out = resp.getWriter();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append("<!DOCTYPE html>");
//	
//		sb.append("<html>");
//		
//		
//		sb.append("<head>");
//		sb.append("<title>회원 가입 결과</title>");
//		sb.append("</head>");
//		
//		sb.append("<body>");
//		
//		if(!inputPw.equals(inputIdCheck)) {//비밀번호 일치여부 확인
//			
//			sb.append("<h3>비밀번호가 일치하지 않습니다</h3>");
//			
//		}else if(Integer.parseInt(inputAge)<14 ) { //나이가 14세 미만
//			
//			sb.append("<h3>14세 이상만 가입 가능 합니다</h3>");
//			
//		}else {
//			sb.append("<h3>가입 성공</h3>");
//			
//			sb.append("<ul>");
//			
//			sb.append(String.format("<li>ID : %s</li>", inputId));
//			sb.append(String.format("<li>PW : %s</li>", inputPw));
//			sb.append(String.format("<li>NAME : %s</li>", inputName));
//			sb.append(String.format("<li>AGE : %s</li>", inputAge));
//			
//			sb.append("</ul>");			
//		}
//		
//		
//		sb.append("</body>");
//		sb.append("</html>");
//		
//		out.print(sb.toString());
		
	}
	
	

	
}
