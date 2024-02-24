package edu.kh.todoList.model.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dao.TodoListDAOImpl;
import edu.kh.todoList.model.dao.TodoListDao;
import edu.kh.todoList.model.dto.Todo;

public class TodoListServiceImpl implements TodoListService {

	
	// 필드
	private TodoListDao dao = null;
	
	// 기본 생성자
	public TodoListServiceImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TodoListServiceImpl 객체가  생성 될 때
		
		dao = new TodoListDAOImpl(); // TodoListDaoImpl 객체 생성
		
		
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	@Override
	public Map<String, Object> todoListFullView() {
		
		// 1. 할 일 목록 DAO에서 얻어오기
		List<Todo> todoList = dao.todoListFullView();
		
		// 2. 할 일 목록에서 완료 (complete 필드 값이 true인 요소가 몇 개인지 카운트
		
		int completeCount = 0;
		
		for(Todo todo : todoList ) {//todoList 순차 접근
			
			//isComplete() == getComplete()
			if(todo.isComplete()) { // true인 경우
				completeCount++;
			}
		}
		
		// 3. todoList, completeCount를 저장할 Map 생성
		// -> 메서드는 반환을 하나밖에 못해서 여러 개를 반환해야 하는 경우 묶어서 반환
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		// 4. map 반환
		return map;
	}
	
	//----------------------------------------------------------------------------------------------------------

	@Override
	public String dateFormat(LocalDateTime regDate) {
		// yyyy-MM-dd HH:mm:ss 형태 날짜 반환
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 
		 // regDate에 저장되어있는 날짜 데이터를
		 // formatter에 지정된 형식으로 변경
		 String formattedDateTime = regDate.format(formatter);
		
		return formattedDateTime;
	}

	
	@Override
	public String todoDetailView(int index) {
		
		//1. DAO에 있는 todoList에서 index번째 요소(Todo) 반환 받기
		//	 없으면 null 반환
		
		Todo todo = dao.todoDetailView(index);
		
		//2. 반환 받은 todo가 null인 경우 null반환
		if(todo == null) return null;
		
		//3. Todo가 참조하는 객체가 있다면 문자열 가공
		
		StringBuilder sb = new StringBuilder(); 
		
		sb.append("--------------------------------------------\n");
		sb.append( String.format("제목 : %s\n", todo.getTitle()) );
		sb.append( String.format("등록일 : %s\n", dateFormat(todo.getRegDate())) );		
		sb.append( String.format("완료여부 : %s\n", todo.isComplete() ? "O" : "X") );
		sb.append("\n[세부 내용]\n");
		sb.append("--------------------------------------------\n");
		sb.append( String.format("%s\n", todo.getDetail()) );
		
		//4. 가공한 문자열 반환
		return sb.toString();
	}

	@Override
	public int todoAdd(String title, String detail) throws FileNotFoundException, IOException {

		// Todo 객체 생성
		Todo todo = new Todo(title, detail, false, LocalDateTime.now());
		// LocalDateTime.now() : 현재 시간 반환
		
		//DAO 메서드 호출 수 결과 반환 받기
		
		int index = dao.todoAdd(todo); // 추가된 index 또는 -1반환
		
		return index;
	}

	@Override
	public boolean todoComplete(int index) throws FileNotFoundException, IOException {

		return dao.todoComplete(index);
	}
	//Service 메서드가 별도 처리를 하지 않음
	//-> 아무것도 안한다고 해서 서비스를 사용하지 않으면 안됨 ! (꼭 써야하는게 규칙! )

	@Override
	public boolean todoUpdate(int index, String title, String detail) throws FileNotFoundException, IOException {
		
		return dao.todoUpdate(index, title, detail);
	}

	@Override
	public String todoDelete(int index) throws FileNotFoundException, IOException {

		//DAO 메서드 호출 후 결과 반환 받기
		// -> 삭제된 Todo 또는 null 
		Todo todo = dao.todoDelete(index);
		
		if(todo == null) return null;
		
		return todo.getTitle(); //제목 반환
	}
	
	
	

}
