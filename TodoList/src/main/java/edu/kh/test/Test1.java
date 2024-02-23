package edu.kh.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		
		/*
		 * 컬렉션 List를 이용해 IP별 사용자를 관리하고, 
		 * IP를 입력 받아 일치하는 사용자가 있는지 검색하는 코드를 작성
		 * 
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		//ArrayList 객체 생성한뒤 ipInfoList에 참조값 넣어주기
		List<IPInfo> ipInfoList = new ArrayList<IPInfo>();
		
		ipInfoList.add(new IPInfo("123.123.123.123","홍길동"));
		
		//인자로 String ip, String user를 넘겨 줘야 하는데 String user 인자가 없어 오류 
		ipInfoList.add(new IPInfo("212.133.7.8","이름"));
		ipInfoList.add(new IPInfo("123.123.123.123","홍길동"));
		

		
		System.out.print("입력 : ");
		
		String ip = sc.next();
		
		for(int i =0; i < ipInfoList.size() ; i ++) {
			
			//String타입은 == 가 아닌 equals로 비교
			if(ipInfoList.get(i).getIp().equals(ip)) {
				System.out.println(ipInfoList.get(i));
				return;
			}
			

		}
		
		System.out.println("일치하는 ip 사용자가 없습니다.");
		
		
	}
	
	
	
	
}
